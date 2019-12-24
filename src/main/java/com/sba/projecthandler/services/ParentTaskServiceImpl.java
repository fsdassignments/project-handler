package com.sba.projecthandler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sba.projecthandler.errors.ParentNotFoundException;
import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.repositories.ParentTaskRepository;

@Service
@Transactional(readOnly = true)
public class ParentTaskServiceImpl implements ParentTaskService {
	
	@Autowired
	private ParentTaskRepository parentRepository;

	@Transactional
	@Override
	public List<ParentTask> list() {
		return parentRepository.findAll();
	}

	@Transactional
	@Override
	public int save(ParentTask task) throws Exception {
		parentRepository.save(task);
		return task.getParentId();
	}

	@Transactional
	@Override
	public ParentTask get(int id) throws ParentNotFoundException {
		return parentRepository.findById(id).orElseThrow(() -> new ParentNotFoundException(id));
	}
}
