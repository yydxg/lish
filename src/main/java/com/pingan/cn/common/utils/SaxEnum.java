package com.pingan.cn.common.utils;

public enum SaxEnum {
    MEN("男"),WOMEN("女");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SaxEnum(String name) {
        this.name = name;
    }
}
