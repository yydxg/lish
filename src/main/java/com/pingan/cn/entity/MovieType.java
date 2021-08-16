package com.pingan.cn.entity;

public enum  MovieType {
    comedy("comedy"),warfare("warfare"),action("Action"),love("love");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MovieType(String name) {
        this.name = name;
    }
}
