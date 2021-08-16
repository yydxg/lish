package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.ActionDao;
import com.pingan.cn.entity.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("actionService")
public class ActionService {
    @Autowired
    private ActionDao actionDao;

    public Action saveAction(Action person){
        Action save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Action action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Action> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Action oldAction = actionDao.findById(action.getId()).get();
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

    public List<Action> findAll(){
        return actionDao.findAll();
    }

    public Action findById(String id){
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
}
