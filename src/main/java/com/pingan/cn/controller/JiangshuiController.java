package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Jiangshui;
import com.pingan.cn.entity.Qiya;
import com.pingan.cn.service.JiangshuiService;
import com.pingan.cn.service.QiyaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "气象-降水")
@RestController
@RequestMapping(value = "/api/jiangshui")
public class JiangshuiController {
    @Autowired
    private JiangshuiService jiangshuiService;

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return jiangshuiService.findAll();
    }

    @GetMapping(value = "/findDistinctOrderByTimeDesc")
    public ResponseUtil findDistinctOrderByTimeDesc(){
        return ResponseUtil.builder().success(true).data(jiangshuiService.findDistinctOrderByTimeDesc()).build();
    }

    @GetMapping(value = "/findByTime/{time}")
    public ResponseUtil findList(@PathVariable String time ) {
        List<Jiangshui> jiangshuis = jiangshuiService.findByTime(time);
        return ResponseUtil.builder().success(true).data(jiangshuis).build();
    }

    @GetMapping(value = "/findHistory/{m}")
    public ResponseUtil findHistory(@PathVariable String m ) {
        return ResponseUtil.builder().success(true).data(jiangshuiService.findHistoryByM(m)).build();
    }
}
