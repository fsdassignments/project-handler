package com.sba.projecthandler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sba.projecthandler.errors.ProjectNotFoundException;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.repositories.ProjectRepository;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Transactional
	@Override
	public List<Project> list() {
		return projectRepository.findAll();
	}

	@Transactional
	@Override
	public int save(Project project) throws Exception {
		project = projectRepository.save(project);
		return project.getProjectId();
	}

	@Transactional
	@Override
	public Project update(int id, Project project) throws ProjectNotFoundException {
		return projectRepository.save(project);
	}

	@Transactional
	@Override
	public Project get(int id) throws ProjectNotFoundException {
		return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
	}

}
