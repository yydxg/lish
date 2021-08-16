package com.pingan.cn.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 魔芋实体
 */
@Data
@Entity
@Table(name = "moyu")
public class Moyu {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    //海拔 温度 湿度 ph 地区 光照强度
    @Column
    private Double lng;//经度
    private Double lat;//维度
    private Double height;//海拔
    private Double tem;//温度
    private Double humidity;//湿度
    private Double ph;
    private Double beam;//光照强度

    private Integer month;//月份

    private DiquNames diqu;
}
