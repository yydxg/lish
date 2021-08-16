package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "image")
public class Imagesearch implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String year;

    @Column
    private String points;

    @Column
    private String sensorType;

    @Column
    private String serviceType;

    @Column
    private String trackType;

    @Column
    private String productNum;

    @Column
    private String biaoqian;

    @Column
    private Integer cloud;
}
