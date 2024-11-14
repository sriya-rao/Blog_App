package com.app.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.blog.entity.CategoryEntity;
import com.app.blog.entity.CommentEntity;
import com.app.blog.entity.PostEntity;
import com.app.blog.entity.UserEntity;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.request.Category;
import com.app.blog.request.Comment;
import com.app.blog.request.Post;
import com.app.blog.request.User;
import com.app.blog.response.PostResponse;
import com.app.blog.service.PostService;

@Service
public class PostImplClass implements PostService{
	
	@Autowired
	PostRepository repository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Integer createPost(Post post,Integer catId,Integer userId) {
		CategoryEntity category=categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found with id:"+catId));
		UserEntity user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
		PostEntity postEntity=new PostEntity();
		BeanUtils.copyProperties(post, postEntity);
		postEntity.setImageName("default.png");
		postEntity.setCategory(category);
		postEntity.setUser(user);
	    PostEntity newPost=	repository.save(postEntity);
		return newPost.getPostId();
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
		Page<PostEntity> pages=repository.findAll(pageable);
		List<Post> posts=getPostsDetails(pages.getContent());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
	    postResponse.setComments(null);
		return postResponse;
	}

	@Override
	public Integer updatepost(Post post) {
		System.out.println(post);
		PostEntity postEntity=repository.findById(post.getPostId()).get();
	    postEntity.setPostTitle(post.getPostTitle());
		CategoryEntity category=categoryRepository.findById(post.getCategory().getId()).orElseThrow(()->new ResourceNotFoundException("Category not found with id:"));
	    postEntity.setCategory(category);
	    postEntity.setContent(post.getContent());
	    postEntity.setDescription(post.getDescription());
		repository.save(postEntity);
		return postEntity.getPostId();
	}

	@Override
	public void deletePost(Integer id) {
		  repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post not found with id:"+id));
        repository.deleteById(id);
	}

	@Override
	public List<Post> getPostsByCategory(Integer catId) {
		CategoryEntity categoryEntity=categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		List<PostEntity> list=categoryEntity.getPosts();
		List<Post> posts=getPostsDetails(list);
		return posts;
	}

	@Override
	public PostEntity getPostById(Integer id) {
		Optional<PostEntity> optional=repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
		return optional.orElseThrow(()->new ResourceNotFoundException("Post not Found"));
		}
	}

	@Override
	public List<Post> getPostsByUser(Integer userId) {
		UserEntity userEntity=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found"));
		List<PostEntity> list=userEntity.getPosts();
		List<Post> posts=getPostsDetails(list);
		
		return posts;
	}
	
	
	public List<Post> getPostsDetails(List<PostEntity> list){
		List<Post> posts=new ArrayList<>();
		for(PostEntity l:list) {
			Post post=new Post();
			BeanUtils.copyProperties(l, post);
			Category category=new Category();
			category.setCategoryName(l.getCategory().getCategoryName());
			category.setId(l.getCategory().getId());
			post.setCategory(category);
			
			List<CommentEntity> commentsEntities=l.getComments();
			List<Comment> comments=new ArrayList<>();
			for(CommentEntity c:commentsEntities) {
				Comment comment=new Comment();
				comment.setComment(c.getComment());
				comment.setCommentId(c.getCommentId());
				comments.add(comment);
			}
			post.setComments(comments);
			

			User user=new User();
			 user.setAbout(l.getUser().getAbout());
			 user.setEmail(l.getUser().getEmail());
			 user.setId(l.getUser().getId());
			 user.setName(l.getUser().getName());
			 post.setUser(user);
			posts.add(post);
			
			
		
		}
		return posts;
	}

	@Override
	public List<Post> searchPosts(String key) {
		List<PostEntity> list=repository.searchByTitle(key);
		List<Post> posts=getPostsDetails(list);
		return posts;
	}

}
