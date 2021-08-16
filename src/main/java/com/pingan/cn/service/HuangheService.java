package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.controller.vo.FlightVo;
import com.pingan.cn.dao.FlightDao;
import com.pingan.cn.dao.HuangheDao;
import com.pingan.cn.entity.Flight;
import com.pingan.cn.entity.Huanghe;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("huangheService")
public class HuangheService {
    @Autowired
    private HuangheDao huangDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd hh:mm");

    public List<Huanghe> findAll(){
        return huangDao.findAll();
    }

    public Huanghe findById(Integer id){
        return huangDao.findById(id).get();
    }


    public ResponseUtil findByConditions(String lng, String lat){
        double lng1 = Double.parseDouble(lng);
        double lat1 = Double.parseDouble(lat);
        List<Huanghe> lists = huangDao.findByF4LikeAndF5Like(lng, lat);
        List<Huanghe> results = new ArrayList<>();
        for (Huanghe huanghe : lists){
            double lng2 = Double.parseDouble(huanghe.getF4());
            double lat2 = Double.parseDouble(huanghe.getF5());
            if(Math.abs(lat2-lat1)<0.0005 && Math.abs(lng2-lng1)<0.0005){
                System.out.println(huanghe.toString());
                results.add(huanghe);
            }
        }
        return ResponseUtil.success(results);
    }

//    public static void main(String[] args) {
//        HuangheService huangheService = new HuangheService();
//        huangheService.findByConditions("116.6569","34.679");
//    }

}
