package com.bridgelabz.fundoonotes.dto;

/*
 *  author : Sandhya
 */

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
/*Dto class for NoteData*/
public class NoteDto {
	@NotNull
	private String title;
	@NotNull
	private String description;


}
