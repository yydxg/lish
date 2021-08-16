package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.CommonDao;
import com.pingan.cn.dao.PigDiseaseDao;
import com.pingan.cn.entity.Common;
import com.pingan.cn.entity.PigDisease;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("pigDiseaseService")
public class PigDiseaseService {
    @Autowired
    private CommonDao commonDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PigDiseaseDao pigDiseaseDao;

    public PigDisease save(PigDisease pigDisease){
        PigDisease save = null;
        try {
            return pigDiseaseDao.save(pigDisease);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(PigDisease pigDisease){
        if(pigDisease == null || StrUtil.isBlank(pigDisease.getId())){
            return  false;
        }
        Optional<PigDisease> byId = pigDiseaseDao.findById(pigDisease.getId());
        if (byId.isPresent()){
            PigDisease oldPigDisease = pigDiseaseDao.findById(pigDisease.getId()).get();
            BeanUtils.copyProperties(pigDisease,oldPigDisease, SpringUtil.getNullPropertyNames(pigDisease));
            try {
                pigDiseaseDao.saveAndFlush(oldPigDisease);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<PigDisease> findAll(){
        return pigDiseaseDao.findAll();
    }

    public PigDisease findById(String id){
        return pigDiseaseDao.findById(id).get();
    }

    public List<PigDisease> findDateAndBinming(String date,String binming,Integer pageNum,Integer pageSize){
        String[] date1 = date.split(",");
        Integer year = Integer.parseInt(date1[0]);
        Integer month = Integer.parseInt(date1[1]);
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<PigDisease> pages = pigDiseaseDao.findsByDateAndBinmingPageable(year,month,binming,pageable);
        return pages.getContent();
    }

    public List<PigDisease> findByDateAndBinming(String date, String binming){
        String[] date1 = date.split("-");
        Integer year = Integer.parseInt(date1[0]);
        Integer month = Integer.parseInt(date1[1]);
        if("ALL".equals(binming)){
            return pigDiseaseDao.findByYearAndMonth(year,month);
        }else {
            return pigDiseaseDao.findByYearAndMonthAndBinming(year,month,binming);
        }

    }

    public boolean deleteById(String id){
        try {
            pigDiseaseDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Common> findByModuleAndV1AndV2AndV3(String module, String v1, String v2, String v3){
        try {
            return commonDao.findByModuleAndV1AndV2AndV3(module,v1,v2,v3);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
