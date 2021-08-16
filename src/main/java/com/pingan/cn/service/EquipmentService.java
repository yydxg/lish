package com.pingan.cn.service;


import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.EquimentDao;
import com.pingan.cn.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("equipmentService")
public class EquipmentService {
    @Autowired
    private EquimentDao equimentDao;

    public ResponseUtil save(Equipment equipment){
        try {
            Equipment result = equimentDao.saveAndFlush(equipment);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<Equipment> result = equimentDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(Integer id){
        try {
            equimentDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil update(Integer id,Integer state,Integer isApply){
        try {
            equimentDao.updateStateAndIsApply(id,state, isApply);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil list(List<Integer> nums){
        try {
            List<Equipment> results = equimentDao.findByNums(nums);
            return ResponseUtil.success(results);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
