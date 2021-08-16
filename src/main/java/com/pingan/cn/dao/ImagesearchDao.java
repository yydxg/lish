package com.pingan.cn.dao;

import com.pingan.cn.entity.Imagesearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesearchDao extends JpaRepository<Imagesearch,String> {

    @Query(value = "select * from image where biaoqian LIKE CONCAT(:biaoqian,'%') and name LIKE CONCAT(:name,'%') and cloud <= (:cloud)", nativeQuery = true)
    List<Imagesearch>  findByBiaoqianAndName(@Param("biaoqian") String biaoqian, @Param("name") String name ,@Param("cloud") Integer cloud);
}
