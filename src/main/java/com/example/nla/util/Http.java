package com.example.nla.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Http {
    public static void main(String[] args) {
        String str = "逻辑/n  是/vl  现代/nt  汉语/n  常用/v  词/n  之一/m  ，/w  是/vl  一个/r  典型/a  的/u  音译/v 音译/v 外来/v 词/n";
        String temp = str.split("/u")[1].replaceAll("/v","#");
        int i = 0;
        for (char c : temp.toCharArray()) {
            if ( c == '#' ) {
                i++;
            }
        }
        System.out.println("一共有"+i+"个符合要求的动词");
        for (int j = 0; j < 3; j++) {
            str = str.replaceAll("(u.*)(/v[a-z]*[ ]*)","$1");
        }
        System.out.println("最终替换后的句子为：\n"+str);
    }
}
