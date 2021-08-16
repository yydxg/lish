package com.pingan.cn.dao;

import com.pingan.cn.entity.Person2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Person2Dao extends JpaRepository<Person2,String> {

    Person2 findByName(String name);

    List<Person2> findBySourceId(String id);

    List<Person2> findByPoorLevelAndSourceId(Integer level,String id);
}
