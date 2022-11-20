package com.example.nla.util;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Test {
    public static void main(String[] args){
        String zy = "";
        String str = "概念是人类认识活动中最基本的思维形式之一，它是思维结构的最小单位，是构成判断和推理的基本要素，被比喻为“思维的细胞”。";
        String v = "([^它她他而，。]+)(是)";
        /**
         * 第一阶段：1-3 获取句子的主语
         */
        // 1.将正则封装成对象
        Pattern p = Pattern.compile(v);

        // 2.让正则对象和要作用的字符串相关联，获取匹配器对象
        Matcher m = p.matcher(str);

        // 3.先find再group
        while (m.find()) {
            zy = m.group().split("是")[0];
        }
        /**
         * 第二阶段：代略显
         */
        // 4.([它她他，。]+)(  )(是) 替换为 A$2$3
        String temp = replace(str,"([它她他，。]+)(是|被)","，\n"+zy+"$2");
        /**
         * 第三阶段：逗化整
         */
        String rs = replace(temp,"([，])\n","。\n");
        // 打印结果
        System.out.println(rs);
    }
    private static String replace(String str, String olds, String news) {
        str = str.replaceAll(olds, news);
        return str;
    }
}
