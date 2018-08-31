package com.xxx.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Series {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

}