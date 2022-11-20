package com.example.nla.service;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

public interface CommonService {
    // 读CSV
    List<String> readCSV(String path);
    // 获取量词词典
    List<String> getQuantifiers();
    // 获取正则词典
    List<String> getRegex();
    // json字符串转Map
    Map<String,String> JSONToMap(String jsonStr) throws JSONException;
}
