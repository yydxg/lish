package com.pingan.cn.entity;

//学期
public enum Semester {
    DAYI("大一"),DAER("大二"),DASAN("大三"),DASI("大四");

    private String name;

    Semester(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
