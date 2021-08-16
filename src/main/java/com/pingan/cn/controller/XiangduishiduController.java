package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Jiangshui;
import com.pingan.cn.entity.Xiangduishidu;
import com.pingan.cn.service.JiangshuiService;
import com.pingan.cn.service.XiangduishiduService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "气象-降水")
@RestController
@RequestMapping(value = "/api/xiangduishidu")
public class XiangduishiduController {
    @Autowired
    private XiangduishiduService xiangduishiduService;

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return xiangduishiduService.findAll();
    }

    @GetMapping(value = "/findDistinctOrderByTimeDesc")
    public ResponseUtil findDistinctOrderByTimeDesc(){
        return ResponseUtil.builder().success(true).data(xiangduishiduService.findDistinctOrderByTimeDesc()).build();
    }

    @GetMapping(value = "/findByTime/{time}")
    public ResponseUtil findList(@PathVariable String time ) {
        List<Xiangduishidu> xiangduishidus = xiangduishiduService.findByTime(time);
        return ResponseUtil.builder().success(true).data(xiangduishidus).build();
    }

    @GetMapping(value = "/findHistory/{m}")
    public ResponseUtil findHistory(@PathVariable String m ) {
        return ResponseUtil.builder().success(true).data(xiangduishiduService.findHistoryByM(m)).build();
    }
}
