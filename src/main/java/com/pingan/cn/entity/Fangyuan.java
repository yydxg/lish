package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Fangyuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String lnglat;

    private String username;
    private String description;
    private String quxian;
    private String address;
    private String area;
    private String sale;
    private String mean;
    private String building;
    private String phone;
    private String note;
}

