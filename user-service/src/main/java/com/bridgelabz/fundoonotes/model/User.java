package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name = "User")
/*Entity Class for User*/
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private Long userId;
		@Column
		private String name;
		@Column
		private String email;
		@Column
		private String password;
		@Column
		private String mobileNumber;
		@Column
		private int isVerified;
		@Column
		private LocalDateTime dateTime;
}
