package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Imagesearch;
import com.pingan.cn.service.ImagesearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

@Api(tags = "行动")
@RestController
@RequestMapping(value = "/api/imagesearch")
public class ImagesearchController {
    private static final Logger logger = LoggerFactory.getLogger(ImagesearchController.class);

    @Autowired
    private ImagesearchService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Imagesearch action){

        Imagesearch result = actionService.save(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Imagesearch action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Imagesearch> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "find/{id}")
    @GetMapping(value = "/find/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Imagesearch action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByBq/{biaoqian}/{name}/{dates}/{cloud}")
    @GetMapping(value = "/findByBq/{biaoqian}/{name}/{dates}/{cloud}")
    public @ResponseBody ResponseUtil findByBq(@PathVariable String biaoqian,@PathVariable String name,@PathVariable String dates,@PathVariable Integer cloud){
        biaoqian = URLDecoder.decode(biaoqian);
        System.out.println(biaoqian);
        List<Imagesearch> actions = actionService.findByBq(biaoqian,name,dates,cloud);
        return ResponseUtil.builder().success(true).data(actions).build();
    }

    @ApiOperation(value = "delete/{id}")
    @GetMapping(value = "/delete/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }
}
