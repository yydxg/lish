package com.pingan.cn.dao;

import com.pingan.cn.entity.Action;
import com.pingan.cn.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionDao extends JpaRepository<Action,String> {

}
