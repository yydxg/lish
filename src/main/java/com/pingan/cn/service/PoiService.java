package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.PoiDao;
import com.pingan.cn.entity.Poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("poiService")
public class PoiService {

    @Autowired
    private PoiDao poiDao;

    public ResponseUtil save(Poi poi){
        try {
            Poi result = poiDao.saveAndFlush(poi);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<Poi> result = poiDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
