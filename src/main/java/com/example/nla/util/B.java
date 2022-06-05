package com.example.nla.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class B {

    public static String rpFromCSV(String fn,String content) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/dic/rp.csv"));
        String text = null;
        String rs = null;
        //读取内容为null则表示读到了文件末尾
        while ((text = br.readLine()) != null) {
            String str[] = text.split(",");
            if( fn.equals("112") ){
                if ( content.contains(str[1])) {
                    rs =  content.replace(str[1],str[2]);
                }
            } else if ( fn.equals("110") ) {
                if ( content.contains(str[1])) {
                    rs =  content.replace(str[1],str[0]);
                }
            }

        }
        return rs;
    }
}
