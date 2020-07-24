package com.bridgelabz.fundoonotes.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.PasswordDto;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.exception.UserException;

import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.response.MailObject;
import com.bridgelabz.fundoonotes.response.MailResponse;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.JwtGenerator;


@Service
public class UserServiceImplemenntation implements UserService {
	
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private BCryptPasswordEncoder encryption;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MailResponse response;
	@Autowired
	private MailObject mailObject;
	@Autowired
	private UserRepository repository;


	@Autowired
	private RestTemplate template; 


	@Transactional
	@Override
	public Boolean register(UserDto information) {
		
		User userInformation=new User();
		User user = repository.getUser(information.getEmail());
		if (user == null) {
			userInformation = modelMapper.map(information, User.class);
			userInformation.setDateTime(LocalDateTime.now());
			String epassword = encryption.encode(information.getPassword());
			userInformation.setPassword(epassword);//
			userInformation.setIsVerified(0);
			repository.save(userInformation);			
			String mailResponse =	generate.jwtToken(userInformation.getUserId());
			Response response=template.getForEntity("http://localhost:8085/sendmail/"+information.getEmail()+"/"+mailResponse,Response.class).getBody();	
			return true;
		}
		throw new UserException("user already exist");

	}


	/* Method for user login */

	@Transactional
	@Override
	public User login(LoginDto information) {
		User user = repository.getUser(information.getEmail());
		if (user != null) {
			if ((user.getIsVerified() == 1) && (encryption.matches(information.getPassword(), user.getPassword()))) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return user;
			}
		}
		return null;
	}

	public String generateToken(Long id) {
		return generate.jwtToken(id);

	}
	/* Method for verifying the user */

	@Transactional
	@Override
	public Boolean verify(String token) {
		System.out.println("id is in verification" + (Long) generate.parseJWT(token));
		Long id = (Long) generate.parseJWT(token);
		repository.verify(id);
		return true;
	}
	/* Method to check whether the user is exist or not */

	@Transactional
	@Override
	public Boolean isUserExist(String email) {
		try {
			User user = repository.getUser(email);
			if (user.getIsVerified() == 1) {
				String mailResposne = response.fromMessage("http://localhost:8080/verify/",
						generate.jwtToken(user.getUserId()));
				mailObject.setEmail(user.getEmail());
				mailObject.setSubject("verification");
				mailObject.setMessage(mailResposne);
				repository.save(user);
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new UserException("User doesn't exist with this email id");
		}

	}
	/* Method for updating the user information */

	@Transactional
	@Override
	public Boolean update(PasswordDto information, String token) {
		try {
			Long id = null;
			id = (Long) generate.parseJWT(token);
			String epassword = encryption.encode(information.getConfirmPassword());
			
				information.setConfirmPassword(epassword);
			
			return repository.update(information, id);
		} catch (Exception e) {
			throw new UserException("Invalid data ");
		}

	}
	/* Method for list out all the userIformation */

	@Transactional
	@Override
	public List<User> getUsers() {
		List<User> users = repository.getUsers();
		User user = users.get(0);
		return users;
		
	}

	/* Method to get the single userInformation */
	@Transactional
	@Override
	public User getSingleUser(String token) {
		try {
			Long id = (Long) generate.parseJWT(token);

			User user = repository.findUserById(id);


			return user;
		} catch (Exception e) {
			throw new UserException("User doesn't exist ");
		}
	}

	@Transactional
	@Override
	public User getSingleUseByEmailr(String email) {
		try {
			Long id = (Long) generate.parseJWT(email);

			User user = repository.getUser(email);


			return user;
		} catch (Exception e) {
			throw new UserException("User doesn't exist ");
		}
	}
}