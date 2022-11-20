package com.example.nla.service;

import net.minidev.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface ReplaceService {
//    String rpByRegex(String content);
    JSONArray getSingleContent(String content) throws JSONException;
    List<String> chooseStatement(String regexp, String content);
    // 以下服务是对句子的处理，以一个句子为一个单位 2022/8/17 10:50 @Author mabo
    /**
     * 性质：一个句子可以有多个“逗句”，
     *      整个句子至多有一个主要描述对象，
     *      每个逗句都可以有一个或多个对象；
     *      一个句子可以描述多个活动，
     *      一个句子可以描述多个现象，
     *      对象围绕着活动和现象
     * 目标：提取事件 --->  "谁" 有（在）、用(把、将)  "什么环境 | 什么资源"  做(有)  "什么事"  是(有)  "什么结果"
     *      提取逻辑 --->  A 是 B  |  A 有 B  |  A 用 B
     *      提取情绪
     *      提取态度
     *      提取观点
     *      提取思想
     *      提取行为
     * 备注：【用】【把】【将】辨析
     *       共性：均是【双行为元标字】  A 用|把|将  B  做  C
     *      【用】：A 驾驭 B 使 A 变成 C （B多为物性事物）
     *      【把】：A 操作 B 将 B 变成 C （B多为物性事物）
     *      【将】：A 操作 B 将 B 变成 C （B多为人性事物）
     *      【做】：是一个代词，可以代指各种“活动”
     */


    /**
     * 以下操作的“扰信度”由低到高
     */

    // 通过逗号切割子句 (查句A)
    List<String> getSubStatesByComma(String content);
    // 提取谓词 (查词A)
    List<String> extractPredicate(List<String> contents);
    // 替换某些匹配词 (改词A)
    List<String> replaceWordsByKey(List<String> contents);
    // 消除某些匹配词 （删词A）
    List<String> eliminateWordsByKey(List<String> contents);
    // 通过关键字词切割子句 (删词B)
    List<String> getSubStatesByKey(List<String> contents);
    // 通过正则获取某一组内容 (删句A)
    List<String> getOneGroupByRegex(List<String> contents);
}
