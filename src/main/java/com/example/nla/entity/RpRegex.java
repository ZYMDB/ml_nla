package com.example.nla.entity;

import lombok.Data;

/**
 * (RpRegex)实体类
 *
 * @author makejava
 * @since 2022-04-24 17:11:40
 */
@Data
public class RpRegex{
    private Integer modeCode;
    private String regex;
    private String mode;
    private String regexDes;
}