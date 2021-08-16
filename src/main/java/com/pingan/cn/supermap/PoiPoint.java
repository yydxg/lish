package com.pingan.cn.supermap;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PoiPoint")
public class PoiPoint {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(unique = true)
    private String name;
    private String resource;
    private String type;
    private String content;
    private String lnglats;
}
