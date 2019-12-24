package com.sba.projecthandler.services;

import java.util.List;

import com.sba.projecthandler.errors.ParentNotFoundException;
import com.sba.projecthandler.model.ParentTask;


public interface ParentTaskService {
	List<ParentTask> list();
	
	int save(ParentTask task) throws Exception;
	
	ParentTask get(int id) throws ParentNotFoundException;
}
