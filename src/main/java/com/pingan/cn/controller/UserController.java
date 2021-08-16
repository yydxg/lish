package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.User;
import com.pingan.cn.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Api(tags = "用户")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResponseUtil register(@RequestBody User user){
        User user1 = new User();
        return userService.register(user);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id){
        return userService.deleteById(id);
    }

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseUtil login(@PathVariable String username,@PathVariable String password){
        return userService.findByUsernameAndPassword(username,password);
    }
}
