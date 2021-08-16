package com.pingan.cn.entity;

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
@Table(name = "PigDisease")
@NoArgsConstructor
@AllArgsConstructor
public class PigDisease {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    //年
    private Integer year;
    //月
    private Integer month;
    //市
    private String shi;
    //县
    private String xian;
    //疫点总数
    private Integer yidianzongshu;
    //易感动物数
    private Integer yigandongwushu;
    //发病数
    private Integer fabinshu;
    //病死数
    private Integer binsishu;
    //扑杀数
    private Integer bushashu;
    //销毁数
    private Integer xiaohuishu;
    //紧急免疫数
    private Integer jinjimianyishu;
    //治疗数
    private Integer zhiliaoshu;
    //疫点类别
    private String yidianleibie;
    //动物分类
    private String dongwufenlei;
    //疫点标识
    private String yidianbiaoshi;
    //养殖规模
    private String yangzhiguimo;
    //新疫点总数
    private Integer xinyidianzongshu;
    //病名
    private String binming;
    //发病动物种类
    private String fabindongwuzonglei;
}
