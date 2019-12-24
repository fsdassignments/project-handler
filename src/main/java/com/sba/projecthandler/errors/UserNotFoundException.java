package com.sba.projecthandler.errors;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6181308627297819246L;

	public UserNotFoundException(Integer id) {
		super("User not found for given id "+ id);
	}
}
