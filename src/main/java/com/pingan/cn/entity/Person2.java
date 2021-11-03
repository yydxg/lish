package com.pingan.cn.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person2")
public class Person2 {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;

    private String phone;

    private String code; //地区编码

    //@Lob 通常与@Basic同时使用，提高访问速度
    @Lob
    @Basic(fetch = FetchType.LAZY)
//    @Column(name=" photo", columnDefinition="longblob", nullable=true)  // mysql
    @Column(name=" photo", columnDefinition="text", nullable=true)
    private byte[] photo;

    private String address;//地址

    private Integer poorLevel;//贫困程度 0 非贫,1,2,3

    private String sourceId="";//扶贫人员id
}
