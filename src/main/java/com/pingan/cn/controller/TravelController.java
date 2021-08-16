package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Person;
import com.pingan.cn.entity.Travel;
import com.pingan.cn.service.TravelService;
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

@Api(tags = "人员活动")
@Controller
@RequestMapping(value = "/api/travel")
public class TravelController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private TravelService travelService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public @ResponseBody
    ResponseUtil saveTravel(@RequestBody Travel travel){
        Travel result = travelService.saveTravel(travel);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findTravelOrderByDate/{p_id}")
    @GetMapping(value = "/findTravelOrderByDate/{p_id}")
    @ResponseBody
    ResponseUtil findTravel(@PathVariable String p_id){
        List<Travel> travelList = travelService.findTravelOrderByDate(p_id);
        return ResponseUtil.builder().success(true).data(travelList).build();
    }

}
