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
@Table(name = "zhamen_info_input")
@NoArgsConstructor
@AllArgsConstructor
public class Zhamen {

    /**
     * id	INT	10	闸门编号
     * position	CHAR	40	位置
     * flow_velocity	FLOAT	8	过水速度
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;
    private String position;
    private String sk_num; // 所属水库的编号
    private String flow_velocity;


}
