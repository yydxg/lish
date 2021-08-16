package com.pingan.cn.dao;

import com.pingan.cn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    User findByUsernameAndPassword(String username,String password);
}
