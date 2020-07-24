package com.bridgelabz.fundoonotes.exception;


/*
 *  author : Sandhya
 */

import lombok.Getter;
@Getter
/*Exception class for custom exception*/
public class LabelException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String message;

	public LabelException(String message) {
		super(message);
		this.message = message;
	}
}
