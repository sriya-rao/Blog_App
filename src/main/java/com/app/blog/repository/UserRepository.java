package com.app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
