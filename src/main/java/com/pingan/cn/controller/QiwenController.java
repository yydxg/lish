package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Qiwen;
import com.pingan.cn.service.QiwenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "气象-温度")
@RestController
@RequestMapping(value = "/api/qiwen")
public class QiwenController {
    @Autowired
    private QiwenService qiwenService;

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return qiwenService.findAll();
    }

    @GetMapping(value = "/findDistinctOrderByTimeDesc")
    public ResponseUtil findDistinctOrderByTimeDesc(){
        return ResponseUtil.builder().success(true).data(qiwenService.findDistinctOrderByTimeDesc()).build();
    }

    @GetMapping(value = "/findByTime/{time}")
    public ResponseUtil findList(@PathVariable String time ) {
        List<Qiwen> wenduses = qiwenService.findByTime(time);
        return ResponseUtil.builder().success(true).data(wenduses).build();
    }

    @GetMapping(value = "/findHistory/{m}")
    public ResponseUtil findHistory(@PathVariable String m ) {
        return ResponseUtil.builder().success(true).data(qiwenService.findHistoryByM(m)).build();
    }
}
