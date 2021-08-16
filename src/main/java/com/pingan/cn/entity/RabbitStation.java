package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rabbitStation")
@NoArgsConstructor
@AllArgsConstructor
public class RabbitStation {
    //站点编号、位置GPS、颜色、保留时间、站点IP、与地理正北的角度差
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String stationNum;
    private String gps;
    private String color;
    private String stayTime;
    private String stationIp;
    private Double angle;
}
