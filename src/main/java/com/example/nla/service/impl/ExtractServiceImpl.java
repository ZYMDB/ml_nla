package com.example.nla.service.impl;

import com.example.nla.constants.PathConstants;
import com.example.nla.constants.RegexConstants;
import com.example.nla.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("ExtractService")
public class ExtractServiceImpl implements ExtractService {
    /**
     * 注入常量
     */
    @Autowired
    PathConstants path;
    @Autowired
    RegexConstants regex;


    /**
     * 公共代码自动整合
     */
    private List<String> getPattern(List<String> contents, List<String> result, String reg, Integer gid) {
        Pattern pattern = Pattern.compile(reg);
            for (String content : contents) {
            Matcher matcher = pattern.matcher(content);
                if ( matcher.find() ) {
                    result.add(matcher.group(gid));
                } else {
                    result.add(content);
                }
            }
        return result;
    }

    /**
     * 提取主语
     */
    @Override
    public List<String> extractSubjects(List<String> contents) {
        List<String> result = new ArrayList<>();
        System.out.println("source:"+regex.extract_subject);
        String reg = regex.extract_subject.split("：")[0];
        String gidS = regex.extract_subject.split("：")[1];
        System.out.println("reg:"+reg+"\tgidS:"+gidS);
        Integer gid = Integer.parseInt(gidS);
        return getPattern(contents, result, reg, gid);
    }

    /**
     * 提取谓语
     */
    @Override
    public List<String> extractPredicates(List<String> contents) {
        List<String> result = new ArrayList<>();
        String reg = regex.extract_predicate.split("：")[0];
        Integer gid = Integer.parseInt(regex.extract_predicate.split("：")[1]);
        return getPattern(contents, result, reg, gid);
    }

    /**
     * 提取宾语
     */
    @Override
    public List<String> extractObjects(List<String> contents) {
        List<String> result = new ArrayList<>();
        String reg = regex.extract_object.split("：")[0];
        Integer gid = Integer.parseInt(regex.extract_object.split("：")[1]);
        return getPattern(contents, result, reg, gid);
    }
}
