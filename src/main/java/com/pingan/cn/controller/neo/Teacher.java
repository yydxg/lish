package com.pingan.cn.controller.neo;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{

    private String major; //老师教的科目

    private String professor;//老师的职称

    private String words(){
        return "老师的名言警句";
    }

    public List<Student> hasStudents(){
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.setClassName("班级1");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        return students;
    }

    public Teacher() {
    }

}
