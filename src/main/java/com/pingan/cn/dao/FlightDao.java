package com.pingan.cn.dao;

import com.pingan.cn.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDao extends JpaRepository<Flight,String> {

     //出发城市，到达城市，航空公司，航班号，飞机规模，用餐类型，准时率，成人票价,出发日期(service处理)
     @Query(value = "select * from flight where departure_city_name = ?1 and arrival_city_name = ?2 and " +
             "if(?3 is not null ,market_airline_name like CONCAT('%',?3,'%') ,1=1) and " +
             "if(?4 is not null , flight_no like CONCAT('%',?4,'%'), 1=1) and " +
             "if(?5 is not null ,aircraft_size = ?5 ,1=1) and " +
             "if(?6 is not null ,meal_type = ?6 ,1=1) and " +
             "if(?7 is not null ,CONVERT(arrival_punctuality,decimal(5,2)) >= ?7 , 1=1 ) and " +
             "if(?8 is not null ,CONVERT(min_adult_price,decimal(10,2)) >= ?8 , 1=1 ) and " +
             "if(?9 is not null ,CONVERT(min_adult_price,decimal(10,2)) <= ?9 , 1=1 ) ",
             nativeQuery = true)
     public List<Flight> findByConditions(String departureCityName,String arrivalCityName,String marketAirlineName,String flightNo,
                                          String aircraftSize,String mealType,Double arrivalPunctuality,Double minAdultPrice,Double maxAdultPrice);

//    @Query(value = "select * from common where module = ?1 " +
//            "and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
//    public List<Common> findByV2(String module, String v2);
//
//    List<Common> findByModule(String module);
//
//    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
//            "and if(?3 != 'ALL',v5 = ?3, 1=1)", nativeQuery = true)
//    List<Common> findByV2AndV5(String module, String v2, String v5);
//
//    List<Common> findByModuleAndV5(String module, String v5);
//
//    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
//    List<Common> findByModuleAndV2(String module, String v2);
//
//    List<Common> findByModuleAndV1(String module, String v1);
//
//    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
//            "and if(?3 != 'ALL',v3 = ?3, 1=1)", nativeQuery = true)
//    List<Common> findByV2AndV3(String module, String v2, String v3);
//
//    //module 为user。登录接口
//    Common findByModuleAndV1AndV7(String module, String v1, String v7);
//
//    List<Common> findByModuleAndV7Like(String module, String v7);
//    // 判断是否打卡
//    List<Common> findByModuleAndV1AndV2AndV3(String module, String v1, String v2, String v3);

}
