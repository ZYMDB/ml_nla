package com.example.nla.entity;

import java.util.Date;
import lombok.Data;

/**
 * (DeconGroup)实体类
 *
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Data
public class DeconGroup{
    private Integer decId;
    private String decGroupName;
    private String decGropuDetails;
    private Date decCreateTime;
    private Date decModifyTime;
}