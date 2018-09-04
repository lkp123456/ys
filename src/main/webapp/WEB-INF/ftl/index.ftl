<!DOCTYPE html>
<html>
<head>
    <title>下载_电影天堂_免费电影_迅雷电影下载</title>
    <META content="下载,电影下载,最新电影" name=keywords>
    <META content="迅雷下载网，分享最新电影，高清电影、综艺、动漫、电视剧等下载！" name=description>
    <link href="/static/css/dytt8.css" rel="stylesheet" type="text/css"/>
    <base target="_blank">
</head>
<body>
<div id="header">
    <div class="contain">
        <h4>
            <a href="index.ftl"></a>
        </h4>
        <div id="headerright">
            <div id="about" align="right">
                <!-- <SCRIPT src="js2/760h.js"></SCRIPT> -->
            </div>
        </div>

        <div id="menu">
            <div class="contain">
                <ul>
                <#list categorys as category>
                    <li>
                        <a href="${category.getUri()}">${category.getName()}</a>
                    </li>
                </#list>
                </ul>
            </div>
        </div>


        <div class="bd2">



            <!--{start:最新影视下载-->
            <!--##########}end:header###########-->
            <!--{start:body content-->
            <div class="bd3">
                <!--{start:内容区左侧-->
                <div class="bd3l">


                    <!--{start:最新-->
                    <div class="co_area2">
                        <div class="title_all">
                            <p>经典影视</p>
                        </div>
                        <div class="co_content2">
                            <ul>
                                <a href="html/gndy/jddy/20160320/50523.html">IMDB评分8分以上影片300余部</a>
                                <br/>
                                <a href="html/gndy/dyzz/20180831/57349.html">2018年悬疑动作《狄仁杰之四</a>
                                <br/>
                                <a href="html/gndy/dyzz/20180830/57348.html">2018年悬疑剧情《金钱世界/万</a>
                                <br/>
                                <a href="html/gndy/dyzz/20180830/57347.html">2018年动画喜剧《神奇马戏团</a>
                                <br/>
                            </ul>
                        </div>
                    </div>

                    <!--}end:最新-->
                </div>
                <!--}end:内容区左侧-->

                <div class="bd3r">
                    <!--{start:搜索最新下载最新文章-->
                    <div>
                        <!--{start:内容区右侧1中间-->
                            <!--{start:搜索框-->
                            <div class="search">
                                <form onSubmit="window.location=this.field.options[this.field.selectedIndex].value+this.keyword.value; return false;">
                                    <div class="searchl">
                                        <p>
                                            <strong>搜索:</strong>
                                            <input name="keyword" type="text" value="关键字" onFocus="this.select();"
                                                   class="formhue"/>


                                            <select name="field" style="width:80px;height:17px;" class="formhue">
                                                <option value="http://s.ygdy8.com/plus/so.php?kwtype=0&searchtype=title&keyword=">
                                                    电影搜索
                                                </option>
                                                <option value="http://s.ygdy8.com/plus/so.php?kwtype=0&searchtype=title&keyword=">
                                                    电影搜索
                                                </option>
                                            </select>
                                        <p>
                                    </div>
                                    <div class="searchr">
                                        <input name="Submit" type="Submit" value="立即搜索"/>
                                    </div>
                                </form>
                            </div>
                            <!--}end:搜索框-->
                            <div class="co_area2">
                                <div class="title_all">
                                    <p>
                                        <strong>2018新片精品</strong>
                                        <em>
                                            <a href="http://www.dytt8.net/html/gndy/dyzz/index.html">更多>></a>
                                        </em>
                                    </p>
                                </div>

                                <div class="co_content8">
                                    <ul>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <#list lastVods.getList() as vod>
                                            <tr>
                                                <td width="85%" height="22" class="inddline">
                                                    ·[<a href="">最新影视下载</a>]
                                                    <a href="/${(vod.vodType==1)?string("tv","mv")}/${vod.id}.html">${vod.title}</a>
                                                    <br/>
                                                </td>
                                                <td width="15%" class="inddline">
                                                    <font color=#FF0000>${vod.publishDate?string("yyyy-MM-dd")}</font>
                                                </td>
                                            </tr>
                                        </#list>
                                        </table>
                                    </ul>
                                </div>
                            </div>
                            <!--}end:最新下载--->
                            <!--{start:最新TV下载-->
                            <div class="co_area2">
                                <div class="title_all">
                                    <p>
                                        <strong>2018精品电视剧</strong>
                                        <em>
                                            <a href="">更多>></a>
                                        </em>
                                    </p>
                                </div>
                                <div class="co_content3">
                                    <ul>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <#list lastTVs.getList() as vod>
                                            <tr>
                                                <td width="85%" height="22" class="inddline">
                                                    ·[<a href="">最新电视剧下载</a>]
                                                    <a href="/${(vod.vodType==1)?string("tv","mv")}/${vod.vodType}/${vod.id}.html">${vod.title}</a>${vod.title}</a>
                                                    <br/>
                                                </td>
                                                <td width="15%" class="inddline">
                                                    <font color=#FF0000>>${vod.publishDate?string("yyyy-MM-dd")}</font>
                                                </td>
                                            </tr>
                                        </#list>
                                        </table>
                                    </ul>
                                </div>

                            </div>
                    </div>
                </div>
            </div>


        </div>


    </div>
</div>
<#include "/footer.ftl">


