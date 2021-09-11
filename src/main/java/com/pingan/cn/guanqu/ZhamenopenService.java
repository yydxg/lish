package com.pingan.cn.guanqu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ZhamenopenService")
public class ZhamenopenService {
    @Autowired
    private ZhamenopenDao actionDao;

    public Zhamenopen saveAction(Zhamenopen person){
        Zhamenopen save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Zhamenopen action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Zhamenopen> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Zhamenopen oldAction = actionDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                actionDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Zhamenopen> findAll(){
        return actionDao.findAll();
    }

    public Zhamenopen findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<Zhamenopen> arrayList = new ArrayList<>();
        for (String id:ids) {
            Zhamenopen entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
