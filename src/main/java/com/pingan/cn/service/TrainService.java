package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.TrainDao;
import com.pingan.cn.entity.Train;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("trainService")
public class TrainService {
    @Autowired
    private TrainDao trainDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public Train saveAction(Train train){
        Train save = null;
        try {
            return trainDao.save(train);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Train train){
        if(train == null || StrUtil.isBlank(train.getId())){
            return  false;
        }
        Optional<Train> byId = trainDao.findById(train.getId());
        if (byId.isPresent()){
            Train oldCommon = trainDao.findById(train.getId()).get();
            BeanUtils.copyProperties(train,oldCommon, SpringUtil.getNullPropertyNames(train));
            try {
                trainDao.saveAndFlush(oldCommon);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Train> findAll(){
        return trainDao.findAll();
    }

    public Train findById(String id){
        return trainDao.findById(id).get();
    }


    public List<Train> findByConditions(String fromStationName, String toStationName,String dateStr){
        try {
            List<Train> trains = trainDao.findByConditions(fromStationName,toStationName,dateStr);
            return trains;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findDistinct(String fromStationName, String toStationName,String dateStr){
        try {
            List<String> trainNos = trainDao.findDistinct(fromStationName,toStationName,dateStr);
            return trainNos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
