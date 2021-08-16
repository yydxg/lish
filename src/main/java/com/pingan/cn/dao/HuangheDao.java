package com.pingan.cn.dao;

import com.pingan.cn.entity.Huanghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HuangheDao extends JpaRepository<Huanghe,Integer> {

    @Query(value = "select * from equipment where num in ?1",nativeQuery = true)
    List<Huanghe> findByNums(List<Integer> nums);

    @Query(value = "select * from huanghe where f4 like CONCAT('%',?1,'%') " +
            "and f5 like CONCAT('%',?2,'%') ",
            nativeQuery = true)
    List<Huanghe> findByF4LikeAndF5Like(String f4,String f5);
}
