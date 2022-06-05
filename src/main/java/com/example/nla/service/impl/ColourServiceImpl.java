package com.example.nla.service.impl;

import com.example.nla.service.ColourService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
@Service("ColourService")
public class ColourServiceImpl implements ColourService {
    // 运行时字典容器声明
    List<String> list = new ArrayList<>();
    {
        BufferedReader br = null;
        try {
            // 获取磁盘中字典文件的输入流，以将数据输入到寄存器
            br = new BufferedReader(new FileReader("src/main/resources/dic/dic.csv"));
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
    // 声明三种词的队列
    BlockingQueue<String> vQueue = new ArrayBlockingQueue<>(list.size());
    BlockingQueue<String> aQueue = new ArrayBlockingQueue<>(list.size());
    BlockingQueue<String> nQueue = new ArrayBlockingQueue<>(list.size());
    {
        // 将字典容器中的字典数据分组
        for ( String s : list ) {
            if ( s.contains("v") ) {
                vQueue.add(s);
            }
            if ( s.contains("a") ) {
                aQueue.add(s);
            }
            if ( s.contains("n") ) {
                nQueue.add(s);
            }
        }
    }
    @Override
    public String getRS(String content){
        // 将匹配到名词组字典中的词替换成着色后的词的编码
        for (String ns : nQueue) {
            String nst  = ns.split(":")[0];
            String nsc = ns.split(":")[2];
            Boolean nb = !content.contains("<font color='red'>"+nst) && !content.contains(nst+"</font>")
                    && !content.contains("<font color='green'>"+nst) && !content.contains(nst+"</font>")
                    && !content.contains("<font color='blue'>"+nst) && !content.contains(nst+"</font>");
            if ( content.contains(nst) && nb ){
                content = content.replace(nst,"<font color='blue'>"+nst+"</font>").replace(nst,nsc);
                vQueue.remove(nst);
            }
        }
        // 将匹配到修词组字典中的词替换成着色后的词的编码
        for (String as : aQueue) {
            String ast  = as.split(":")[0];
            String asc  = as.split(":")[2];
            Boolean ab = !content.contains("<font color='red'>"+ast) && !content.contains(ast+"</font>")
                    && !content.contains("<font color='green'>"+ast) && !content.contains(ast+"</font>")
                    && !content.contains("<font color='blue'>"+ast) && !content.contains(ast+"</font>");
            if ( content.contains(ast) && ab ){
                content = content.replace(ast,"<font color='green'>"+ast+"</font>").replace(ast,asc);
                aQueue.remove(ast);
            }
        }
        // 将匹配到动词组字典中的词替换成着色后的词的编码
        for (String vs : vQueue) {
            String vst  = vs.split(":")[0];
            String vsc  = vs.split(":")[2];
            Boolean vb = !content.contains("<font color='red'>"+vst) && !content.contains(vst+"</font>")
                    && !content.contains("<font color='green'>"+vst) && !content.contains(vst+"</font>")
                    && !content.contains("<font color='blue'>"+vst) && !content.contains(vst+"</font>");
            if ( content.contains(vst) && vb ){
                content = content.replace(vst,"<font color='red'>"+vst+"</font>").replace(vst,vsc);
                vQueue.remove(vst);
            }
        }
        // 还原着色的词的编码为词本身
        for (String ls : list) {
            String lsc = ls.split(":")[2];
            String lso = ls.split(":")[0];
            if ( content.contains(lsc) ) {
                content = content.replace(lsc,lso);
            }
        }
        return content;
    }

}
