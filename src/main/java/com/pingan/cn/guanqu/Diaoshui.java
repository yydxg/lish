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
@Table(name = "diaoshui_data")
@NoArgsConstructor
@AllArgsConstructor
public class Diaoshui {
    /**
     * date	DATE	20	数据时间
     * id	INT	10	塘坝编号
     * water_diversion	FLOAT	8	调水量
     * time_input	DATE	20	数据录入时间
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String date;
    private String t_id;
    private String water_diversion;
    private String time_input;
}
