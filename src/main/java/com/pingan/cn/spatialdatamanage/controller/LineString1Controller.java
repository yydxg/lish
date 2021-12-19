package com.pingan.cn.spatialdatamanage.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.spatialdatamanage.dto.LineString1Dto;
import com.pingan.cn.spatialdatamanage.dto.Polygon1Dto;
import com.pingan.cn.spatialdatamanage.entity.LineString1;
import com.pingan.cn.spatialdatamanage.entity.Polygon1;
import com.pingan.cn.spatialdatamanage.service.LineString1Service;
import com.pingan.cn.spatialdatamanage.service.Polygon1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "spatialdatamanage空间数据管理")
@RestController
@RequestMapping(value = "/api/lineString1")
public class LineString1Controller {
    private static final Logger logger = LoggerFactory.getLogger(LineString1Controller.class);

    @Autowired
    private LineString1Service actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody LineString1Dto action){
        LineString1 result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        LineString1 action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

}
