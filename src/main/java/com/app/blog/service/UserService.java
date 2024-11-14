package com.app.blog.service;

import java.util.List;

import com.app.blog.entity.UserEntity;
import com.app.blog.request.User;

public interface UserService {

	public Integer createUser(User user);
	
	
	public Boolean updateUser(Integer id,User user);
	
	public List<User> getAllUsers();
	
	public UserEntity getUserById(Integer id);
	
	public void deleteUserById(Integer id);
	
}
