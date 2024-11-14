package com.app.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entity.PostEntity;
import com.app.blog.entity.UserEntity;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.repository.UserRepository;
import com.app.blog.request.User;
import com.app.blog.service.UserService;

@Service
public class UserImplClass implements UserService{
	
	@Autowired
	UserRepository repository;

	@Override
	public Integer createUser(User user) {		
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(user, entity);
	UserEntity userEntity=	repository.save(entity);
		return userEntity.getId();
	}

	
	@Override
	public Boolean updateUser(Integer id, User user) {
		Optional<UserEntity> optional=repository.findById(id);
	       if(optional.isPresent()) {
		UserEntity entity=optional.get();
		BeanUtils.copyProperties(user, entity);
		repository.save(entity);
		return true;
	       }
	       else {
			return false;
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<UserEntity> users=repository.findAll();
        List<User> list=new ArrayList<>();
        for(UserEntity u:users) {
        	User user=new User();
        	BeanUtils.copyProperties(u, user);
        	list.add(user);
        }
		return list;
	}

	@Override
	public UserEntity getUserById(Integer id) {
       Optional<UserEntity> optional=repository.findById(id);
       if(optional.isPresent()) {
		return optional.get();
       }
       else {
    	   return optional.orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
	}
	}

	@Override
	public void deleteUserById(Integer id) {
		Optional<UserEntity> optional=repository.findById(id);
	       if(optional.isPresent()) {
	    	   repository.deleteById(id);
	       }
	       else {
	    	  optional.orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
		}
          		
	}

}
