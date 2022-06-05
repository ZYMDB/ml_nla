package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Res)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Res{
    private Integer resId;
    private String resName;
    private String resDes;
    private String resRelationObjs;
    private Date createTime;
    private Date modifyTime;
}