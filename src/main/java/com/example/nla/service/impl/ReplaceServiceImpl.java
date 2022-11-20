package com.example.nla.service.impl;

import com.example.nla.constants.PathConstants;
import com.example.nla.constants.RegexConstants;
import com.example.nla.service.CommonService;
import com.example.nla.service.ReplaceService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("ReplaceService")
public class ReplaceServiceImpl implements ReplaceService {
    @Autowired
    CommonService commonService;
    @Autowired
    PathConstants path;
    @Autowired
    RegexConstants regex;
    /**
     * 此方法是【根据正则表达式进行替换】的方法
     */
//    @Override
//    public String rpByRegex(String content){
//       String temp = content;
//       String rs = "";
//       Integer length =  regexMapper.getLength();
//
//        // 第一步：正则替换
//        for (int i = 0; i < length; i++) {
//                        content = rpRegexUtil(content,i+1);
//                                }
//        // 第二步：单词替换
//        content = rpKeyWordsUtil(content);
//
//        // 第三步：杂句删除
//        for (String p : Arrays.asList(content.split("。"))) {
//            p = p + "。";
//            List<String> list = Arrays.asList(p.split("，"));
//            for (String str : list) {
//                // 如果有”元标字“，就处理句子中的逗号
//                if (Pattern.compile("有|是|用|把").matcher(str).find()) {
//                                        if ( !str.contains("。") ) {
//                        rs += str + "，";
//                    } else {
//                        rs += str;
//                    }
//                }
//            }
//        }
//
//        rs = (rs==""?content:rs).replace("，","");
//        float ratio = 1f-((float)rs.length()/temp.length());
//
//        String ratioS = (
//                new DecimalFormat("##0.00").
//                format(ratio).
//                replace("0.0","")+"%").
//                replace("0.","");
//        return "原始句子：\n"+temp+"\n压缩后句子:\n"+rs+"\n压缩率:\n"+ratioS;
//    }
    /**
     * 此方法是【根据正则表达式进行替换】的辅助方法
     */
//    public String rpRegexUtil(String content,Integer modelCode){
//        RpRegex rpRegex = regexMapper.queryById(modelCode);
//        String pattern = rpRegex.getRegex();
//        String model = rpRegex.getMode();
//        if (model.contains("0")) {
//            return cycleRxRp(content, pattern);
//        } else {
//            return reRp(content,pattern,model);
//        }
//    }


    /**
     * 此方法是将字符串中的旧关键字转化为新关键字
     * @param content
     * @return
     */
//    public String rpKeyWordsUtil(String content){
//        List<Rp> rps = rpMapper.queryAll2();
//        for (Rp rp: rps) {
//            String oldS = rp.getOldStr();
//            String newS = rp.getNewStr();
//            if ( content.contains(oldS) ) {
//                content = content.replace(oldS,newS);
//            }
//        }
//        return content;
//    }

    private String cycleRxRp(String content,String pattern){
        /**
         * 思路：
         * 先分组
         * 后分别对每一组替换（循环替换，直到匹配不上为止）
         * 最后拼起来
         */
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        List<String> list = Arrays.asList(content.split("，"));
        String s = "";
        // 分组
        for (String str : list) {
            // 创建 matcher 对象
            Matcher m = r.matcher(str);
            // 循环替换 （正则表达式是单线程匹配）
            while(m.find()){
                str = str.replace(m.group(0), "")+"，";
            }
            // 合并
            s += str.contains("。")?str:str+"，";
        }
        return s.replaceAll("(，)+", "$1");
    }

    private String reRp(String content, String pattern,String model) {
        StringBuffer sb = new StringBuffer();
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        String str[] = model.split("\\$");
        int ind =Integer.valueOf(str[1].split("[\u4e00-\u9af5]+")[0]);
        String left = str[0];
        String right = str[1].split("[0-9]+")[1];
        while ( m.find() ) {
            String center = m.group(ind);
            content = content.replace(m.group(),left+center+right);
        }
        return content;
    }

    /**
     * @desc 此方法返回单个被匹配的元素
     * @param content 需要匹配的素材
     * @return 返回内容
     */
    @Override
    public JSONArray getSingleContent(String content) throws JSONException {
        return getZhuYu(content);
    }

    /**
     * @Time 2022/8/16 9:11
     * @Author mabo
     * @param content ： 待处理句子
     * @return 主语、谓语、宾语
     * @desc 获取基本句法成分（主谓宾）
     */
    private JSONArray getZhuYu(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(); // 声明结果集
        String str[] = content.split("[，]"); // 将句子拆分成一个个的
        Pattern zp = Pattern.compile(regex.match_subject); // 封装主语匹配正则对象
        Pattern bp = Pattern.compile(regex.match_object); // 封装宾语匹配正则对象
        String  zs; // 声明主语
        String bs; // 声明宾语
        List<String> zList = new ArrayList(); // 声明主语集
        List<String> bList = new ArrayList(); // 声明宾语集
        for ( String c : str ) {
            System.out.println("------------句子-----------\n"+c);
            Matcher zm = zp.matcher(c); // 获取主语匹配器
            Matcher bm = bp.matcher(c); // 获取宾语匹配器
            // 使用主语匹配器匹配主语，并将匹配到的主语加入主语集中
            if ( zm.find() ) {
                String ms = zm.group();
                zs = ms.split("[不就乃]?(是)")[0];
                zList.add(zs==""?"占":zs);
            } else {
                zList.add("没主语");
            }
            // 使用宾语匹配器匹配宾语，并将匹配到的宾语加入宾语集中
            if ( bm.find() ) {
                String ms = bm.group();
                String[] bss = ms.split("(的|是|之一|由|从|一词)");
                int len = bss.length;
                int index = len - 1;
                System.out.println("len:"+len);
                bs = bss[index];
                bList.add(bs==""?"占":bs);
            } else {
                bList.add("没宾语");
            }
        }
        for (int i = 0; i < zList.size(); i++) {
            JSONObject json = new JSONObject();
            // 主语
            String subject =  zList.get(i)
                    .replace(" ","") // 结果处理：去空格
                    .replaceAll("[“”。]+","");// 结果处理：去引号
            // 宾语
            String object = bList.get(i)
                    .replace(" ","") // 结果处理：去空格
                    .replaceAll("[“”。]+",""); // 结果处理：去引号
            json.put("subject",subject);
            json.put("object",object); // 结果处理：去引号
            boolean b = subject.contains("没") && object.contains("没");
            json.put("model",b?"unknown":"is");
            jsonArray.add(json);
        }
        return jsonArray;
    }
    @Override
    public List<String> chooseStatement(String regexp, String content){
        List<String> states = new ArrayList<>();
        // 1. 分割句子
        String temps[] = content.split("[，]");
        // 2. 筛选句子
        Pattern pattern = Pattern.compile(regexp);
        for ( String temp : temps ) {
            Matcher matcher = pattern.matcher(temp);
            if ( matcher.find() ) {
                states.add( matcher.group() );
            }
        }
        return states;
    }

//    private JSONArray cutStatement(){
//
//    }
    /**
     * 说明：以下方法是与句子相关的操作 2022/8/17 13:50 @Author mabo
     * @param content
     * @return
     */
    @Override
    public List<String> getSubStatesByComma(String content) {
        List<String> list = Arrays.asList(content.split("[，]"));
        List<String> result = new ArrayList<>();
        for ( String s : list ) {
            result.add( s.trim());
        }
        return result;
    }

    @Override
    public List<String> getSubStatesByKey(List<String> contents) {
        String keyRes = commonService.readCSV(path.CD).toString();
        String keyStr = keyRes.replaceAll("[\\[\\]]+","")
                              .replace(", ","|");
        keyStr = keyStr.substring(0,keyStr.length()-1);
        System.out.println("keyStr:"+keyStr);

        List<String> result = new ArrayList<>();
        for ( String s : contents ) {
            result.addAll(Arrays.asList(s.trim().split("("+keyStr+")")));
        }
        if ( result.size() > 0) {
            return result;
        }
        return null;
    }

    @Override
    public List<String> getOneGroupByRegex(List<String> contents) {
        // 读取正则
        List<String> regexList = commonService.getRegex();
        // 结果集
        List<String> list = new ArrayList<>();
        // 遍历规则
        for (String regex : regexList) {
        // 构建模式
        Pattern pattern = Pattern.compile(regex.split("：")[0]);
            // 遍历内容
            for ( String content : contents ) {
                Matcher matcher = pattern.matcher(content);
                // 将匹配的内容加入结果集
                if( matcher.find() ) {
                    list.add( matcher.group(Integer.valueOf(regex.split("：")[1])) );
                } else {
                    list.add( "不变" );
                }
            }
        }
        // 结果集不为空时返回
        if ( null != list && ! list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**
     * 说明：消除修饰词集
     */
    @Override
    public List<String> eliminateWordsByKey(List<String> contents) {
        List<String> adj_words = commonService.readCSV(path.CAD);
        List<String> num_words = commonService.readCSV(path.CND);
        List<String> list = new ArrayList<>();
        list.addAll(adj_words);
        list.addAll(num_words);
        List<String> temps = new ArrayList<>();
        // 处理words，构建【修饰词】词典
        for(String word : list){
            // 三个字有的地
            if ( word.contains("j") ) {
                temps.add(word.replace("：adj","的"));
            } else {
                temps.add(word.replace("：adv","地"));
            }
            // 两个字无的地
            temps.add(word.split("：")[0]);
        }
        List<String> result = new ArrayList<>();
        String keyStr = temps.toString().replaceAll("[\\[\\]]+","").replace(", ","|");
            for ( String content : contents ) {
              String  temp = content.replaceAll("("+keyStr+")","");
              result.add(temp);
            }
            if( null != result && ! result.isEmpty() ){
                return result;
            }
        return null;
    }

    /**
     * 替换匹配词
     */
    @Override
    public List<String> replaceWordsByKey(List<String> contents) {
        List<String> similarWords =  commonService.readCSV(path.RS);
        List<String> result = new ArrayList<>();
        for (String content : contents) {
            for (String similarWord : similarWords) {
                String left = similarWord.split("：")[0];
                String right = similarWord.split("：")[1];
                if ( content.contains(left) ) {
                    result.add(content.replace(left, right));
                } else {
                    result.add(content);
                }
                break;
            }
        }

        if ( null != result && ! result.isEmpty()) {
            return result;
        }
        return null;
    }

    /**
     * 提取谓词
     */
    @Override
    public List<String> extractPredicate(List<String> contents) {
        // 大概率匹配谓词的先行词
        Pattern pattern = Pattern.compile(regex.extract_predicate);
        // 元标字匹配
        String is_regex = "([^还]*)(是)([^。]*)";
        String have_regex = "([^还]*)(有)([^。]*)";
        String use_regex = "([^还]*)(用)([^。]*)";
        List<String> result = new ArrayList<>();
        // 替换匹配词
        contents = this.replaceWordsByKey(contents);
        // 消除修饰词
        contents = this.eliminateWordsByKey(contents);

        for ( String content : contents ) {
            Matcher m = pattern.matcher(content);
            if ( m.find() ) {
                result.add( m.group(2) );
            } else if ( content.matches(is_regex) ) {
                Matcher is = Pattern.compile(is_regex).matcher(content);
                if ( is.find() ) {
                    System.out.println(is.group(2));
                }
                result.add(content.replace(is.group(2),"∈"));
            } else if ( content.matches(have_regex) ) {
                result.add("元谓词【有】");
            } else if ( content.matches(use_regex) ) {
                result.add("元谓词【用】");
            } else {
                result.add("无谓词");
            }
        }

        if ( null != result && ! result.isEmpty() ) {
            return result;
        }

        return null;
    }

    public String repeatRpBySameRegex(String content,String regex,String replaceModel,int frequency){
        for (int j = 0; j < frequency; j++) {
            content = content.replaceAll(regex,replaceModel);
        }
        return content;
    }
}
