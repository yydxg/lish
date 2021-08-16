package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.CommonDao;
import com.pingan.cn.entity.Common;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("commonService")
public class CommonService {
    @Autowired
    private CommonDao commonDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Common saveAction(Common common){
        Common save = null;
        try {
            return commonDao.save(common);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Common common){
        if(common == null || StrUtil.isBlank(common.getId())){
            return  false;
        }
        Optional<Common> byId = commonDao.findById(common.getId());
        if (byId.isPresent()){
            Common oldCommon = commonDao.findById(common.getId()).get();
            BeanUtils.copyProperties(common,oldCommon, SpringUtil.getNullPropertyNames(common));
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

    public List<Common> findAll(){
        return commonDao.findAll();
    }

    public Common findById(String id){
        return commonDao.findById(id).get();
    }

    //根据模块进行查找
    public List<Common> findByModule(String module){
        return commonDao.findByModule(module);
    }

    public Common findByV1(String v1){
        return commonDao.findByV1(v1);
    }

    public List<Common> findByV2(String module,String v2){
        return commonDao.findByV2(module,v2);
    }

    public List<Common> findByV4(String module,String v4){
        return commonDao.findByV4(module,v4);
    }

    public List<String> findDistinctV5ByV4(String module,String v4){
        return commonDao.findDistinctV5ByV4(module,v4);
    }

    public List<String> findDistinctV2ByV4(String module,String v4){
        return commonDao.findDistinctV2ByV4(module,v4);
    }

    public List<Common> findByModuleAndV1(String module,String v1){
        return commonDao.findByModuleAndV1(module,v1);
    }

    public List<Common> findByV2AndV3(String module, String v2,String v3){
        try {
            List<Common> commons = commonDao.findByV2AndV3(module,v2,v3);
            return commons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Common> findV7Like(String module, String v7){
        try {
            List<Common> commons = commonDao.findByModuleAndV7Like(module,v7);
            return commons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Common> findV2AndDate(String module,String v2,String startDate,String endDate){
        try {
            List<Common> returnCommons = new ArrayList<>();
            List<Common> commons = commonDao.findByModuleAndV2(module,v2);
            for (Common common:commons) {
                String dateStr = common.getV7();
                Date date = simpleDateFormat.parse(dateStr);
                Date start = simpleDateFormat.parse(startDate);
                Date end = simpleDateFormat.parse(endDate);
                if (start.before(date) && end.after(date)){
                    returnCommons.add(common);
                }
            }
            return returnCommons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Common findByV1AndV7(String module, String v1,String v7){
        try {
            Common common = commonDao.findByModuleAndV1AndV7(module,v1,v7);
            return common;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Common> findByV2AndV5(String module, String v2,String v5){
        try {
            List<Common> commons = commonDao.findByV2AndV5(module,v2,v5);
            return commons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Common> findByModuleAndV5(String module, String v5){
        try {
            List<Common> commons = commonDao.findByModuleAndV5(module,v5);
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

    public List<Common> findByModuleAndV1AndV2AndV3(String module, String v1, String v2, String v3){
        try {
            return commonDao.findByModuleAndV1AndV2AndV3(module,v1,v2,v3);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isV1Unique(String module,String v1){
        try {
            Common common = commonDao.isV1Unique(module,v1);
            if (common != null){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }


    public List<Common> findModule1AndV1NotInModule2AndV2(String module1,String module2){
        return commonDao.findModule1AndV1NotInModule2AndV2(module1,module2);
    }

    public List<Common> findModule1AndV1InModule2AndV2(String module1,String module2){
        return commonDao.findModule1AndV1InModule2AndV2(module1,module2);
    }

    public List<Common> findByModuleAndV4GreaterThanZero(String module){
        return commonDao.findByModuleAndV4GreaterThanZero(module);
    }

    public void updateV4(String id,String v4){
        commonDao.updateV4(id,v4);
    }

    public List<Common> getAllByModuleAndV3(String module,String v3){
        return commonDao.findByModuleAndV3(module,v3);
    }

}
