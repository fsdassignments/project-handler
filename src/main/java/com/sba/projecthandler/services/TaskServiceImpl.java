package com.sba.projecthandler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sba.projecthandler.errors.TaskNotFoundException;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.repositories.TaskRepository;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	@Override
	public int save(Task task) throws Exception {
		task = taskRepository.save(task);
		return task.getTaskId();
	}

	@Transactional
	@Override
	public Task update(int id, Task task) throws TaskNotFoundException {
		return taskRepository.save(task);
	}

	@Transactional
	@Override
	public List<Task> list() {
		return taskRepository.findAll();
	}

	@Transactional
	@Override
	public Task get(int id) throws TaskNotFoundException {
		return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}

}
