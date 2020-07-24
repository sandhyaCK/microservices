package com.bridgelabz.fundoonotes.configuration;

/*
 *  author : Sandhya
 */

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration

public class ApplicationConfig {
@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}
@Bean
public RestTemplate getRestTemplate() {
	return new RestTemplate();
}	
	
}
