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
@Table(name = "zhamen_open_data")
@NoArgsConstructor
@AllArgsConstructor
public class Zhamenopen {
    /**
     * date	DATE	20	数据时间
     * id	INT	10	闸门编号
     * opening_duration	FLOAT	8	闸门开启时长
     * time_input	DATE	20	数据录入时间
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String date;
    private String z_id;
    private String opening_duration;
    private String time_input;
}
