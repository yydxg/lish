package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ExportUtils;
import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Kuangjing;
import com.pingan.cn.entity.Student;
import com.pingan.cn.service.KuangjingService;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api(tags = "矿井")
@RestController
@RequestMapping(value = "/api/kuang")
public class KuangjingController {
    @Autowired
    private KuangjingService kuangjingService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Kuangjing student) {
        return kuangjingService.save(student);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return kuangjingService.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id) {
        return kuangjingService.deleteById(id);
    }

    @GetMapping(value = "/download")
    public void exportBfhtresw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Kuangjing> lists = kuangjingService.findAllKuangjings();
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = workbook.createSheet("特约单位e生活商户集团交易明细文件");
        //文件名称
        String fileName = "kuang"+new Date().getTime();
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 表头
        String[] headTitle = {"年份", "月份", "块段编号","井口代码","井口名称", "采区名称", "采区代码","煤层名称", "倾角", "煤厚","地质储量" };
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
            Kuangjing p = lists.get(iBody);
            String[] posArray = new String[60];
            posArray[0] = p.getNF();
            posArray[1] = p.getYF();
            posArray[2] = p.getKDBH();
            posArray[3] = p.getJKDM();
            posArray[4] = p.getJKMC();
            posArray[5] = p.getCQMC();
            posArray[6] = p.getCQDM();
            posArray[7] = p.getMCMC();
            posArray[8] = p.getQJ();
            posArray[9] = p.getMH();
            posArray[10] = p.getDZCL();
            for (int iArray = 0; iArray < posArray.length; iArray++) {
                row.createCell(iArray).setCellValue(posArray[iArray]);
            }
        }
        // 生成Excel文件
        ExportUtils.createFile(response, workbook, fileName);
    }
}
