package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.dao.UserDao;
import com.pingan.cn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    //注册
    public ResponseUtil register(User user){
        try {
            User result = userDao.saveAndFlush(user);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //登录查找
    public ResponseUtil findByUsernameAndPassword(String username, String password){
        try {
            User result = userDao.findByUsernameAndPassword(username,password);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<User> users = userDao.findAll();
            return ResponseUtil.success(users);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            userDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }


}
