package com.xxx.entity;

import lombok.Data;

import java.util.Date;
@Data
public class DownloadUrl {
    private Long id;

    private Long vodId;

    private String sourceName;

    private String downloadUrl;

    private String magnetUrl;

    private Date createTime;

    private Date updateTime;

}