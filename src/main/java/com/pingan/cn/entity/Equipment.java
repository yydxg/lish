package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer num;//编号

    private String name;//名称

    private Integer capacity;//可容纳人数

    private String occupancy;//占用情况

    private Integer state = 0;//设备状态，0，未被使用；1，被使用

    private Integer isApply = 0; //是否已被申请，0，未被申请，1，被申请
}
