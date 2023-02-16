package com.saga.cleanindia.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;
	
	@NotBlank
	private String  title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String imageName;
	
	@NotBlank
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
}
