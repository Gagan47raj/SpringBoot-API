package com.saga.cleanindia.services;

import java.util.List;

import com.saga.cleanindia.payloads.PostDto;
import com.saga.cleanindia.payloads.PostResponse;

public interface PostServices {

	//create
	PostDto createPost(PostDto postDto, int userId, int categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, int postId);
	
	//delete
	
	void deletePost(int postId);
	
	//getAll
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	//get
	
	PostDto getPostById(int postId);
	
	//get all post by category
	
	List<PostDto> getPostsByCategory(int categoryId);
	
	//get all post by user
	
	List<PostDto> getPostsByUser(int userId);
	
	//search post 
	List<PostDto> searchPosts(String keyword);
}
