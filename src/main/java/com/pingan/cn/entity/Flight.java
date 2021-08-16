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
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String flightNo;
    private String sequenceNo;
    private String marketAirlineCode;
    private String marketAirlineName;
    private String departureCountryName;
    private String departureProvinceId;
    private String departureCityId;
    private String departureCityCode;
    private String departureCityName;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportShortName;
    private String departureTerminal;
    private String arrivalCountryName;
    private String arrivalProvinceId;
    private String arrivalCityId;
    private String arrivalCityCode;
    private String arrivalCityName;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportShortName;
    private String arrivalTerminal;
    private String duration;
    private String transferDuration;
    private String stopList;
    private String aircraftCode;
    private String aircraftName;
    private String aircraftSize;
    private String departureDateTime;
    private String arrivalDateTime;
    private String leakedVisaTagSwitch;
    private String trafficType;
    private String mealType;
    private String arrivalPunctuality;
    private String highLightPlaneNo;
    private String minAdultPrice;

}
