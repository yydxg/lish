package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Common;
import com.pingan.cn.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通用接口rest层， 对内隐藏，对外扩展，对字段只增不减，不做修改，以便保持通用性，注意字段语义
@Api(tags = "通用保存,用于鱼塘管理系统|")
@RestController
@RequestMapping(value = "/api/v1/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    //依赖注入service
    @Autowired
    private CommonService commonService;

    //通用保存接口，传入实体
    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Common common){
        Common result = commonService.saveAction(common);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody Common common){
        return ResponseUtil.builder().success(true).data(commonService.update(common)).build();
    }

    //更新v4
    @ApiOperation(value = "updateV4/{id}/{v4}")
    @GetMapping(value = "/updateV4/{id}/{v4}")
    public ResponseUtil updateV4(@PathVariable String id,@PathVariable String v4){
        commonService.updateV4(id,v4);
        return ResponseUtil.builder().success(true).data(true).build();
    }

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Common> actionsList = commonService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    //通用id查找
    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Common common = commonService.findById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    //通用查找模块下所有数据接口，经常使用
    @ApiOperation(value = "findByModule/{module}")
    @GetMapping(value = "/findByModule/{module}")
    public @ResponseBody ResponseUtil findByModule(@PathVariable String module){
        List<Common> common = commonService.findByModule(module);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV1/{v1}")
    @GetMapping(value = "/findByV1/{v1}")
    public @ResponseBody ResponseUtil findByV1(@PathVariable String v1){
        Common common = commonService.findByV1(v1);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByModuleAndV1/{module}/{v1}")
    @GetMapping(value = "/findByModuleAndV1/{module}/{v1}")
    public @ResponseBody ResponseUtil findByModuleAndV1(@PathVariable String module, @PathVariable String v1){
        List<Common> common = commonService.findByModuleAndV1(module,v1);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV2/{module}/{v2}")
    @GetMapping(value = "/findByV2/{module}/{v2}")
    public @ResponseBody ResponseUtil findByV2(@PathVariable String module,@PathVariable String v2){
        List<Common> common = commonService.findByV2(module,v2);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    @ApiOperation(value = "findByV4/{module}/{v4}")
    @GetMapping(value = "/findByV4/{module}/{v4}")
    public @ResponseBody ResponseUtil findByV4(@PathVariable String module,@PathVariable String v4){
        System.out.println(module+v4);
        List<Common> common = commonService.findByV4(module,v4);
        List<String> v5Distinct = commonService.findDistinctV5ByV4(module,v4);
        List<String> v2Distinct = commonService.findDistinctV2ByV4(module,v4);
        Map<String,Object> newmap = new HashMap<>();
        newmap.put("v5Distinct",v5Distinct);
        newmap.put("v2Distinct",v2Distinct);
        newmap.put("common",common);
        return ResponseUtil.builder().success(true).data(newmap).build();
    }

    @ApiOperation(value = "findByV4_/{module}/{v4}")
    @GetMapping(value = "/findByV4_/{module}/{v4}")
    public @ResponseBody ResponseUtil findByV4_(@PathVariable String module,@PathVariable String v4){
        List<Common> commons;
        if("all".equals(v4)){
            commons = commonService.findByV4(module,"");
        }else {
            commons = commonService.findByV4(module,v4);
        }
        return ResponseUtil.builder().success(true).data(commons).build();
    }

    //通用按id删除接口
    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean common = commonService.deleteById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }


    /**
     *     /fish/users模块
     */
    @ApiOperation(value = "findByV2AndV5/{module}/{v2}/{v5}")
    @GetMapping(value = "/findByV2AndV5/{module}/{v2}/{v5}")
    public @ResponseBody ResponseUtil findByV2AndV5(@PathVariable String module,@PathVariable String v2,@PathVariable String v5){
        List<Common> common = commonService.findByV2AndV5(module,v2,v5);
        return ResponseUtil.builder().success(true).data(common).build();
    }
    //查找所有模块下v5的xx
    @ApiOperation(value = "findByModuleAndV5/{module}/{v5}")
    @GetMapping(value = "/findByModuleAndV5/{module}/{v5}")
    public @ResponseBody ResponseUtil findByV2AndV5(@PathVariable String module,@PathVariable String v5){
        List<Common> common = commonService.findByModuleAndV5(module,v5);
        return ResponseUtil.builder().success(true).data(common).build();
    }
    //登录校验
    @ApiOperation(value = "findByV1AndV7")
    @PostMapping(value = "/findByV1AndV7")
    public @ResponseBody ResponseUtil findByV1AndV7(@RequestBody Common common){
        String module = common.getModule();
        String v1 = common.getV1();
        String v7 = common.getV7();
        Common reback = commonService.findByV1AndV7(module,v1,v7);
        return ResponseUtil.builder().success(true).data(reback).build();
    }


    /**
     *     /fish/yutang模块
     */
    @ApiOperation(value = "findByV2AndV3/{module}/{v2}/{v3}")
    @GetMapping(value = "/findByV2AndV3/{module}/{v2}/{v3}")
    public @ResponseBody ResponseUtil findByV2AndV3(@PathVariable String module,@PathVariable String v2,@PathVariable String v3){
        List<Common> common = commonService.findByV2AndV3(module,v2,v3);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    /**
     *  /fish/productsPlan 模块
     */
    @ApiOperation(value = "findV7Like/{module}/{v7}/")
    @GetMapping(value = "/findV7Like/{module}/{v7}")
    public @ResponseBody ResponseUtil findV7Like(@PathVariable String module,@PathVariable String v7){
        List<Common> common = commonService.findV7Like(module,"%"+v7+"%");
        return ResponseUtil.builder().success(true).data(common).build();
    }

    /**
     *  /fish/history 模块，日期搜索
     */
    @ApiOperation(value = "findV2AndDate/{module}/{v2}/")
    @GetMapping(value = "/findV2AndDate/{module}/{v2}/{startDate}/{endDate}")
    public @ResponseBody ResponseUtil findV1AndDate(@PathVariable String module,@PathVariable String v2,
                                                    @PathVariable String startDate,@PathVariable String endDate){
        List<Common> common = commonService.findV2AndDate(module,v2,startDate,endDate);
        return ResponseUtil.builder().success(true).data(common).build();
    }

    /**
     *     fish/worktime 模块，是否打卡
     */
    @ApiOperation(value = "getHasNot")
    @PostMapping(value = "/getHasNot")
    public ResponseUtil getHasNot(@RequestBody Common common){
        List<Common> result = commonService.findByModuleAndV1AndV2AndV3(common.getModule(),common.getV1(),common.getV2(),common.getV3());
        return ResponseUtil.builder().success(true).data(result).build();
    }

    //判断某个模块下的V1是否唯一
    @ApiOperation(value = "isV1Unique/{module}/{v1}")
    @GetMapping(value = "/isV1Unique/{module}/{v1}")
    public @ResponseBody ResponseUtil isV1Unique(@PathVariable String module,@PathVariable String v1){
        boolean b = commonService.isV1Unique(module,v1);
        return ResponseUtil.builder().success(true).data(b).build();
    }

    @ApiOperation(value = "findModule1AndV1NotInModule2AndV2/{module}/{module2}")
    @GetMapping(value = "/findModule1AndV1NotInModule2AndV2/{module1}/{module2}")
    public @ResponseBody ResponseUtil findModule1AndV1NotInModule2AndV2(@PathVariable String module1,@PathVariable String module2){
        List<Common> commons = commonService.findModule1AndV1NotInModule2AndV2(module1,module2);
        return ResponseUtil.builder().success(true).data(commons).build();
    }

    @ApiOperation(value = "findModule1AndV1InModule2AndV2/{module}/{module2}")
    @GetMapping(value = "/findModule1AndV1InModule2AndV2/{module1}/{module2}")
    public @ResponseBody ResponseUtil findModule1AndV1InModule2AndV2(@PathVariable String module1,@PathVariable String module2){
        List<Common> commons = commonService.findModule1AndV1InModule2AndV2(module1,module2);
        return ResponseUtil.builder().success(true).data(commons).build();
    }

    @ApiOperation(value = "findByModuleAndV4GreaterThanZero/{module}")
    @GetMapping(value = "/findByModuleAndV4GreaterThanZero/{module}")
    public @ResponseBody ResponseUtil findByModuleAndV4GreaterThanZero(@PathVariable String module){
        List<Common> commons = commonService.findByModuleAndV4GreaterThanZero(module);
        return ResponseUtil.builder().success(true).data(commons).build();
    }

    @ApiOperation(value = "getAllByModuleAndV3/{module}/{v3}")
    @GetMapping(value = "/getAllByModuleAndV3/{module}/{v3}")
    public @ResponseBody ResponseUtil getAllByModuleAndV3(@PathVariable String module,@PathVariable String v3){
        List<Common> commons = commonService.getAllByModuleAndV3(module,v3);
        return ResponseUtil.builder().success(true).data(commons).build();
    }

}
