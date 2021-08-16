package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Qiya;
import com.pingan.cn.service.QiyaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "气象-气压")
@RestController
@RequestMapping(value = "/api/qiya")
public class QiyaController {
    @Autowired
    private QiyaService qiyaService;

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return qiyaService.findAll();
    }

    @GetMapping(value = "/findDistinctOrderByTimeDesc")
    public ResponseUtil findDistinctOrderByTimeDesc(){
        return ResponseUtil.builder().success(true).data(qiyaService.findDistinctOrderByTimeDesc()).build();
    }

    @GetMapping(value = "/findByTime/{time}")
    public ResponseUtil findList(@PathVariable String time ) {
        List<Qiya> qiyas = qiyaService.findByTime(time);
        return ResponseUtil.builder().success(true).data(qiyas).build();
    }

    @GetMapping(value = "/findHistory/{m}")
    public ResponseUtil findHistory(@PathVariable String m ) {
        List<Qiya> qiyas = qiyaService.findHistoryByM(m);
        return ResponseUtil.builder().success(true).data(qiyas).build();
    }
}
