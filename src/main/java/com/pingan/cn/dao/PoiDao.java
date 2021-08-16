package com.pingan.cn.dao;

import com.pingan.cn.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiDao extends JpaRepository<Poi,String> {
}
