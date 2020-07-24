package com.bridgelabz.fundoonotes.dto;

/*
 *  author : Sandhya
 */

import lombok.Getter;

import lombok.Data;
import lombok.Setter;
@Data
@Getter
@Setter
/*Dto class for LabelData*/
public class LabelDto {
String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
	
}
