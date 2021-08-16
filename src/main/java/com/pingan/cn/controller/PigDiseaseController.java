package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Common;
import com.pingan.cn.entity.PigDisease;
import com.pingan.cn.service.CommonService;
import com.pingan.cn.service.PigDiseaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "pigDisease")
@RestController
@RequestMapping(value = "/api/pigDisease")
public class PigDiseaseController {
    private static final Logger logger = LoggerFactory.getLogger(PigDiseaseController.class);

    //依赖注入service
    @Autowired
    private PigDiseaseService pigDiseaseService;

    //通用保存接口，传入实体
    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody PigDisease pigDisease){
        PigDisease result = pigDiseaseService.save(pigDisease);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody PigDisease pigDisease){
        return ResponseUtil.builder().success(true).data(pigDiseaseService.update(pigDisease)).build();
    }

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<PigDisease> actionsList = pigDiseaseService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    //通用id查找
    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        PigDisease common = pigDiseaseService.findById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    //通用按id删除接口
    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean result = pigDiseaseService.deleteById(id);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findByDateAndBinming/{date}/{binming}")
    @GetMapping(value = "/findByDateAndBinming/{date}/{binming}")
    public @ResponseBody ResponseUtil findByDateAndBinming(@PathVariable String date, @PathVariable String binming){
        List<PigDisease> result = pigDiseaseService.findByDateAndBinming(date,binming);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    /**
     *     fish/worktime 模块，是否打卡
     */
//    @ApiOperation(value = "getHasNot")
//    @PostMapping(value = "/getHasNot")
//    public ResponseUtil getHasNot(@RequestBody Common common){
//        List<Common> result = commonService.findByModuleAndV1AndV2AndV3(common.getModule(),common.getV1(),common.getV2(),common.getV3());
//        return ResponseUtil.builder().success(true).data(result).build();
//    }

}
