package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.JZJingDian;
import com.pingan.cn.service.JZJingDianService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "景点")
@RestController
@RequestMapping(value = "/api/jzJingDian")
public class JZJingDianController {
    @Autowired
    private JZJingDianService jzJingDianService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody JZJingDian jzJingDian){
        return jzJingDianService.save(jzJingDian);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseUtil findById(@PathVariable String id){
        return jzJingDianService.findById(id);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseUtil findByName(@PathVariable String name){
        return jzJingDianService.findByName(name);
    }

}
