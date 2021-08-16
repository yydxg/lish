package com.pingan.cn.dao;

import com.pingan.cn.entity.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommonDao extends JpaRepository<Common,String> {

    public Common findByV1(String v1);

    @Query(value = "select * from common where module = ?1 and v1 = ?2 ", nativeQuery = true)
    public Common isV1Unique(String module,String v1);

    @Query(value = "select * from common where module = ?1 " +
            "and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    public List<Common> findByV2(String module,String v2);

    @Query(value = "select * from common where module = ?1 " +
            "and if(?2 != 'ALL',v4 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    public List<Common> findByV4(String module,String v4);

    @Query(value = "select DISTINCT(c.v5) from common c where module = ?1 " +
            "and if(?2 != 'ALL',v4 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    public List<String> findDistinctV5ByV4(String module,String v4);

    @Query(value = "select DISTINCT(c.v2) from common c where module = ?1 " +
            "and if(?2 != 'ALL',v4 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    public List<String> findDistinctV2ByV4(String module,String v4);

    List<Common> findByModule(String module);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
            "and if(?3 != 'ALL',v5 = ?3, 1=1)", nativeQuery = true)
    List<Common> findByV2AndV5(String module,String v2,String v5);

    List<Common> findByModuleAndV5(String module,String v5);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1)", nativeQuery = true)
    List<Common> findByModuleAndV2(String module,String v2);

    List<Common> findByModuleAndV1(String module,String v1);

    @Query(value = "select * from common where module = ?1 and if(?2 != 'ALL',v2 like CONCAT('%',?2,'%') ,1=1) " +
            "and if(?3 != 'ALL',v3 = ?3, 1=1)", nativeQuery = true)
    List<Common> findByV2AndV3(String module,String v2,String v3);

    //module 为user。登录接口
    Common findByModuleAndV1AndV7(String module,String v1,String v7);

    List<Common> findByModuleAndV7Like(String module,String v7);
    // 判断是否打卡
    List<Common> findByModuleAndV1AndV2AndV3(String module, String v1,String v2,String v3);

    @Query(value = "select * from common c where c.module = ?1 and c.v1 not in (SELECT DISTINCT v2 from common where module = ?2)", nativeQuery = true)
    List<Common> findModule1AndV1NotInModule2AndV2(String module1,String module2);

    @Query(value = "select * from common c where c.module = ?1 and c.v1 in (SELECT DISTINCT v2 from common where module = ?2)", nativeQuery = true)
    List<Common> findModule1AndV1InModule2AndV2(String module1,String module2);

    @Query(value = "select * from common c where c.module = ?1 and c.v4 > 0", nativeQuery = true)
    List<Common> findByModuleAndV4GreaterThanZero(String module);

    @Transactional
    @Modifying
    @Query(value = "UPDATE common set v4 = ?2 WHERE id = ?1", nativeQuery = true)
    void updateV4(String id,String v4);

    List<Common> findByModuleAndV3(String module,String v3);
}
