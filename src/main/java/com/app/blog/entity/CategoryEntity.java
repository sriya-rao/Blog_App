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
@Table(name = "category_table")
public class CategoryEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cat_id")
	private Integer id;
	
	@Column(name = "cat_name")
    private String categoryName;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<PostEntity> posts=new ArrayList<>();
}
