package com.pingan.cn.guanqu;

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
@Table(name = "qixiang_data_input")
@NoArgsConstructor
@AllArgsConstructor
public class Qixiang {
    /**
     * date	DATE	20	数据时间
     * st_id	CHAR	10	气象站站号
     * average_tem	FLOAT	8	日平均温度
     * max_tem	FLOAT	8	日最高温度
     * min_tem	FLOAT	8	日最低温度
     * pre	FLOAT	8	24小时降雨量
     * time_input	DATE	20	数据录入时间
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String date;
    private String st_id;
    private String average_tem;
    private String max_tem;
    private String min_tem;
    private String pre;
    private String time_input;
}
