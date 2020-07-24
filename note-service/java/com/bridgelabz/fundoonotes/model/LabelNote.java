package com.bridgelabz.fundoonotes.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class LabelNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lableNoteId;
   private Long labelId;
   private Long noteId;
}
