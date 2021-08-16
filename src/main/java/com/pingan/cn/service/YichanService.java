package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.ActionDao;
import com.pingan.cn.dao.YichanDao;
import com.pingan.cn.entity.Action;
import com.pingan.cn.entity.Yichan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("yichanService")
public class YichanService {
    @Autowired
    private YichanDao yichanDao;

    public Yichan saveAction(Yichan yichan){
        Yichan save = null;
        try {
            return yichanDao.save(yichan);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Yichan yichan){
        if(yichan == null || StrUtil.isBlank(yichan.getId())){
            return  false;
        }
        Optional<Yichan> byId = yichanDao.findById(yichan.getId());
        if (byId.isPresent()){
            Yichan oldAction = yichanDao.findById(yichan.getId()).get();
            BeanUtils.copyProperties(yichan,oldAction, SpringUtil.getNullPropertyNames(yichan));
            try {
                yichanDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Yichan> findAll(){
        return yichanDao.findAll();
    }

    public Yichan findById(String id){
        return yichanDao.findById(id).get();
    }

    public boolean deleteById(String id){
        try {
            yichanDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Yichan> findByNameAndCategory(String name, String category){
        if (category.equals("ALL")){
            return yichanDao.findByName(name);
        }else {
            return yichanDao.findByNameAndCategory(name,category);
        }
    }
}
