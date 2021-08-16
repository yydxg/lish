package com.pingan.cn.dao;

import com.pingan.cn.entity.Equipment;
import com.pingan.cn.entity.Fangyuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FangyuanDao extends JpaRepository<Fangyuan,Integer> {
    List<Fangyuan> findByUsername(String username);
}
