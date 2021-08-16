package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Yichan;
import com.pingan.cn.service.YichanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "遗产")
@RestController
@RequestMapping(value = "/api/yichan")
public class YichanController {
    private static final Logger logger = LoggerFactory.getLogger(YichanController.class);

    @Autowired
    private YichanService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Yichan yichan){
        Yichan result = actionService.saveAction(yichan);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Yichan yichan){
        return ResponseUtil.builder().success(true).data(actionService.update(yichan)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Yichan> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Yichan yichan = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(yichan).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean yichan = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(yichan).build();
    }

    @ApiOperation(value = "findByNameAndCategory/{name}/{category}")
    @GetMapping(value = "/findByNameAndCategory/{name}/{category}")
    public @ResponseBody ResponseUtil findByNameAndCategory(@PathVariable String name,@PathVariable String category){
        List<Yichan> actionsList = actionService.findByNameAndCategory(name,category);
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }
}
