package com.pingan.cn.controller.neo;

public class Student extends Person{
    private String className; //班级
    private String major; //主修专业

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private void love(){
        System.out.println("心中都喜欢着班花");
    }

    public Teacher leader(){
        Teacher teacher = new Teacher();
        teacher.setName("王大力");
        return teacher;
    }

}
