package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.QiwenDao;
import com.pingan.cn.entity.Qiwen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("wenduService")
public class QiwenService {
    @Autowired
    private QiwenDao qiwenDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public ResponseUtil findAll(){
        try {
            List<Qiwen> result = qiwenDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Qiwen> findByTime(String time){
        try {
            List<Qiwen> qiyas = qiwenDao.findByTime(time);
            return qiyas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDistinctOrderByTimeDesc(){
        return qiwenDao.findDistinctOrderByTimeDesc();
    }

    public List<Qiwen> findHistoryByM(String m){
        try{
            return qiwenDao.findHistoryByM(m);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
