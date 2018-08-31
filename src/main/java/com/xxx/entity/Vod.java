package com.xxx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Vod {
    private Long id;

    private String title;

    private String name;

    private String postUrl;

    private String screenshotUrl;

    private Long seriesId;

    private Integer vodType;

    private Integer countryType;

    private Date publishDate;

    private Date createTime;

    private Date updateTime;

    private String content;

}