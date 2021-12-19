package com.pingan.cn.spatialdatamanage.dao;

import com.pingan.cn.ningbomap.entity.Point1;
import com.pingan.cn.spatialdatamanage.entity.LineString1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineString1Dao extends JpaRepository<LineString1,String> {
}
