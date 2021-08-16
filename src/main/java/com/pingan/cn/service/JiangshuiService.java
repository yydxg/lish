package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.JiangshuiDao;
import com.pingan.cn.entity.Jiangshui;
import com.pingan.cn.entity.Qiya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("jiangshuiService")
public class JiangshuiService {
    @Autowired
    private JiangshuiDao jiangshuiDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public ResponseUtil findAll(){
        try {
            List<Jiangshui> result = jiangshuiDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Jiangshui> findByTime(String time){
        try {
            List<Jiangshui> jiangshuis = jiangshuiDao.findByTime(time);
            return jiangshuis;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDistinctOrderByTimeDesc(){
        return jiangshuiDao.findDistinctOrderByTimeDesc();
    }

    public List<Jiangshui> findHistoryByM(String m){
        try{
            return jiangshuiDao.findHistoryByM(m);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
