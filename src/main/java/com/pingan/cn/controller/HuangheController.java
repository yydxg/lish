package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Equipment;
import com.pingan.cn.service.EquipmentService;
import com.pingan.cn.service.HuangheService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "")
@RestController
@RequestMapping(value = "/api/huanghe")
public class HuangheController {
    @Autowired
    private HuangheService huangheService;

    @GetMapping(value = "/findList/{lng}/{lat}")
    public ResponseUtil findList(@PathVariable String lng, @PathVariable String lat) {
        return huangheService.findByConditions(lng,lat);
    }
}
