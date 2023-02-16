package com.saga.cleanindia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.saga.cleanindia.config.AppConstants;
import com.saga.cleanindia.payloads.ApiResponse;
import com.saga.cleanindia.payloads.PostDto;
import com.saga.cleanindia.payloads.PostResponse;
import com.saga.cleanindia.services.FileServices;
import com.saga.cleanindia.services.PostServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private FileServices fileServices;
	
	@Value("${project.image:images/}")
	private String path;

	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable int userId,
			@PathVariable int categoryId)
	{
		PostDto createPost =  this.postServices.createPost(postDto, userId, categoryId); 
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get post by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(
			@PathVariable int userId)
	{
	List<PostDto> posts = 	this.postServices.getPostsByUser(userId);
	return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get post by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(
			@PathVariable int categoryId)
	{
	List<PostDto> posts = 	this.postServices.getPostsByCategory(categoryId);
	return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	// get post by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(
			@PathVariable int postId)
	{
	PostDto post = 	this.postServices.getPostById(postId);
	return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	// get all post
	
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPost(
				@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,  required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) int pageSize,
				@RequestParam(value = "sortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
				@RequestParam(value = "sortDir", defaultValue=AppConstants.SORT_DIR, required=false) String sortDir
				)
		{
		 	PostResponse postResponse = this.postServices.getAllPost(pageNumber,pageSize, sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		}
	
	//deletePost
		
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable int postId)
	{
		this.postServices.deletePost(postId);
		return new ApiResponse("Post deleted successfully deleted !!",true);
	}
	
	//updatePost
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId)
	{
		PostDto updatePost = this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//searchPost
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords)
	{
		List<PostDto> result = this.postServices.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile imgFile,
			@PathVariable int postId
			) throws IOException{
		
		PostDto postDto = this.postServices.getPostById(postId);
		String fileName = this.fileServices.uploadImage(path, imgFile);
		
		postDto.setImageName(fileName);
		PostDto updatePost = this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//serve image
	@GetMapping(value="/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException{
		
		InputStream resource = this.fileServices.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
