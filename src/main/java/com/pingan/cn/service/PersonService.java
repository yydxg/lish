package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.PersonDao;
import com.pingan.cn.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("personService")
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public Person savePerson(Person person){
        Person save = null;
        try {
            return personDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean updatePerson(Person person){
        if(person == null || StrUtil.isBlank(person.getId())){
            return  false;
        }
        Optional<Person> byId = personDao.findById(person.getId());
        if (byId.isPresent()){
            Person oldCabinet = personDao.findById(person.getId()).get();
            BeanUtils.copyProperties(person,oldCabinet, SpringUtil.getNullPropertyNames(person));
            try {
                personDao.saveAndFlush(oldCabinet);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Person> findAll(){
        return personDao.findAll();
    }

    public List<Person> findByCurrentCity(String currentCity){
        return personDao.findByCurrentCity(currentCity);
    }

    public List<Person> findSuyuan(String id){
        Optional<Person> personOptional = personDao.findById(id);
        Person person = null;
        if(personOptional.isPresent()){
            person = personDao.findById(id).get();
        }
        if("".equals(person.getConnectId())){
            List<Person> personList = new ArrayList<Person>();
            personList.add(person);
            return personList;
        }else {
            List<Person> personList1 = findSuyuan(person.getConnectId());
            personList1.add(person);
            return personList1;
        }
    }
}
