package com.bridgelabz.fundoonotes.model;

/*
 *  author : Sandhya
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
/*Entity Class for Label*/
public class LabelData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;
	private String name;
	private Long userId;

	
}
