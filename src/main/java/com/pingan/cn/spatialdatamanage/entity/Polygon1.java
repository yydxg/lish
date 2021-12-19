package com.pingan.cn.spatialdatamanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Polygon;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "polygon1")
@NoArgsConstructor
@AllArgsConstructor
public class Polygon1 {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;
    private String others;

    // mysql 库下注释
    @JsonIgnore
    @Column(name = "geometry", columnDefinition = "geometry(Polygon,4326)")
    private Polygon geometry;
}
