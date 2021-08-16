package com.pingan.cn.dao;

import com.pingan.cn.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDao extends JpaRepository<Train,String> {

     //出发城市，到达城市，日期
     @Query(value = "select * from train where from_station_name like CONCAT('%',?1,'%') " +
             "and to_station_name like CONCAT('%',?2,'%') and train_date = ?3 ",
             nativeQuery = true)
     public List<Train> findByConditions(String fromStationName, String toStationName,String dateStr);

     @Query(value = "select distinct(full_train_code) from train where from_station_name like CONCAT('%',?1,'%') " +
             "and to_station_name like CONCAT('%',?2,'%') and train_date = ?3 ",
             nativeQuery = true)
     public List<String> findDistinct(String fromStationName, String toStationName,String dateStr);

}
