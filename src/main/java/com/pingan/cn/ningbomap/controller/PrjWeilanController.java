package com.pingan.cn.ningbomap.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.guanqu.DiaoshuiService;
import com.pingan.cn.ningbomap.entity.PrjWeilan;
import com.pingan.cn.ningbomap.service.PrjWeilanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Api(tags = "land")
@RestController
@RequestMapping(value = "/api/prjweilan")
public class PrjWeilanController {
    private static final Logger logger = LoggerFactory.getLogger(PrjWeilanController.class);

    @Value("${dataserver.folder}")
    private String FILELoction;

    @Autowired
    private PrjWeilanService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody PrjWeilan action){
        PrjWeilan result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "save2")
    @PostMapping(value = "/save2")
    public ResponseUtil save(){
        PrjWeilan result = actionService.saveAction2();
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "upload")
    @PostMapping(value = "/upload")
    public ResponseUtil upload(@RequestParam("file") MultipartFile file){
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = FILELoction;
        // 文件重命名，防止重复
        fileName = filePath + UUID.randomUUID() + fileName;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        String result = "";
        try {
            // 保存到服务器中
            file.transferTo(dest);
            result = "上传成功！";
        } catch (Exception e) {
            e.printStackTrace();
            result = "上传失败！";
        }
        return ResponseUtil.builder().success(true).data(result).build();
    }



    /*@ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Diaoshui action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Diaoshui> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Diaoshui action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody String[] ids){
        boolean action = actionService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }*/
}
