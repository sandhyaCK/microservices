package com.bridgelabz.fundoonotes.dto;

/*
 *  author : Sandhya
 */

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
/* Dto class for updating LabelData*/
public class NoteUpdate {
	@NotBlank
	private Long id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private int isArchieved;
	private int isPinned;
	private int isTrashed;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime upDateAndTime;


}
