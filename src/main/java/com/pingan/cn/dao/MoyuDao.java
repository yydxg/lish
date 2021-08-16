package com.pingan.cn.dao;

import com.pingan.cn.entity.DiquNames;
import com.pingan.cn.entity.Moyu;
import com.pingan.cn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoyuDao extends JpaRepository<Moyu,String> {

    List<Moyu> findByDiqu(DiquNames diqu);
}
