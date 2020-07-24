package com.bridgelabz.fundoonotes.controller;

/*
 *  author : Sandhya
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDto;

import com.bridgelabz.fundoonotes.dto.PasswordDto;

import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.User;

import com.bridgelabz.fundoonotes.response.Response;

import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.JwtGenerator;

@RestController
//@RequestMapping("/User")
@CrossOrigin("*")
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="user-service")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private JwtGenerator generator;

	/* API for User registration */
	
	@PostMapping("user/Registration")

	public ResponseEntity<Response> registration(@RequestBody UserDto information) throws Exception {
		System.out.println(information.getEmail());
		boolean reg = service.register(information);
		if (reg) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("Registration Successfull", 200, information));
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
				.body(new Response("Already existing user", 400, information));
	}

	/* API for user login */
	@PostMapping("user/Login")
	public ResponseEntity<Response> Login(@RequestBody LoginDto information) {
		User user = service.login(information);
		if (user != null) {
			String token = generator.jwtToken(user.getUserId());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("login successfull", 200, token));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Login failed", 400, user));

	}

	/* API for verifying the token generated for the email */
	@GetMapping("verify/{token}")
	public ResponseEntity<Response> verify(@PathVariable("token") String token) throws Exception {
		boolean verification = service.verify(token);
		if (verification) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("verified", 200, token));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 400, token));
	}

	/* API for reset the forget password */
	@PostMapping("user/forgetPassword")
	public ResponseEntity<Response> forgetPassword(@RequestParam("email") String email) {
		System.out.println(email);
		boolean result = service.isUserExist(email);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("User exixt", 200, email));

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("User doesnt exist with given mail id", 200, email));

	}

	/* API for update the user information for the specific token */
	@PutMapping("user/update{token}")
	public ResponseEntity<Response> update(@PathVariable("token") String token,
			@RequestBody PasswordDto passwordUpdate) {
		boolean result = service.update(passwordUpdate, token);
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("password updated successfully", 200, token));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("password doesn't matched", 402, token));

	}

	/* API for retrieving the all the user information */
	@GetMapping("user/allUsers")
	public ResponseEntity<Response> getAllUsers() {
		List<User> users = service.getUsers();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Listed all user information", 200, users));
	}

	/* API to get the single user information */
	@GetMapping("user/singleUser/{token}")
	public ResponseEntity<Response> singleUser(@PathVariable("token") String token) throws Exception {
		User user = service.getSingleUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("User is", 200, user));
	}

	@GetMapping("user/singleUserByEmail")
	public ResponseEntity<Response> singleUserByEmail(@RequestHeader("email") String email) throws Exception {
		User user = service.getSingleUseByEmailr(email);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("User is", 200, user));
	}

}