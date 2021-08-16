package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.controller.vo.FlightVo;
import com.pingan.cn.controller.vo.TrainVo;
import com.pingan.cn.entity.Flight;
import com.pingan.cn.entity.Train;
import com.pingan.cn.service.FlightService;
import com.pingan.cn.service.TrainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/train")
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    //依赖注入service
    @Autowired
    private TrainService trainService;

    //通用保存接口，传入实体
    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Train train){
        Train result = trainService.saveAction(train);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody Train train){
        return ResponseUtil.builder().success(true).data(trainService.update(train)).build();
    }

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Train> actionsList = trainService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    //通用id查找
    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Train train = trainService.findById(id);
        return ResponseUtil.builder().success(true).data(train).build();
    }

    @ApiOperation(value = "findByCondition")
    @PostMapping(value = "/findByCondition")
    public @ResponseBody ResponseUtil findByCondition(@RequestBody TrainVo trainVo){
        String from = trainVo.getFromStationName();
        String to = trainVo.getToStationName();
        String date = trainVo.getTrainDate();
        System.out.println(trainVo.toString());
        List<Train> trains = trainService.findByConditions(from,to,date);
        List<String> trainNos = trainService.findDistinct(from,to,date);
        Map<String,List> maps = new HashMap<>();
        maps.put("trains",trains);
        maps.put("trainNos",trainNos);
        return ResponseUtil.builder().success(true).data(maps).build();
    }

}
