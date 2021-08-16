package com.pingan.cn.common.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder
public class ResponseUtil {
    private boolean success;
    private Object data;
    private Object err;

    public static ResponseUtil success(){
        return new ResponseUtil(true,null,null);
    }

    public static ResponseUtil success(Object data){
        return new ResponseUtil(true,data,null);
    }

    public static ResponseUtil error(){
        return new ResponseUtil(false,null,null);
    }

    public static ResponseUtil error(Object err){
        return new ResponseUtil(false,null,err);
    }
}
