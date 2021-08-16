package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Person;
import com.pingan.cn.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.omg.CORBA.portable.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api(tags = "人员")
@Controller
@RequestMapping(value = "/api/person")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public @ResponseBody
    ResponseUtil savePerson(@RequestBody @Validated Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(FieldError fieldError:bindingResult.getFieldErrors()){
                logger.error("validate :[{}] is not pass",fieldError.getField());
            }
            return null;
        }
        Person result = personService.savePerson(person);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "findAll")
    @PostMapping(value = "/findAll")
    @ResponseBody
    ResponseUtil findAll(){
        List<Person> personList = personService.findAll();
        return ResponseUtil.builder().success(true).data(personList).build();
    }

    @ApiOperation(value = "findByCurrentCity/{currentCity}")
    @GetMapping(value = "/findByCurrentCity/{currentCity}")
    @ResponseBody
    ResponseUtil findByCurrentCity(@PathVariable String currentCity){
        List<Person> personList = personService.findByCurrentCity(currentCity);
        return ResponseUtil.builder().success(true).data(personList).build();
    }

    @ApiOperation(value = "findSuyuan/{id}")
    @GetMapping(value = "/findSuyuan/{id}")
    @ResponseBody
    ResponseUtil findSuyuan(@PathVariable String id){
        List<Person> personList = personService.findSuyuan(id);
        return ResponseUtil.builder().success(true).data(personList).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    boolean updateCabinet(Person person){
        return personService.updatePerson(person);
    }
}
