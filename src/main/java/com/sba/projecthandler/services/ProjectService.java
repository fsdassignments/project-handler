package com.sba.projecthandler.services;

import java.util.List;

import com.sba.projecthandler.errors.ProjectNotFoundException;
import com.sba.projecthandler.model.Project;


public interface ProjectService {
	List<Project> list();
	
	int save(Project project) throws Exception;
	
	Project update(int id, Project project) throws ProjectNotFoundException;
	
	Project get(int id) throws ProjectNotFoundException;
}
