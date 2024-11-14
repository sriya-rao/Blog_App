package com.app.blog.service;

import java.util.List;

import com.app.blog.entity.PostEntity;
import com.app.blog.request.Post;
import com.app.blog.response.PostResponse;

public interface PostService {

	
	public Integer createPost(Post post,Integer catId,Integer userId);
	
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	
	public Integer updatepost(Post post);
	
	public void deletePost(Integer id);
	
	public List<Post> getPostsByCategory(Integer catId);
	
	public PostEntity getPostById(Integer id);
	
	public List<Post> getPostsByUser(Integer userId);
	
	public List<Post> searchPosts(String key);
}
