package com.example.testdemo.mybatis.mapper;

import com.example.testdemo.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    
    List<User> selectUserList(Map<String, Object> map);
    
    User checkUser(@Param("userId")String userId, @Param("password")String password);
}