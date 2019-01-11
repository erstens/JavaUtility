package com.erstens.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    /**
     * 获取所有单个数字，并连接在一起
     * @return
     */
    public static String getJoinAllNumberString(String txt) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(txt);
        return matcher.replaceAll("").trim() ;
    }

    /**
     * 最后一个连续数字串
     * @return
     */
    public static String getLastNumberString(String txt) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(txt);
        Stack<String> s = new Stack();
        while(matcher.find()) {
            s.push(matcher.group());
        }
        if (!s.empty()) {
            return s.pop();
        }
        return "";
    }

    /**
     * 最后一个连续数字串,并指定一个默认值
     * @return
     */
    public static String getLastNumberString(String txt,String defaults) {
        if("".equals(txt)){
            return defaults;
        }
        if(null == txt){
            return null;
        }
        return txt;
    }

    /**
     * 验证指定正则
     * @return
     */
    public static boolean valid(String reg,String txt) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    /**
     * 正则匹配文本，常规表达式
     * @return
     */
    public static List<String> getRegTxt(String reg , String txt) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(txt);
        List<String> list = new LinkedList<>();
        while(matcher.find()) {
            list.add(matcher.group());
        }
        return list ;
    }

    /**
     * 正则匹配文本，如果匹配不到，返回原文本
     * @return
     */
    public static List<String> getRegTxtDefaultTxt(String reg , String txt) {
        List<String> list = getRegTxt(reg, txt);
        if(list.isEmpty()) {
            list.add(txt);
        }
        return list ;
    }
}
