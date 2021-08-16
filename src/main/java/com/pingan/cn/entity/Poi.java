package com.pingan.cn.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "poi")
public class Poi {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String name;

    @Column
    @Lob
    private String info;

    @Column
    private Double lng;

    @Column
    private Double lat;
}
