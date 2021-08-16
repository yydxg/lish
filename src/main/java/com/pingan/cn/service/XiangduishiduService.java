package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.QiyaDao;
import com.pingan.cn.dao.XiangduishiduDao;
import com.pingan.cn.entity.Qiya;
import com.pingan.cn.entity.Xiangduishidu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("xiangduishiduService")
public class XiangduishiduService {
    @Autowired
    private XiangduishiduDao xiangduishiduDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public ResponseUtil findAll(){
        try {
            List<Xiangduishidu> result = xiangduishiduDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Xiangduishidu> findByTime(String time){
        try {
            List<Xiangduishidu> xiangduishidus = xiangduishiduDao.findByTime(time);
            return xiangduishidus;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDistinctOrderByTimeDesc(){
        return xiangduishiduDao.findDistinctOrderByTimeDesc();
    }

    public List<Xiangduishidu> findHistoryByM(String m){
        try{
            return xiangduishiduDao.findHistoryByM(m);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
