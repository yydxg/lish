package com.pingan.cn.dao;

import com.pingan.cn.entity.Jiangshui;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JiangshuiDao extends JpaRepository<Jiangshui,Integer> {

    List<Jiangshui> findByTime(String time);

    @Query(value = "SELECT DISTINCT time FROM jiangshui ORDER BY time DESC", nativeQuery = true)
    List<String> findDistinctOrderByTimeDesc();

    @Query(value = "SELECT * FROM jiangshui WHERE m = ?1 ORDER BY time", nativeQuery = true)
    List<Jiangshui> findHistoryByM(String m);
}
