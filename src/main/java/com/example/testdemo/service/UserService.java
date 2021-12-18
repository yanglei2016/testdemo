package com.example.testdemo.service;



import com.example.testdemo.mybatis.entity.User;
import com.example.testdemo.mybatis.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface UserService {

	public User selectUser(String userId);
	
	public void insertUser(User user, String roleIds);
	
	public void updateUser(User user, String roleIds);
	
	public void deleteUser(String userId);
	
	public List<User> selectUserList(Map<String, Object> map);
	
	public List<UserRole> selectUserRoleByUserId(String userId);
	
	public User checkUser(String userId, String password);
}
