package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Tool)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Tool{
    private Integer toolId;
    private String toolName;
    private String toolDes;
    private String toolRelationObjs;
    private Date createTime;
    private Date modifyTime;
}