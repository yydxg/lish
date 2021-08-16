package com.pingan.cn.dao;

import com.pingan.cn.entity.PigDisease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PigDiseaseDao extends JpaRepository<PigDisease,String> {

    @Query(value = "select b from PigDisease b where b.year=:year and b.month=:month and b.binming = :binming")
    Page<PigDisease> findsByDateAndBinmingPageable(@Param("year") Integer year, @Param("month") Integer month,
                                           @Param("binming")String binming, Pageable pageable);

    List<PigDisease> findByYearAndMonthAndBinming(Integer year,Integer month,String binming);
    List<PigDisease> findByYearAndMonth(Integer year,Integer month);
    List<PigDisease> findByYearAndBinming(Integer year,String binming);
    List<PigDisease> findByYear(Integer year);


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
