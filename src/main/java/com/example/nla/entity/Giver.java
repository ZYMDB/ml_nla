package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Giver)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class Giver{
    private Integer givId;
    private String givName;
    private String givDes;
    private String givRelationObjs;
    private Date createTime;
    private Date modifyTime;
}