package com.pingan.cn.guanqu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShuiweiDao extends JpaRepository<Shuiwei,String> {

    @Query(value = "select * from shuiwei_data_input where s_t_num = ?1", nativeQuery = true)
    public List<Shuiwei> findBySTNum(String STnum);

}
