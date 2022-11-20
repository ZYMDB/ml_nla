package com.example.nla;
//import com.example.nla.mapper.*;
import com.example.nla.constants.PathConstants;
import com.example.nla.service.CommonService;
import com.example.nla.service.ExtractService;
import com.example.nla.service.ReplaceService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@Slf4j
class NlaApplicationTests {
    @Autowired
    ReplaceService replaceService;
    @Autowired
    CommonService commonService;
    @Autowired
    ExtractService extractService;

    @Test
    void configurationTest(){
//        System.out.println("配置文件里读取的参数："+testV);
    }
    @Test
    void regexRPTest(){
//        String content = "SpringFramework已有十余年的历史了，是Java应用程序开发框架的事实标准。啦啦啦，嘻嘻嘻。";
        String content = "有序数组并不是所有操作都比常规数组要快";
//        String content = "ReactiveX.io给的定义是，Rx是一个使用可观察数据流进行异步编程的编程接口，ReactiveX结合了观察者模式、迭代器模式和函数式编程的精华。";
//        String content = "在有关21世纪的所有预测中，最不希望的一个也许是我们需要每天收集世界上任何地方、关于任何事情的海量数据。" +
//                "近几年来，人们见证了关于世界、生活和技术方面难以置信的海量数据爆炸，这也是我们确信引发变革的源动力。" +
//                "虽然我们生活在信息时代，但是仅仅收集数据而不发掘价值和抽取知识是没有任何意义的。";
//        System.out.println(replaceService.rpByRegex(content));
    }


    @Test
    void pp(){
        List<String> keyMatches = new ArrayList();
        List<String> rs = new ArrayList();
        String str = "逻辑” 是现代汉语常用词之一， 20 世纪初由大学问家严复从英语“ logic”一词翻译而来， 是一个典型的音译外来词。";
        String regex = "(是|由|[具]?有)([^，]+)(的)?([^，]+)";
        List<String> dict = commonService.getQuantifiers();
//        System.out.println("states:\n"+replaceService.getSingleContent(str));
        List<String> states = replaceService.chooseStatement(regex,str);
        System.out.println("states:\n"+states);
        for ( String state : states ) {
            for (String key : dict) {
                if ( state.contains(key) ) {
                    keyMatches.add(key);
                }
            }

            if ( null != keyMatches && ! keyMatches.isEmpty()) {
                for (String m : keyMatches) {
                    state = state.replace(m,"");
                }
                rs.add(state);
            }

        }
        System.out.println("keywords:\n"+keyMatches+"\nrs:\n"+rs);
    }



    @Test
    void readCSV() {
        // 生成量词词典
        List<String> dict = commonService.getQuantifiers();
        System.out.println("dictLen:"+dict.size()+"\n"+dict);
    }

    @Test
    void readRegCSV() {
        JSONArray jsonArray = new JSONArray();
        // 生成量词词典
        List<String> reg = commonService.readCSV("src/main/resources/dic/exa_regex.csv");
        Map<String,String> map = new HashMap<>();
        System.out.println("regLen:"+reg.size()+"\n"+reg);
        for (String r : reg) {
            String pattern = r.split("：")[0];
            Integer groupIndex = Integer.valueOf(r.split("：")[1]);
            String role = r.split("：")[2];
            String states[] = "思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。".split("，");
            for (String s : states) {
                Matcher m = Pattern.compile(pattern).matcher(s);
                if ( m.find() ) {
                    JSONObject json = new JSONObject();
                    String ms = m.group(groupIndex);
                    json.put("match",ms);
                    json.put("role",role);
                    jsonArray.add(json);
                }
            }
        }
        System.out.println(jsonArray);
    }


    @Test
    void testGetSubStatesByRegex(){
       List<String> list =  replaceService.getSubStatesByComma("思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。");
        List<String> rs = replaceService.getOneGroupByRegex(list);
       System.out.println("OldList:\n"+list+"\nnewList:\n"+rs);
    }

    @Test
    void testGetSubStatesByKey(){
        List<String> list =  replaceService.getSubStatesByComma("思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。");
        List<String> rs = replaceService.getSubStatesByKey(list);
        System.out.println("OldList:\n"+list+"\nnewList:\n"+rs);
    }
    @Test
    void testEliminateWordsByKey(){
        List<String> list =  replaceService.getSubStatesByComma("思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。");
        List<String> rs = replaceService.eliminateWordsByKey(list);
        System.out.println("OldList:\n"+list+"\nnewList:\n"+rs);
    }

    @Test
    void testExtractPredicate(){
        List<String> list =  replaceService.getSubStatesByComma("思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。");
        List<String> rs = replaceService.extractPredicate(list);
        System.out.println("OldList:\n"+list+"\nnewList:\n"+rs);
    }

    @Test
    void testExtractService(){
        List<String> list =  replaceService.getSubStatesByComma("思维的逻辑形式是普通逻辑的主要研究对象， 而逻辑形式又是由逻辑常项决定的， 因此只有准确地理解各种逻辑常项的确切含义， 才能正确掌握由常项决定的各种逻辑形式的逻辑性质。");
        List<String> rs = extractService.extractSubjects(list);
        System.out.println("OldList:\n"+list+"\nnewList:\n"+rs);
    }


    @Test
    void printCSV(){
        List<String> s = commonService.readCSV(PathConstants.CAD);
        for(String str : s){
            System.out.println("----------str:\t"+str);
        }
    }
}

