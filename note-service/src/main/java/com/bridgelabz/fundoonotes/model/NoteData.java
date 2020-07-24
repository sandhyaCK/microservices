package com.bridgelabz.fundoonotes.model;

/*
 *  author : Sandhya
 */

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table
/*Entity Class for Note*/
public class NoteData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long 	noteId;

	private String title;

	private String description;

	private int isArchieved;

	private int isPinned;

	private int isTrashed;

	private LocalDateTime createdDateAndTime;

	private LocalDateTime upDateAndTime;

	private String colour;

	private LocalDateTime reminder;
	private Long userId;
	
		// TODO Auto-generated method stub
		
	}

