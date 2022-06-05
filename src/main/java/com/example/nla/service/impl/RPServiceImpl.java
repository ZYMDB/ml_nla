package com.example.nla.service.impl;

import com.example.nla.entity.Rp;
import com.example.nla.entity.RpRegex;
import com.example.nla.mapper.RpMapper;
import com.example.nla.mapper.RpRegexMapper;
import com.example.nla.service.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("RPService")
public class RPServiceImpl implements RPService {
    @Autowired
    RpMapper rpMapper;
    @Autowired
    private RpRegexMapper regexMapper;
    @Override
    public String rpRelationWord(String word) {
        List<String> str = new ArrayList<>();
        return null;
    }
    /**
     * 此方法是【根据正则表达式进行替换】的方法
     */
    @Override
    public String rpByRegex(String content){
       Integer length =  regexMapper.getLength();
        for (int i = 0; i < length; i++) {
            content = rpRegexUtil(content,i+1);
        }
        return rpKeyWordsUtil(content);
    }
    /**
     * 此方法是【根据正则表达式进行替换】的辅助方法
     */
    public String rpRegexUtil(String content,Integer modelCode){
        RpRegex rpRegex = regexMapper.queryById(modelCode);
        String pattern = rpRegex.getRegex();
        String model = rpRegex.getMode();
        String right = model.split(" → ")[1];
        String strList[] = right.split("，");
        List<String> arrayRightIndexList = Arrays.asList(strList);
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(content);
        String s = "";
        if (m.find()) {
            for (String i : arrayRightIndexList) {
                s += m.group(Integer.valueOf(i));
            }
            return s;
        } else {
            System.out.println("NO MATCH");
            return content;
        }
    }
    /**
     * 此方法是将字符串中的旧关键字转化为新关键字
     * @param content
     * @return
     */
    public String rpKeyWordsUtil(String content){
        List<Rp> rps = rpMapper.queryAll2();
        for (Rp rp: rps) {
            String oldS = rp.getOldStr();
            String newS = rp.getNewStr();
            if ( content.contains(oldS) ) {
                content = content.replace(oldS,newS);
            }
        }
        return content;
    }

}
