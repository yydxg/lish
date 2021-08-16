package com.pingan.cn.dao;

import com.pingan.cn.entity.Semester;
import com.pingan.cn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student,String> {
   List<Student> findBySemester(Semester semester);
}
