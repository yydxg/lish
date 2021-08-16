package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.CommonAttrDao;
import com.pingan.cn.entity.CommonAttr;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("commonAttrService")
public class CommonAttrService {
    @Autowired
    private CommonAttrDao commonAttrDao;

    public CommonAttr saveAction(CommonAttr common){
        CommonAttr save = null;
        try {
            return commonAttrDao.save(common);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(CommonAttr common){
        if(common == null || StrUtil.isBlank(common.getId())){
            return  false;
        }
        Optional<CommonAttr> byId = commonAttrDao.findById(common.getId());
        if (byId.isPresent()){
            CommonAttr oldCommon = commonAttrDao.findById(common.getId()).get();
            BeanUtils.copyProperties(common,oldCommon, SpringUtil.getNullPropertyNames(common));
            try {
                commonAttrDao.saveAndFlush(oldCommon);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<CommonAttr> findAll(){
        return commonAttrDao.findAll();
    }

    public CommonAttr findById(String id){
        return commonAttrDao.findById(id).get();
    }

    public CommonAttr findByV1(String v1){
        return commonAttrDao.findByV1(v1);
    }

    public CommonAttr findByV2(String v2){
        return commonAttrDao.findByV2(v2);
    }

    public boolean deleteById(String id){
        try {
            commonAttrDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
