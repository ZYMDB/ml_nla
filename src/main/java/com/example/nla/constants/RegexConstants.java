package com.example.nla.constants;

import org.springframework.stereotype.Component;

@Component
public class RegexConstants {
    // 提取主语
    public static final String extract_subject = "(由)([^从]+)(从|决定)：2";
    public static final String extract_subject_v001 = "(由)([^从]+)(从|决定)：2";
    // 提取谓语
    public static final String extract_predicate = "(只有|才能)([^。]{2})([^。]*)：2";
    public static final String extract_predicate_v001 = "(只有|才能)([^。]{2})([^。]*)：2";
    // 提取宾语
    public static final String extract_object = "(只有|才能)([^。]{2})([^。]*)：2";
    public static final String extract_object_v001 = "(只有|才能)([^。]{2})([^。]*)：2";
    // 匹配主语
    public static final String match_subject = "([^它她他，。]*)(是|的[^。]+)";
    // 匹配宾语
    public static final String match_object = "(是|由|[具]?有)([^，]+)(的)?([^，]+)";
}
