package com.pingan.cn.ningbomap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Polygon;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prj_weilan")
@NoArgsConstructor
@AllArgsConstructor
public class PrjWeilan{
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;
//     @JsonIgnoreProperties({"fundamentalUnit"})
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    @Column(columnDefinition = "GEOGRAPHY(POLYGON)")
    @JsonIgnore
    @Column(name = "geometry", columnDefinition = "geometry(Polygon,4326)")
    private Polygon geometry;
}
