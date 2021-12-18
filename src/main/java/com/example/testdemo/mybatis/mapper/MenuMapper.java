package com.example.testdemo.mybatis.mapper;

import com.example.testdemo.mybatis.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectMenuList();
    
    List<Menu> selectTreeMenuList(String userId);
    
    int deleteMenuByMenuIds(Map<String,Object> paramMap);
    
    List<Menu> selectMenuByUserId(String userId);
}