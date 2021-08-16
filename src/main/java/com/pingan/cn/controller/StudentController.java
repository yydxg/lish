package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ExportUtils;
import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Student;
import com.pingan.cn.service.StudentService;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api(tags = "学生")
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping(value = "/findBySemester/{semester}")
    public ResponseUtil findBySemester(@PathVariable String semester) {
        return studentService.findBySemester(semester);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id) {
        return studentService.deleteById(id);
    }

    @GetMapping(value = "/download")
    public void exportBfhtresw(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Student> lists = studentService.findAllStudents();
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = workbook.createSheet("特约单位e生活商户集团交易明细文件");
        //文件名称
        String fileName = "student"+new Date().getTime();
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 表头
        String[] headTitle = {"id", "姓名", "学期","成绩" };
        HSSFCell cell ;
        // 设置表头
        for (int iHead = 0; iHead < headTitle.length; iHead++) {
            cell = row.createCell(iHead);
            cell.setCellValue(headTitle[iHead]);
            cell.setCellStyle(cellStyle);
        }
        // 设置表格内容
        for (int iBody = 0; iBody < lists.size(); iBody++) {
            row = sheet.createRow(iBody + 1);
            Student p = lists.get(iBody);
            String[] posArray = new String[60];
            posArray[0] = p.getId();
            posArray[1] = p.getName();
            posArray[2] = p.getSemester().getName();
            posArray[3] = p.getRecords();
            for (int iArray = 0; iArray < posArray.length; iArray++) {
                row.createCell(iArray).setCellValue(posArray[iArray]);
            }
        }
        // 生成Excel文件
        ExportUtils.createFile(response, workbook, fileName);

    }

}
