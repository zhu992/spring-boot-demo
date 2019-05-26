package com.zwy.dao;

import com.zwy.dao.mapper.UserMapper;
import com.zwy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zwy on 2019/5/26.
 */
@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public Integer insertUsers(List<User> users){
        return userMapper.insertBatch(users);
    }

    public Integer updateUsers(List<User> users){
        return userMapper.updateBatch(users);
    }
}
