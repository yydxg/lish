package com.pingan.cn.entity;

public enum SexType {
    MEN("男"),WOMEN("女");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SexType(String name) {
        this.name = name;
    }
}
