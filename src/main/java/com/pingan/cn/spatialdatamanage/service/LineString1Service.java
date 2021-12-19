package com.pingan.cn.spatialdatamanage.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.ningbomap.entity.Point1;
import com.pingan.cn.spatialdatamanage.dao.LineString1Dao;
import com.pingan.cn.spatialdatamanage.dao.Point1Dao;
import com.pingan.cn.spatialdatamanage.dto.LineString1Dto;
import com.pingan.cn.spatialdatamanage.dto.Point1Dto;
import com.pingan.cn.spatialdatamanage.entity.LineString1;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geolatte.geom.LineString;
import org.geolatte.geom.codec.Wkt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("lineString1Service")
public class LineString1Service {
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Autowired
    private LineString1Dao actionDao;

    public LineString1 saveAction(LineString1Dto action){
        try {
            LineString1 entity = new LineString1();
//            BeanUtils.copyProperties(entity, action);
            org.geolatte.geom.LineString geo = (org.geolatte.geom.LineString) Wkt.fromWkt(action.getWkt());

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

    public LineString1 findById(String id){
        return actionDao.findById(id).get();
    }

    /*public boolean update(Diaoshui action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Diaoshui> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Diaoshui oldAction = actionDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                actionDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Diaoshui> findAll(){
        return actionDao.findAll();
    }

    public Diaoshui findById(String id){
        return actionDao.findById(id).get();
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

    public boolean deleteBatch(String[] ids){
        ArrayList<Diaoshui> arrayList = new ArrayList<>();
        for (String id:ids) {
            Diaoshui entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }*/
}
