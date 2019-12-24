package com.sba.projecthandler.errors;

public class ProjectNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3531326059738594295L;

	public ProjectNotFoundException() {
	}

	public ProjectNotFoundException(Integer id) {
		super("Project not found for given id " + id);
	}
}
