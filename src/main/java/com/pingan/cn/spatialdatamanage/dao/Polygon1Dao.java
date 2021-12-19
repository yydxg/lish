package com.pingan.cn.spatialdatamanage.dao;

import com.pingan.cn.spatialdatamanage.entity.LineString1;
import com.pingan.cn.spatialdatamanage.entity.Polygon1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Polygon1Dao extends JpaRepository<Polygon1,String> {
}
