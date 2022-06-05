package com.example.nla.controller;

import java.util.Date;
import lombok.Data;

/**
 * (Con)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:24
 */
@Data
public class Con{
    private Integer conId;
    private String conName;
    private String conDes;
    private String conRelationObjs;
    private Date createTime;
    private Date modifyTime;
}