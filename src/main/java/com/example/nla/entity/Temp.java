package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Temp)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Temp{
    private Integer tempId;
    private String tempType;
    private String tempName;
    private String tempDes;
    private String tempRelationObjs;
    private Date createTime;
    private Date modifyTime;
}