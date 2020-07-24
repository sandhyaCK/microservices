package com.bridgelabz.fundoonotes.configuration;

/*
 *  author : Sandhya
 */

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class ApplicationConfig {

@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}
@Bean
public RestTemplate getRestTemplate() {
	return new RestTemplate();
}
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return   WebClient.builder();}
	}
