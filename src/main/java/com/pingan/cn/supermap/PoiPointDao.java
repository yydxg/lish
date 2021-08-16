package com.pingan.cn.supermap;

import com.pingan.cn.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiPointDao extends JpaRepository<PoiPoint,String> {
    PoiPoint findByName(String name);
    List<PoiPoint> findByType(String type);
}
