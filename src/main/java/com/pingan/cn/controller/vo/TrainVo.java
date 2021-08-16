package com.pingan.cn.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainVo {
    private String fromStationName;
    private String toStationName;
    private String trainDate;
}
