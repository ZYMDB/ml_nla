package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (Action)实体类
 * @author makejava
 * @since 2022-04-05 21:21:20
 */
@Data
public class Action{
    private Integer actionId;
    private String actionName;
    private String actionDes;
    private String actionRelationObjs;
    private String actionResult;
    private Date createTime;
    private Date modifyTime;
}