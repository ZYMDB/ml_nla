package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Env)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Env{
    private Integer envId;
    private String envName;
    private String envDes;
    private String envRelationObjs;
    private Date createTime;
    private Date modifyTime;
}