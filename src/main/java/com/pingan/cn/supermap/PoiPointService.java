package com.pingan.cn.supermap;

import com.pingan.cn.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("poiPointService")
public class PoiPointService {
    @Autowired
    private PoiPointDao poiPointDao;

    public ResponseUtil save(PoiPoint poi){
        try {
            PoiPoint result = poiPointDao.saveAndFlush(poi);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil delete(String id){
        try {
            poiPointDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<PoiPoint> result = poiPointDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil clear(){
        try {
            poiPointDao.deleteAll();
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByName(String name){
        try {
            return ResponseUtil.success(poiPointDao.findByName(name));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByType(String type){
        if(type.equals("All")){
            return this.findAll();
        }else {
            try {
                return ResponseUtil.success(poiPointDao.findByType(type));
            }catch (Exception e){
                e.printStackTrace();
                return ResponseUtil.error(e.getMessage());
            }
        }
    }
}
