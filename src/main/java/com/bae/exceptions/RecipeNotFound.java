package com.bae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This recipe does not exist")
public class RecipeNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 451977226456116897L;
		
}


