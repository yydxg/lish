package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.StudentDao;
import com.pingan.cn.entity.Semester;
import com.pingan.cn.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public ResponseUtil save(Student student){
        try {
            Student result = studentDao.saveAndFlush(student);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findBySemester(String semester){
        try {
            Semester semesterType = Semester.valueOf(semester);
            List<Student> result = studentDao.findBySemester(semesterType);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<Student> result = studentDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Student> findAllStudents(){
        return studentDao.findAll();
    }

    public ResponseUtil deleteById(String id){
        try {
            studentDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
