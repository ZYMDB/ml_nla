package com.example.nla.service.impl;
import com.example.nla.constants.PathConstants;
import com.example.nla.service.CommonService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CommonService")
public class CommonServiceImpl implements CommonService {
    @Autowired
    PathConstants path;
    @Override
    public List<String> readCSV(String path) {
        // 运行时字典容器声明
        List<String> list = new ArrayList<>();
        {
            BufferedReader br = null;
            try {
                // 获取磁盘中字典文件的输入流，以将数据输入到寄存器
                br = new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String text = null;
            //读取内容为null则表示读到了文件末尾
            while (true) {
                try {
                    if (!((text = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 向运行时字典容器中写入字典数据
                list.add(text);
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<String> getQuantifiers() {
        List<String> res = this.readCSV(path.CND);
        List<String> nList = new ArrayList<>();
        List<String> lList = new ArrayList<>();
        List<String> rList = new ArrayList<>();
        List<String> dict =  new ArrayList<>();
        for (String str : res) {
            if ( str.contains("n") ) {
                nList.add(str.split("：")[0]);
            }
            if ( str.contains("l") ) {
                lList.add(str.split("：")[0]);
            }
            if ( str.contains("r") ) {
                rList.add(str.split("：")[0]);
            }
        }
        // 三部分量词
        for ( String l : lList ) {
            if ( !l.equals("之") ) {
                for ( String n : nList ) {
                    for (String r : rList) {
                        if (  !r.equals("词") ) {
                            dict.add(l + n + r);
                        }
                    }
                }
            }
        }
        // 两部分量词A型
        for ( String n : nList ) {
            for ( String r : rList ) {
                dict.add(n+r);
            }
        }
        // 两部分量词B型
        for ( String l : lList ) {
            if ( !l.equals("每") ) {
                for (String n : nList) {
                    if ( !n.equals("十") ) {
                        dict.add(l + (n.equals("两") ? "二" : n));
                    }
                }
            }
        }
        return dict;
    }

    @Override
    public List<String> getRegex() {
        return this.readCSV(path.ER);
    }

    @Override
    public Map<String, String> JSONToMap(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray keys = jsonObject.names();
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);
            String value = jsonObject.getString(keys.getString(i));
//            System.out.println(key);
//            System.out.println(value);
            map.put(key, value);
        }
        if ( null != map && !map.isEmpty()) {
            return map;
        }
        return null;
    }


}
