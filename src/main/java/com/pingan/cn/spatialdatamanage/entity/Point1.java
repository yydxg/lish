package com.pingan.cn.ningbomap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "point1")
@NoArgsConstructor
@AllArgsConstructor
public class Point1{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;
    private String others;
//     @JsonIgnoreProperties({"fundamentalUnit"})
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    @Column(columnDefinition = "GEOGRAPHY(POLYGON)")

    // mysql 库下注释
    @JsonIgnore
    @Column(name = "geometry", columnDefinition = "geometry(Point,4326)")
    private Point geometry;
//    private Polygon geometry;
}
