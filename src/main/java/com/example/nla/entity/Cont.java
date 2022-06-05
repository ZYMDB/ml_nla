package com.example.nla.entity;

import lombok.Data;

import java.util.Date;

/**
 * (Cont)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:24
 */
@Data
public class Cont {
    private Integer conId;
    private String conName;
    private String conDes;
    private String conRelationObjs;
    private Date createTime;
    private Date modifyTime;
}