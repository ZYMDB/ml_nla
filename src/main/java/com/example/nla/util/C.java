package com.example.nla.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C {
    private String rs;
    public C(String rs) {
    this.rs = setBlue(rs);
    setGreen(rs);
    setRed(rs);
    }
    public C() {
    }
    private String setBlue(String content){
        if ( content.equals("小狗") || content.equals("小明") ) {
            return C.g(content,34);
        } else {
            return null;
        }
    }
    private String setGreen(String content){
        if ( content.equals("一只可爱的") ) {
            return  C.g(content,34);
        }else {
            return null;
        }
    }
    private String setRed(String content){
        if ( content.equals("有") ) {
            return content = C.g(content,34);
        }else {
            return null;
        }
    }
    static C c = new C();
    List<String> wordsLinkVerbs = new ArrayList<>();
    List<String> wordsInfoNouns = new ArrayList<>();
    {
        wordsInfoNouns.add("自然界");
    }
    /**
     * 控制台打印输出有颜色的文本
     * @param content
     * @param colour
     * @return
     */
    public static String g(String content, int colour) {
       // type = 1,3,4 ; colour = 31-36;
       return String.format("\033[%d;%dm%s\033[0m", colour, 1, content);
    }
    /**
     * 判断【实体】是否属于 <信息界>
     * @param word
     * @return
     */
    public static String isInfoList (String word) {
         if ( c.wordsInfoNouns.toString().contains(word)){
            return word;
         } else {
             return null;
         }
    }
    /**
     * 补全【信息实体】的表达
     * @param word
     * @return
     */
    public static String rpInfo(String word){
        if ( null != isInfoList(word)) {
            return "有关"+word + "的信息";
        } else {
            return word;
        }
    }
    /**
     * 把匹配的实体替换成其对应的id
     * @param line
     * @return
     */
    public static String rpObj(String rp,String line) throws IOException {
        return B.rpFromCSV(rp,line);
    }
    /**
     * 替换“是关系”多余内容为：null
     * @param word
     * @return
     */
    public static String rpToNullIsRT(String rp,String word) throws IOException {
        word = rpObj(rp,word);
        String pattern = "([0/-9]+)([^@]+)([是])([^@]+[。])";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(word);
        if (m.find()) {
            return m.group(1)+m.group(3)+m.group(4);
        } else {
            System.out.println("NO MATCH");
            return word;
        }
    }
    /**
     * 替换【名词所属短语】为【形容词】
     * @return
     */
    public static String rpNToAdj(String w){
        String pad = "([^@]+)([是])([^@]+)([的])([^@]+)([。|，])";
        Pattern r = Pattern.compile(pad);
        Matcher m = r.matcher(w);
        if (m.find()) {
            return m.group(1)+m.group(2)+m.group(5)+m.group(4)+m.group(3)+m.group(6);
        } else {
            System.out.println("NO MATCH");
            return w;
        }
    }
}