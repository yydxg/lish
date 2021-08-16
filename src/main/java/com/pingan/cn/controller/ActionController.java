package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Action;
import com.pingan.cn.entity.DiquNames;
import com.pingan.cn.entity.Moyu;
import com.pingan.cn.service.ActionService;
import com.pingan.cn.service.MoyuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "行动")
@RestController
@RequestMapping(value = "/api/action")
public class ActionController {
    private static final Logger logger = LoggerFactory.getLogger(ActionController.class);

    @Autowired
    private ActionService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Action action){

        Action result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Action action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Action> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Action action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }
}
