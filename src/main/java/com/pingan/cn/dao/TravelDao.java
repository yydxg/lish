package com.pingan.cn.dao;

import com.pingan.cn.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelDao extends JpaRepository<Travel,String> {

    List<Travel> findByPersonIdOrderByDate(@Param("p_id") String p_id);
}
