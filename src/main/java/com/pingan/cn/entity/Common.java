package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * 一个通用的存储数据的接口表
 */
@Data
@Entity
@Table(name = "common")
@NoArgsConstructor
@AllArgsConstructor
public class Common {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String module;
    private String v1;//字段唯一，一般放置名称，如用户名
    private String v2;
    private String v3;
    private String v4;
    private String v5;
    private String v6;
    private String v7;
    private String v8;
    private String v9;
    private String v10;
    private String v11;
    private String v12;
    private String v13;
    private String v14;
    private String v15;
    private String v16;


    public Common(String module, String v1, String v7) {
        this.module = module;
        this.v1 = v1;
        this.v7 = v7;
    }
}
