package com.bae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This category does not exist")
public class CategoryNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4021801055951535160L;
	

}
