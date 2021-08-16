package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.CommonDao;
import com.pingan.cn.dao.RabbitStationDao;
import com.pingan.cn.entity.Common;
import com.pingan.cn.entity.RabbitStation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("rabbitStationService")
public class RabbitStationService {
    @Autowired
    private RabbitStationDao commonDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RabbitStation saveAction(RabbitStation common){
        RabbitStation save = null;
        try {
            return commonDao.save(common);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(RabbitStation common){
        if(common == null || StrUtil.isBlank(common.getId())){
            return  false;
        }
        Optional<RabbitStation> byId = commonDao.findById(common.getId());
        if (byId.isPresent()){
            RabbitStation oldCommon = commonDao.findById(common.getId()).get();
            BeanUtils.copyProperties(common, oldCommon, SpringUtil.getNullPropertyNames(common));
            try {
                commonDao.saveAndFlush(oldCommon);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<RabbitStation> findAll(){
        return commonDao.findAll();
    }

    public RabbitStation findById(String id){
        return commonDao.findById(id).get();
    }

    public List<RabbitStation> findByV2(String module,String v2){
        return commonDao.findByV2(module,v2);
    }

    public List<RabbitStation> findByV2AndV3(String module, String v2,String v3){
        try {
            List<RabbitStation> commons = commonDao.findByV2AndV3(module,v2,v3);
            return commons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<RabbitStation> findByV2AndV5(String module, String v2,String v5){
        try {
            List<RabbitStation> commons = commonDao.findByV2AndV5(module,v2,v5);
            return commons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(String id){
        try {
            commonDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
