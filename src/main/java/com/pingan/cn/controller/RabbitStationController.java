package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.RabbitStation;
import com.pingan.cn.service.RabbitStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//通用接口rest层， 对内隐藏，对外扩展，对字段只增不减，不做修改，以便保持通用性，注意字段语义
@Api(tags = "rabbitMqStation")
@RestController
@RequestMapping(value = "/api/rabbitStation")
public class RabbitStationController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitStationController.class);

    //依赖注入service
    @Autowired
    private RabbitStationService commonService;

    //通用保存接口，传入实体
    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody RabbitStation common){
        RabbitStation result = commonService.saveAction(common);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody RabbitStation common){
        return ResponseUtil.builder().success(true).data(commonService.update(common)).build();
    }

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<RabbitStation> actionsList = commonService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    //通用id查找
    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        RabbitStation common = commonService.findById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV2/{module}/{v2}")
    @GetMapping(value = "/findByV2/{module}/{v2}")
    public @ResponseBody ResponseUtil findByV2(@PathVariable String module,@PathVariable String v2){
        List<RabbitStation> common = commonService.findByV2(module,v2);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    //通用按id删除接口
    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean common = commonService.deleteById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }


    /**
     *     /fish/users模块
     */
    @ApiOperation(value = "findByV2AndV5/{module}/{v2}/{v5}")
    @GetMapping(value = "/findByV2AndV5/{module}/{v2}/{v5}")
    public @ResponseBody ResponseUtil findByV2AndV5(@PathVariable String module,@PathVariable String v2,@PathVariable String v5){
        List<RabbitStation> common = commonService.findByV2AndV5(module,v2,v5);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    /**
     *     /fish/yutang模块
     */
    @ApiOperation(value = "findByV2AndV3/{module}/{v2}/{v3}")
    @GetMapping(value = "/findByV2AndV3/{module}/{v2}/{v3}")
    public @ResponseBody ResponseUtil findByV2AndV3(@PathVariable String module,@PathVariable String v2,@PathVariable String v3){
        List<RabbitStation> common = commonService.findByV2AndV3(module,v2,v3);
        return ResponseUtil.builder().success(true).data(common).build();
    }

}
