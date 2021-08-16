package com.pingan.cn.dao;

import com.pingan.cn.entity.Xiangduishidu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiangduishiduDao extends JpaRepository<Xiangduishidu,Integer> {

    List<Xiangduishidu> findByTime(String time);

    @Query(value = "SELECT DISTINCT time FROM xiangduishidu ORDER BY time DESC", nativeQuery = true)
    List<String> findDistinctOrderByTimeDesc();

    @Query(value = "SELECT * FROM xiangduishidu WHERE m = ?1 ORDER BY time", nativeQuery = true)
    List<Xiangduishidu> findHistoryByM(String m);
}
