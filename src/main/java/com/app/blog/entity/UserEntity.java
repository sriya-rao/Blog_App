package com.app.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_entity_table")
public class UserEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_pwd")
	private String password;
	
	@Column(name = "user_about")
	private String about;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PostEntity> posts=new ArrayList<>();

}

