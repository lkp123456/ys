package com.xxx.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.Map;

public class HttpClientFactory {
    private static final Logger LOGGER = Logger.getLogger(HttpClientFactory.class);
    private static final int MAX_TIME_OUT = 30000;
    // max connection number in connection pool
    private static final int MAX_CONN = 200;
    // single route max connection number
    private static final int SINGLE_ROUTE_MAX_CONN = 100;
    // retry number after connection lost
    private static final int MAX_EXECUT_COUNT = 0;
    private static final boolean EXPECT_CONTINUE_ENABLED = false;
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    private PoolingHttpClientConnectionManager connManager = null;
    private HttpClient httpClient = null;

    private HttpClientFactory() {
        if (httpClient != null) {
            return;
        }
        try {
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    //.register("https",createPuppetSSLConnSocketFactory())
                    .build();

            connManager = new PoolingHttpClientConnectionManager(registry);
            connManager.setMaxTotal(MAX_CONN);
            connManager.setDefaultMaxPerRoute(SINGLE_ROUTE_MAX_CONN);

            httpClient = HttpClients.custom().setConnectionManager(connManager).setRetryHandler(httpRequestRetry())
                    .setDefaultRequestConfig(config()).build();
        } catch (Exception e) {
            LOGGER.error("获取httpClient(https)对象池异常:" + e.getMessage(), e);
        }
    }

    public static HttpClientFactory getInstance() {
        return HttpClientFactoryHolder.httpClientFactory;
    }

    /**
     * set request configuration
     */
    private static RequestConfig config() {
        // set timeout while get request connection from connection pool
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(MAX_TIME_OUT)
                .setConnectTimeout(MAX_TIME_OUT)// set connection timeout
                .setSocketTimeout(MAX_TIME_OUT)// set read connection timeout
                .setExpectContinueEnabled(EXPECT_CONTINUE_ENABLED).build();
        return requestConfig;
    }

    /**
     * request retry strategy
     */
    private HttpRequestRetryHandler httpRequestRetry() {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= MAX_EXECUT_COUNT) {// will give up after
                    // retry times up to
                    // MAX_EXECUT_COUNT
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// retry if
                    // server
                    // lost
                    // connection
                    LOGGER.error("httpclient 服务器连接丢失");
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// not retry
                    // while
                    // occur
                    // SSLHandshakeException
                    LOGGER.error("httpclient SSL握手异常");
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// connection
                    // timeout
                    LOGGER.error("httpclient 连接超时");
                    return false;
                }
                if (exception instanceof UnknownHostException) {// destination
                    // host
                    // unreachable
                    LOGGER.error("httpclient 目标服务器不可达");
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// connect
                    // timeout
                    LOGGER.error("httpclient 连接被拒绝");
                    return false;
                }
                if (exception instanceof SSLException) {// SSL exception
                    LOGGER.error("httpclient SSLException");
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                clientContext.getRequest();
                return false;
            }
        };
        return httpRequestRetryHandler;
    }

    /**
     * 创建SSL连接
     *
     * @throws Exception
     */
    private SSLConnectionSocketFactory createPuppetSSLConnSocketFactory() throws Exception {
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            SSLContext sslcontext = SSLContext.getInstance("TLS", "SunJSSE");
            sslcontext.init(null, new TrustManager[]{truseAllManager}, new java.security.SecureRandom());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
            return sslsf;
        } catch (Exception e) {
            LOGGER.error("获取SSL连接错误", e);
            throw new RuntimeException("createPuppetSSLConnSocketFactory");
        }

    }

    public synchronized void close() {
        if (connManager == null) {
            return;
        }
        connManager.shutdown();
        httpClient = null;
        connManager = null;
    }

    public synchronized boolean isOpen() {
        if (connManager == null) {
            return false;
        }
        return true;
    }

    public String get(String domainUrl, Map<String,String> headers) {
        if (!isOpen()) {
            return "本地连接池关闭";
        }
        long start = System.currentTimeMillis();
        HttpResponse response = null;
        String strReturn = "";
        String strLog = "";

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("HttpClient POST for:" + domainUrl);
            }
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)   //设置连接超时时间
                    .setConnectionRequestTimeout(5000) // 设置请求超时时间
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true)//默认允许自动重定向
                    .build();

            HttpGet httpGet = new HttpGet(domainUrl);
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
            httpGet.setConfig(requestConfig);

            response = httpClient.execute(httpGet);


            int reqStatus = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                strReturn = EntityUtils.toString(entity, "UTF-8");
            }
            if (reqStatus == 200) {
                LOGGER.info("\n\t正常响应数据:\n" + strReturn);
            } else {
                LOGGER.error("\n\t异常响应数据:\n请求数据=" + domainUrl + "\n" + strReturn);
            }
            long end = System.currentTimeMillis();
            LOGGER.info("HttpClient Get for:" + domainUrl + ", 参数：【" + strLog + "】请求花费时间：" + (end - start) + "ms");
            return strReturn;
        } catch (ClientProtocolException e) {
            throw new RuntimeException("strUrl=" + domainUrl + " ClientProtocolException:" + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("strUrl=" + domainUrl + " \nIOException:" + e.getMessage(),
                    e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());// will release
                    // connection
                    // automatically
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public String postData(String domainUrl, String jsonDatagram) {
        if (!isOpen()) {
            return "本地连接池关闭";
        }
        long start = System.currentTimeMillis();
        HttpResponse response = null;
        String strReturn = "";
        String strLog = "";
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("HttpClient POST for:" + domainUrl);
            }
            HttpPost post = new HttpPost(domainUrl);
            post.setHeader("Content-type", "application/json; charset=UTF-8");
            post.setHeader("Accept", "application/json; charset=UTF-8");
            post.setEntity(new StringEntity(jsonDatagram, Charset.forName("UTF-8")));
            response = httpClient.execute(post);
            int reqStatus = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                strReturn = EntityUtils.toString(entity, "UTF-8");
            }
            if (reqStatus == 200) {
                LOGGER.info("\n\t正常响应数据:\n" + strReturn);
            } else {
                LOGGER.error("\n\t异常响应数据:\n请求数据=" + domainUrl + "\n" + strReturn);
            }
            long end = System.currentTimeMillis();
            LOGGER.info("HttpClient POST for:" + domainUrl + ", 参数：【" + strLog + "】请求花费时间：" + (end - start) + "ms");
            return strReturn;
        } catch (ClientProtocolException e) {
            throw new RuntimeException("strUrl=" + domainUrl + " ClientProtocolException:" + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("strUrl=" + domainUrl + " \nIOException:" + e.getMessage(),
                    e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());// will release
                    // connection
                    // automatically
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class HttpClientFactoryHolder {
        public static final HttpClientFactory httpClientFactory = new HttpClientFactory();
    }
}
