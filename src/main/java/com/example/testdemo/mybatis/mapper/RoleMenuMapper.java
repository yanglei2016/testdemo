package com.example.testdemo.mybatis.mapper;

import com.example.testdemo.mybatis.entity.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
    
    List<RoleMenu> selectListByRoleId(Integer roleId);
    
    int deleteRoleMenuByRoleId(Integer roleId);
    
    int deleteRoleMenuByMenuIds(Map<String,Object> paramMap);
    
    int batchInsert(List<RoleMenu> roleMenuList);
    
}