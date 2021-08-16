package com.pingan.cn.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightVo {

    private String departureCityName;
    private String arrivalCityName;
    private String marketAirlineName;
    private String flightNo;
    private String aircraftSize;
    private String mealType;
    private Double arrivalPunctuality;
    private Double minAdultPrice;
    private Double maxAdultPrice;

    private String startDate;
    private String endDate;
}
