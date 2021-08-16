package com.pingan.cn.common.utils;

public class StrUtil {
    public static boolean isBlank(String ...strs){
        if(strs==null||strs.length==0){
            return true;
        }
        for (String str:strs){
            if (str==null||str.trim().length()==0){
                return true;
            }
        }
        return false;
    }
}
