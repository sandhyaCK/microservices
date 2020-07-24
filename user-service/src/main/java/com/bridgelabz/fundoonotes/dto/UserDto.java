package com.bridgelabz.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@Getter
@Setter
@AllArgsConstructor
/* Dto class for Registration */
public class UserDto {
	private String name;
	private String email;
	private String password;
	private String mobileNumber;

}
