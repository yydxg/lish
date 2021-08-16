package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.QiyaDao;
import com.pingan.cn.entity.Qiya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("qiyaService")
public class QiyaService {
    @Autowired
    private QiyaDao qiyaDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public ResponseUtil findAll(){
        try {
            List<Qiya> result = qiyaDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Qiya> findByTime(String time){
        try {
            List<Qiya> qiyas = qiyaDao.findByTime(time);
            return qiyas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDistinctOrderByTimeDesc(){
        return qiyaDao.findDistinctOrderByTimeDesc();
    }

    public List<Qiya> findHistoryByM(String m){
        try{
            return qiyaDao.findHistoryByM(m);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
