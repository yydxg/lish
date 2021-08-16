package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Equipment;
import com.pingan.cn.entity.Fangyuan;
import com.pingan.cn.service.EquipmentService;
import com.pingan.cn.service.FangyuanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "房源")
@RestController
@RequestMapping(value = "/api/fangyuan")
public class FangyuanController {
    @Autowired
    private FangyuanService fangyuanService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Fangyuan equipment) {
        return fangyuanService.save(equipment);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return fangyuanService.findAll();
    }

    @GetMapping(value = "/find/{username}")
    public ResponseUtil findByUsername(@PathVariable String username) {
        return fangyuanService.findByUsername(username);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable Integer id) {
        return fangyuanService.deleteById(id);
    }
}
