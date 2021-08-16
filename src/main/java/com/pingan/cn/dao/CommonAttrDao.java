package com.pingan.cn.dao;

import com.pingan.cn.entity.Action;
import com.pingan.cn.entity.CommonAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonAttrDao extends JpaRepository<CommonAttr,String> {
    public CommonAttr findByV1(String v1);

    public CommonAttr findByV2(String v1);
}
