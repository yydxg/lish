package com.pingan.cn.dao;

import com.pingan.cn.entity.Qiya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface QiyaDao extends JpaRepository<Qiya,Integer> {

    List<Qiya> findByTime(String time);

    @Query(value = "SELECT DISTINCT time FROM qiya ORDER BY time DESC", nativeQuery = true)
    List<String> findDistinctOrderByTimeDesc();

    @Query(value = "SELECT * FROM qiya WHERE m = ?1 ORDER BY time", nativeQuery = true)
    List<Qiya> findHistoryByM(String m);
}
