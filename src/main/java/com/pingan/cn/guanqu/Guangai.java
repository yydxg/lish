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
@Table(name = "guangai_data")
@NoArgsConstructor
@AllArgsConstructor
public class Guangai {
    /**
     * date	DATE	20	数据时间
     * tid	INT	10	塘坝编号
     * water_requirement	FLOAT	8	每亩灌溉需水量
     * type	CHAR	8	作物类型
     * time_input	DATE	20	数据录入时间
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String t_id;
    private String date;
    private String water_requirement;
    private String type;
    private String time_input;
}
