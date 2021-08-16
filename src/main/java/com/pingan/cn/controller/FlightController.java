package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.controller.vo.FlightVo;
import com.pingan.cn.entity.Common;
import com.pingan.cn.entity.Flight;
import com.pingan.cn.service.CommonService;
import com.pingan.cn.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/flight")
public class FlightController {
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    //依赖注入service
    @Autowired
    private FlightService flightService;

    //通用保存接口，传入实体
    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Flight flight){
        Flight result = flightService.saveAction(flight);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody Flight flight){
        return ResponseUtil.builder().success(true).data(flightService.update(flight)).build();
    }

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Flight> actionsList = flightService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    //通用id查找
    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Flight flight = flightService.findById(id);
        return ResponseUtil.builder().success(true).data(flight).build();
    }

    @ApiOperation(value = "findByCondition")
    @PostMapping(value = "/findByCondition")
    public @ResponseBody ResponseUtil findByCondition(@RequestBody FlightVo flightVo){
        List<Flight> flights = flightService.findByConditions(flightVo);
        return ResponseUtil.builder().success(true).data(flights).build();
    }

}
