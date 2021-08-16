package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.DiquNames;
import com.pingan.cn.entity.Moyu;
import com.pingan.cn.service.MoyuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "魔芋")
@RestController
@RequestMapping(value = "/api/moyu")
public class MoyuController {
    private static final Logger logger = LoggerFactory.getLogger(MoyuController.class);

    @Autowired
    private MoyuService moyuService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody @Validated Moyu moyu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(FieldError fieldError:bindingResult.getFieldErrors()){
                logger.error("validate :[{}] is not pass",fieldError.getField());
            }
            return null;
        }
        Moyu result = moyuService.save(moyu);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Moyu> moyuList = moyuService.findAll();
        return ResponseUtil.builder().success(true).data(moyuList).build();
    }

    @ApiOperation(value = "findByDiqu/{diqu}")
    @GetMapping(value = "/findByDiqu/{diqu}")
    public @ResponseBody ResponseUtil findByDiqu(@PathVariable String diqu){
        DiquNames diquName = DiquNames.valueOf(diqu);
        List<Moyu> moyuList = moyuService.findByDiqu(diquName);
        return ResponseUtil.builder().success(true).data(moyuList).build();
    }

//    @ApiOperation(value = "findSuyuan/{id}")
//    @GetMapping(value = "/findSuyuan/{id}")
//    @ResponseBody
//    ResponseUtil findSuyuan(@PathVariable String id){
//        List<Moyu> moyuList = moyuService.findSuyuan(id);
//        return ResponseUtil.builder().success(true).data(moyuList).build();
//    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    boolean updateCabinet(Moyu moyu){
        return moyuService.update(moyu);
    }
}
