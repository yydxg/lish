package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.MoyuDao;
import com.pingan.cn.dao.MoyuDao;
import com.pingan.cn.entity.DiquNames;
import com.pingan.cn.entity.Moyu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("MoyuService")
public class MoyuService {
    @Autowired
    private MoyuDao moyuDao;

    public Moyu save(Moyu Moyu){
        try {
            return moyuDao.save(Moyu);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Moyu Moyu){
        if(Moyu == null || StrUtil.isBlank(Moyu.getId())){
            return  false;
        }
        Optional<Moyu> byId = moyuDao.findById(Moyu.getId());
        if (byId.isPresent()){
            Moyu oldCabinet = moyuDao.findById(Moyu.getId()).get();
            BeanUtils.copyProperties(Moyu,oldCabinet, SpringUtil.getNullPropertyNames(Moyu));
            try {
                moyuDao.saveAndFlush(oldCabinet);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Moyu> findAll(){
        return moyuDao.findAll();
    }

    public List<Moyu> findByDiqu(DiquNames diqu){
        return moyuDao.findByDiqu(diqu);
    }

    /*public List<Moyu> findSuyuan(String id){
        Optional<Moyu> MoyuOptional = moyuDao.findById(id);
        Moyu Moyu = null;
        if(MoyuOptional.isPresent()){
            Moyu = moyuDao.findById(id).get();
        }
        if("".equals(Moyu.getConnectId())){
            List<Moyu> MoyuList = new ArrayList<Moyu>();
            MoyuList.add(Moyu);
            return MoyuList;
        }else {
            List<Moyu> MoyuList1 = findSuyuan(Moyu.getConnectId());
            MoyuList1.add(Moyu);
            return MoyuList1;
        }
    }*/
}
