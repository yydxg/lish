package com.pingan.cn.controller.neo;


public class Person {
    private String name;
    private Integer age;

    public static String source = "亚当夏娃";

    public Person() {
        Object obj = new Object();
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void say(){
        System.out.printf("你好");
    }

    private void secret(){
        System.out.printf("每个人都有自己的秘密,不能说出去哦。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
