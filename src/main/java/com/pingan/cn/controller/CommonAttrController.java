package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Action;
import com.pingan.cn.entity.CommonAttr;
import com.pingan.cn.service.CommonAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "通用保存")
@RestController
@RequestMapping(value = "/api/common")
public class CommonAttrController {
    private static final Logger logger = LoggerFactory.getLogger(CommonAttrController.class);

    @Autowired
    private CommonAttrService commonAttrService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody CommonAttr common){

        CommonAttr result = commonAttrService.saveAction(common);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody CommonAttr common){
        return ResponseUtil.builder().success(true).data(commonAttrService.update(common)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<CommonAttr> actionsList = commonAttrService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        CommonAttr common = commonAttrService.findById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV1/{v1}")
    @GetMapping(value = "/findByV1/{v1}")
    public @ResponseBody ResponseUtil findByV1(@PathVariable String v1){
        CommonAttr common = commonAttrService.findByV1(v1);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV2/{v2}")
    @GetMapping(value = "/findByV2/{v2}")
    public @ResponseBody ResponseUtil findByV2(@PathVariable String v2){
        CommonAttr common = commonAttrService.findByV2(v2);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean common = commonAttrService.deleteById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }
}
