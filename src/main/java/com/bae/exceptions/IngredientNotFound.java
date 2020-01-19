package com.bae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This ingredient does not exist")
public class IngredientNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8750846831532717396L;



}
