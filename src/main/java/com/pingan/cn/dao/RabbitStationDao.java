package com.pingan.cn.dao;

import com.pingan.cn.entity.RabbitStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RabbitStationDao extends JpaRepository<RabbitStation,String> {

    @Query(value = "select * from common where module = ?1 " +
            "and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    public List<RabbitStation> findByV2(String module, String v2);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
            "and if(?3 != 'ALL',v5 = ?3, 1=1)", nativeQuery = true)
    List<RabbitStation> findByV2AndV5(String module, String v2, String v5);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    List<RabbitStation> findByModuleAndV2(String module, String v2);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
            "and if(?3 != 'ALL',v3 = ?3, 1=1)", nativeQuery = true)
    List<RabbitStation> findByV2AndV3(String module, String v2, String v3);

}
