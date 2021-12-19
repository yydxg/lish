package com.pingan.cn.spatialdatamanage.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.spatialdatamanage.dao.LineString1Dao;
import com.pingan.cn.spatialdatamanage.dao.Polygon1Dao;
import com.pingan.cn.spatialdatamanage.dto.Point1Dto;
import com.pingan.cn.spatialdatamanage.dto.Polygon1Dto;
import com.pingan.cn.spatialdatamanage.entity.LineString1;
import com.pingan.cn.spatialdatamanage.entity.Polygon1;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geolatte.geom.LineString;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.codec.Wkt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("polygon1Service")
public class Polygon1Service {
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Autowired
    private Polygon1Dao actionDao;

    public Polygon1 saveAction(Polygon1Dto action){
        try {
            Polygon1 entity = new Polygon1();
//            BeanUtils.copyProperties(entity, action, SpringUtil.getNullPropertyNames(entity));
            Polygon geo = (Polygon) Wkt.fromWkt(action.getWkt());

            entity.setOthers(action.getOthers());
            entity.setGeometry(geo);
            actionDao.save(entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Polygon1 findById(String id){
        return actionDao.findById(id).get();
    }

}
