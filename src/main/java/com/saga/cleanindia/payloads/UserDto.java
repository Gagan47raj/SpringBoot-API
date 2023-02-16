package com.saga.cleanindia.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty(message = "Name can't be null")
	private String name;
	
	@Email(message = "Email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 8, max = 16, message = "Password must be minimum of 8 characters and maximum of 16 characters")
	private String password;
	
	@NotEmpty
	private String phone;
	
	@NotEmpty
	private String dob;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String state;
	
	@NotNull
	private int zipcode;
	
	@NotEmpty
	private String nationalId;
}
