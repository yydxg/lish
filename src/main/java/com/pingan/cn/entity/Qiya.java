package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * 一个通用的存储数据的接口表
 */
@Data
@Entity
@Table(name = "qiya")
@NoArgsConstructor
@AllArgsConstructor
public class Qiya {

//    @Id
//    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "idGenerator")
//    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double w;
    private String s;
    private Double p;
    private Integer l;
    private String m;
    private Double j;
    private String h;
    private String color;
    private String time;
}
