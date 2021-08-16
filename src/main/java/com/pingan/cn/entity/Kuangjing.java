package com.pingan.cn.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table
public class Kuangjing {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String NF;
    @Column
    private String YF;

    @Column
    private String KDBH;
    @Column
    private String JKDM;
    @Column
    private String JKMC;
    @Column

    private String CQMC;
    @Column
    private String CQDM;
    @Column
    private String MCMC;

    @Column
    private String QJ;
    @Column
    private String MH;
    @Column
    private String DZCL;

}
