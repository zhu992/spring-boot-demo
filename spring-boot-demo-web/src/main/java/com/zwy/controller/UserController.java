package com.zwy.controller;

import com.zwy.dao.UserDao;
import com.zwy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zwy on 2019/5/26.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("/insertList")
    public Integer insertUsers(@RequestBody List<User> users) {
        return userDao.insertUsers(users);
    }

    @PostMapping("/updateList")
    public Integer updateUsers(@RequestBody List<User> users) {
        return userDao.updateUsers(users);
    }
}
