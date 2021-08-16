package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 飞行数据表
 */
@Data
@Entity
@Table(name = "Train")
@NoArgsConstructor
@AllArgsConstructor
public class Train {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String trainNo;
    private String trainDate;
    private String fullTrainCode;
    private String startTime;
    private String arriveTime;
    private String runTime;
    private String fromStationName;
    private String fromStationTelecode;
    private String toStationName;
    private String toStationTelecode;
    private String seatTypeName;
    private String seatMinPrice;
    private String seatYupiao;
}
