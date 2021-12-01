package com.youyu.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pin_leung
 * @date 2021/9/15
 * @description  list和字符串互转
 */
public class StringAndListUtil {

    //s:string
    //s2:分隔符合
    public static List<String> StringTOList(String s, String s2){
        if (s==null)return new ArrayList<String>();
        return Arrays.asList(s.split(s2));
    }

    //List转字符串
    public static String ListToString(List<String> list,String  s){
        StringBuilder sb = new StringBuilder();
        if (list==null||list.size()==0){
            return "";
        }
        for (int i = 0; i < list.size(); i++) {
            if (i==list.size()-1){
                sb.append(list.get(i));
            }else {
                sb.append(list.get(i));
                sb.append(s);
            }
        }
        return sb.toString();
    }
}
