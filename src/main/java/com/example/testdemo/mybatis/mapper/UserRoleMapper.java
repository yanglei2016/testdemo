package com.example.testdemo.mybatis.mapper;

import com.example.testdemo.mybatis.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
    
    List<UserRole> selectUserRoleByUserId(String userId);
    
    int deleteUserRoleByUserId(String userId);
    
    int deleteUserRoleByRoleId(Integer roleId);
    
    int batchInsert(List<UserRole> userRoleList);
    
}