package com.saga.cleanindia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.cleanindia.payloads.JwtAuthRequest;
import com.saga.cleanindia.payloads.JwtAuthResponse;
import com.saga.cleanindia.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin 
public class AuthController {
	
	@Autowired
	private JwtTokenHelper helper;
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.detailsService.loadUserByUsername(request.getUsername());
		
		String token = this.helper.generateToken(userDetails);
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try
		{
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e)
		{
			System.out.println("Invalid Details");
			
			throw new Exception("Invalid username or password");
		}
	}
}
