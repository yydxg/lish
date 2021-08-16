package com.pingan.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pg_user") //pg库不能用user表
public class User implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String className;//班级
    @Column
    private String major;//主修专业
    @Column
    private String role;//用户的角色

    private String phoneNum;//手机号码

    private String email;//email

    private String age;
}
