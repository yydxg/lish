package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.Person2Dao;
import com.pingan.cn.dao.PersonDao;
import com.pingan.cn.entity.Person;
import com.pingan.cn.entity.Person2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("person2Service")
public class Person2Service {
    @Autowired
    private Person2Dao person2Dao;

    public Person2 savePerson(Person2 person){
        Person2 save = null;
        try {
            return person2Dao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean updatePerson(Person2 person){
        if(person == null || StrUtil.isBlank(person.getId())){
            return  false;
        }
        Optional<Person2> byId = person2Dao.findById(person.getId());
        if (byId.isPresent()){
            Person2 oldCabinet = person2Dao.findById(person.getId()).get();
            BeanUtils.copyProperties(person,oldCabinet, SpringUtil.getNullPropertyNames(person));
            try {
                person2Dao.saveAndFlush(oldCabinet);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Person2> findAll(){
        return person2Dao.findAll();
    }

    public List<Person2> findBySourceId(String id){
        return person2Dao.findBySourceId(id);
    }

    public List<Person2> findBySourceIdAndType(String id,Integer level){
        return person2Dao.findByPoorLevelAndSourceId(level,id);
    }

    public Person2 findById(String id){
        return person2Dao.findById(id).get();
    }

    public Person2 findByName(String name){
        return person2Dao.findByName(name);
    }


    public ResponseUtil deleteById(String id){
        try {
            person2Dao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
