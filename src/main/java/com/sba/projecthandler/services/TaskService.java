package com.sba.projecthandler.services;

import java.util.List;

import com.sba.projecthandler.errors.TaskNotFoundException;
import com.sba.projecthandler.model.Task;


public interface TaskService {
	int save(Task task) throws Exception;
	
	Task update(int id, Task task) throws TaskNotFoundException;
	
	List<Task> list();
	
	Task get(int id) throws TaskNotFoundException;
}
