package com.pingan.cn.dao;

import com.pingan.cn.entity.Qiwen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QiwenDao extends JpaRepository<Qiwen,Integer> {

    List<Qiwen> findByTime(String time);

    @Query(value = "SELECT DISTINCT time FROM qiwen ORDER BY time DESC", nativeQuery = true)
    List<String> findDistinctOrderByTimeDesc();

    @Query(value = "SELECT * FROM qiwen WHERE m = ?1 ORDER BY time", nativeQuery = true)
    List<Qiwen> findHistoryByM(String m);
}
