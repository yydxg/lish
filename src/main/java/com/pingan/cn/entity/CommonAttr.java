package com.pingan.cn.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 一个通用的存储数据的接口表
 */
@Data
@Entity
@Table(name = "commonAttr")
public class CommonAttr implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String v1;

    private String v2;

    private String v3;

    private String v4;

    private String v5;

    private String v6;

}
