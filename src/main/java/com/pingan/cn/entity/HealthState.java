package com.pingan.cn.entity;

public enum HealthState {
    YES("健康"),SICK("确诊"),CASE("疑似");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    HealthState(String name) {
        this.name = name;
    }
}
