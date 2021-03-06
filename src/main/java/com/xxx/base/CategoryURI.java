package com.xxx.base;

import org.apache.commons.lang3.StringUtils;

public enum CategoryURI {

    LASTVIDEO("最近更新", "/index.html"),
    CHINAVIDEO("国产影片", "/mv/china.html"),
    OUMEIVIDEO("欧美影片", "/mv/oumei.html"),
    RIHANVIDEO("日韩影片", "/mv/rihan.html"),
    CHINATV("华语电视", "/tv/china.html"),
    OUMEITV("欧美电视", "/tv/oumei.html"),
    RIHANTV("日韩电视", "/tv/rihan.html");

    private String name;
    private String uri;

    CategoryURI(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public static boolean contains(String uri) {
        CategoryURI[] values = values();
        for (CategoryURI value : values) {
            if(StringUtils.equals(value.getUri(),uri)){
                return true;
            }
        }
        return false;
    }
}
