package com.sba.projecthandler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sba.projecthandler.convertors.ProjectConverter;
import com.sba.projecthandler.errors.ProjectNotFoundException;
import com.sba.projecthandler.services.ProjectService;
import com.sba.projecthandler.vo.ProjectVO;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/projects")
	public ResponseEntity<List<ProjectVO>> list() {
		return ResponseEntity.ok().body(ProjectConverter.toDtos(projectService.list()));
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<ProjectVO> get(@PathVariable("id") int id) throws ProjectNotFoundException {
		return ResponseEntity.ok().body(ProjectConverter.toDto(projectService.get(id)));
	}
	
	@PostMapping("/projects")
	public ResponseEntity<ProjectVO> save(@RequestBody ProjectVO project) throws Exception {
		int id = projectService.save(ProjectConverter.toPojo(project));
		project.setProjectId(id);
		return ResponseEntity.ok().body(project);
	}
}
