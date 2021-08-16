package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Person;
import com.pingan.cn.entity.Person2;
import com.pingan.cn.service.Person2Service;
import com.pingan.cn.service.PersonService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Api(tags ="贫困人员")
@Controller
@RequestMapping(value = "/api/profile")
public class Person2Controller {
    private static final Logger logger = LoggerFactory.getLogger(Person2Controller.class);

    @Autowired
    private Person2Service personService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public @ResponseBody
    ResponseUtil savePerson(@RequestBody @Validated Person2 person, BindingResult bindingResult){//@RequestParam(name="fileField",required=false) MultipartFile fileField
        if(bindingResult.hasErrors()){
            for(FieldError fieldError:bindingResult.getFieldErrors()){
                logger.error("validate :[{}] is not pass",fieldError.getField());
            }
            return null;
        }
        Person2 result = personService.savePerson(person);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findAll")
    @PostMapping(value = "/findAll")
    @ResponseBody
    ResponseUtil findAll(){
        List<Person2> personList = personService.findAll();
        return ResponseUtil.builder().success(true).data(personList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    ResponseUtil findById(@PathVariable String id){
        Person2 person = personService.findById(id);
        return ResponseUtil.builder().success(true).data(person).build();
    }

    @ApiOperation(value = "findByName/{name}")
    @GetMapping(value = "/findByName/{name}")
    @ResponseBody
    ResponseUtil findByName(@PathVariable String name){
        Person2 person = personService.findByName(name);
        if(person==null){
            return ResponseUtil.builder().success(true).build();
        }else if(person.getSourceId()==null||person.getSourceId().equals("")){
            return ResponseUtil.builder().success(true).data(person).build();
        }else  {
            List<Person2> list = new ArrayList<>();
            list.add(person);
            Person2 person2 = personService.findById(person.getSourceId());
            list.add(person2);
            return ResponseUtil.builder().success(true).data(list).build();
        }
    }

    @ApiOperation(value = "findBySourceId/{id}")
    @GetMapping(value = "/findBySourceId/{id}")
    @ResponseBody
    ResponseUtil findBySourceId(@PathVariable String id){
        List<Person2> person = personService.findBySourceId(id);
            return ResponseUtil.builder().success(true).data(person).build();
    }

    @ApiOperation(value = "fetchByType/{type}")
    @GetMapping(value = "/fetchByType/{id}/{type}")
    @ResponseBody
    ResponseUtil findBySourceId(@PathVariable String id,@PathVariable String type){
        List<Person2> person = new ArrayList<>();
        switch (type){
            case "ALL":
                person = personService.findBySourceId(id);
                break;
            case "0":
            case "1":
            case "2":
            case "3":
                person = personService.findBySourceIdAndType(id,Integer.parseInt(type));
                break;
            default:
                break;
        }
        return ResponseUtil.builder().success(true).data(person).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    @ResponseBody
    boolean updateCabinet(Person2 person){
        return personService.updatePerson(person);
    }

    @ApiOperation(value = "delete")
    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    ResponseUtil delete(@PathVariable String id){
        return personService.deleteById(id);
    }
}
