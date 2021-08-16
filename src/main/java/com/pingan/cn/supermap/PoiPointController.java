package com.pingan.cn.supermap;

import com.pingan.cn.common.utils.ResponseUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "应急|灾害|障碍区域")
@RestController
@RequestMapping(value = "/api/supermap")
public class PoiPointController {
    @Autowired
    private PoiPointService poiPointService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody PoiPoint poi){
        return poiPointService.save(poi);
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return poiPointService.findAll();
    }


    @ApiOperation(value = "clear")
    @GetMapping(value = "/clear")
    public ResponseUtil clear(){
        return poiPointService.clear();
    }

    @ApiOperation(value = "deleteById")
    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id){
        return poiPointService.delete(id);
    }

    @ApiOperation(value = "findByName")
    @GetMapping(value = "/find/{name}")
    public ResponseUtil findByName(@PathVariable String name){
        return poiPointService.findByName(name);
    }

    @ApiOperation(value = "findByNameAndType")
    @GetMapping(value = "/findByType/{type}")
    public ResponseUtil findByNameAndType(@PathVariable String type){
        return poiPointService.findByType(type);
    }
}
