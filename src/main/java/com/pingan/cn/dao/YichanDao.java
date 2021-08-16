package com.pingan.cn.dao;

import com.pingan.cn.entity.Yichan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YichanDao extends JpaRepository<Yichan,String> {

     @Query(value = "select * from yichan where name_en like CONCAT('%',?1,'%') " +
             "and category like CONCAT('%',?2,'%') ",
             nativeQuery = true)
     public List<Yichan> findByNameAndCategory(String name, String category);

     @Query(value = "select * from yichan where name_en like CONCAT('%',?1,'%') ",
             nativeQuery = true)
     public List<Yichan> findByName(String name);
}
