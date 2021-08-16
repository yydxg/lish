package com.pingan.cn.entity;

public enum DiquNames {
    YUNNAN("云南"),SICUAN("四川"),GUANGXI("广西");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DiquNames(String name) {
        this.name = name;
    }
}
