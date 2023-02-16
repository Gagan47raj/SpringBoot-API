package com.saga.cleanindia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.cleanindia.entities.Category;
import com.saga.cleanindia.entities.Post;
import com.saga.cleanindia.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List <Post> findByTitleContaining(String title);	
}
