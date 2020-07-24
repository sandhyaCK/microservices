package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data

/*Entity Class for User*/
public class User {
	
		private Long userId;
		
		private String name;
		
		private String email;
		
		private String password;
		
		private String mobileNumber;
		
		private int isVerified;
		
		private LocalDateTime dateTime;
}
