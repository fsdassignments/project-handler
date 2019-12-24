package com.sba.projecthandler.errors;

public class TaskNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3863425773864538108L;

	public TaskNotFoundException(int id) {
		super("Task not found for given id: " + id);
	}
}
