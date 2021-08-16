package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 一个通用的存储数据的接口表
 */
@Data
@Entity
@Table(name = "jiangshui")
@NoArgsConstructor
@AllArgsConstructor
public class Jiangshui {

//    @Id
//    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "idGenerator")
//    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double w;
    private String s;//字段唯一，一般放置名称，如用户名
    private Double r1;
    private Integer l;
    private String m;
    private Double j;
    private String h;
    private String color;
    private String time;
}
