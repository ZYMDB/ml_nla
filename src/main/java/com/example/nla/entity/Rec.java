package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Rec)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Rec{
    private Integer recId;
    private String recName;
    private String recDes;
    private String recRelationObjs;
    private Date createTime;
    private Date modifyTime;
}