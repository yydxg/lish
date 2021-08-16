package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "huanghe")
@NoArgsConstructor
@AllArgsConstructor
public class Huanghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String f5;
    private String f6;
    private String f7;
    private String f8;
    private String f9;
    private String f10;
    private String f11;
    private String f12;
    private String f13;
    private String f14;
    private String f15;
    private String f16;
    private String f17;
    private String f18;
    private String f19;
    private String f20;
    private String f21;
    private String f22;
    private String f23;
    private String f24;
    private String f25;
    private String f26;
    private String f27;
    private String f28;
    private String f29;
    private String f30;
    private String f31;
    private String f32;
    private String f33;
    private String f34;
    private String f35;
    private String f36;
    private String f37;
    private String f38;
    private String f39;

    @Override
    public String toString() {
        return "Huanghe{" +
                "id=" + id +
                ", f1='" + f1 + '\'' +
                ", f2='" + f2 + '\'' +
                ", f3='" + f3 + '\'' +
                ", f4='" + f4 + '\'' +
                ", f5='" + f5 + '\'' +
                ", f6='" + f6 + '\'' +
                ", f7='" + f7 + '\'' +
                ", f8='" + f8 + '\'' +
                ", f9='" + f9 + '\'' +
                ", f10='" + f10 + '\'' +
                ", f11='" + f11 + '\'' +
                ", f12='" + f12 + '\'' +
                ", f13='" + f13 + '\'' +
                ", f14='" + f14 + '\'' +
                ", f15='" + f15 + '\'' +
                ", f16='" + f16 + '\'' +
                ", f17='" + f17 + '\'' +
                ", f18='" + f18 + '\'' +
                ", f19='" + f19 + '\'' +
                ", f20='" + f20 + '\'' +
                ", f21='" + f21 + '\'' +
                ", f22='" + f22 + '\'' +
                ", f23='" + f23 + '\'' +
                ", f24='" + f24 + '\'' +
                ", f25='" + f25 + '\'' +
                ", f26='" + f26 + '\'' +
                ", f27='" + f27 + '\'' +
                ", f28='" + f28 + '\'' +
                ", f29='" + f29 + '\'' +
                ", f30='" + f30 + '\'' +
                ", f31='" + f31 + '\'' +
                ", f32='" + f32 + '\'' +
                ", f33='" + f33 + '\'' +
                ", f34='" + f34 + '\'' +
                ", f35='" + f35 + '\'' +
                ", f36='" + f36 + '\'' +
                ", f37='" + f37 + '\'' +
                ", f38='" + f38 + '\'' +
                ", f39='" + f39 + '\'' +
                '}';
    }
}
