package com.zwy.dao.mapper;

import com.zwy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zwy on 2019/5/26.
 */
public interface UserMapper {


    /**
     * 单个对象可以不用@Param做参数名映射
     */
    int insert(User user);

    /**
     * 集合对象需要做参数映射
     */
    int insertBatch(@Param("users") List<User> users);

    int insertBatch2(@Param("users") List<User> users);


    int updateBatch(@Param("users") List<User> users);

    int updateBatch2(@Param("users") List<User> users);
}
