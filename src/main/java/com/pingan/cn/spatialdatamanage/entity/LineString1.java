package com.pingan.cn.spatialdatamanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Point;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lineString1")
@NoArgsConstructor
@AllArgsConstructor
public class LineString1 {
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
    @Column(name = "geometry", columnDefinition = "geometry(LineString,4326)")
    private org.geolatte.geom.LineString geometry;
//    private Polygon geometry;
}
