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
@Table(name = "shuiwei_data_input")
@NoArgsConstructor
@AllArgsConstructor
public class Shuiwei {
    /**
     * date	DATE	20	数据时间
     * id	INT	10	水库、塘坝编号
     * water_level	FLOAT	8	水位
     * time_input	DATE	20	数据录入时间
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String date;
    private String s_t_num;
    private String water_level;
    private String time_input;
}
