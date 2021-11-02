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
@Table(name = "shuiku_info_input")
@NoArgsConstructor
@AllArgsConstructor
public class Shuiku {
    /**
     * id	INT	10	水库编号
     * name	CHAR	20	水库名称
     * position	CHAR	40	位置
     * area	FLOAT	8	面积
     * gate	CHAR	8	拥有闸门
     * max_water	FLOAT	8	最大水位
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;
    private String name;
    private String position;
    private String area;
    private String gate;
    private String max_water;
}
