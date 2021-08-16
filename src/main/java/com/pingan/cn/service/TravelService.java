package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.PersonDao;
import com.pingan.cn.dao.TravelDao;
import com.pingan.cn.entity.Person;
import com.pingan.cn.entity.Travel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("travelService")
public class TravelService {
    @Autowired
    private TravelDao travelDao;

    public Travel saveTravel(Travel travel){
        try{
            return travelDao.save(travel);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Travel> findTravelOrderByDate(String p_id){
        return travelDao.findByPersonIdOrderByDate(p_id);
    }
}
