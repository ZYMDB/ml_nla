package com.example.nla.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class R {
    /**
     * 此类是根据正则表达式进行替换的工具类
     */
    public String rpByRegex(String regex,String content,Integer model){
        String pattern = regex;
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(content);
        if (m.find()) {
            if ( model == 1 ) {
                return m.group(1)+m.group(3)+m.group(4);
            } else if ( model == 2 ) {
                return m.group(1)+m.group(4)+m.group(3)+m.group(2)+m.group(5);
            } else {
                return content;
            }
        } else {
            System.out.println("NO MATCH");
            return content;
        }
    }

}
