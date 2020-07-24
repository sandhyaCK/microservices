package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordDto;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.User;

public interface UserService {

	public Boolean register(UserDto information) throws Exception;

	User login(LoginDto information);

	Boolean verify(String token);

	Boolean isUserExist(String Email);

	Boolean update(PasswordDto information, String token);

	List<User> getUsers();

	User getSingleUser(String token) throws Exception;
	public User getSingleUseByEmailr(String email);
}


