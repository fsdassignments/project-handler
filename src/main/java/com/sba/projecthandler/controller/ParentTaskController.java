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

import com.sba.projecthandler.convertors.ParentTaskConverter;
import com.sba.projecthandler.errors.ParentNotFoundException;
import com.sba.projecthandler.services.ParentTaskService;
import com.sba.projecthandler.vo.TaskVO;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class ParentTaskController {
	@Autowired
	private ParentTaskService parentTaskService;

	@GetMapping("/parent/tasks")
	public ResponseEntity<List<TaskVO>> list() {
		return ResponseEntity.ok().body(ParentTaskConverter.toDtos(parentTaskService.list()));
	}
	
	@GetMapping("/parent/tasks/{id}")
	public ResponseEntity<TaskVO> get(@PathVariable("id") int id) throws ParentNotFoundException {
		return ResponseEntity.ok().body(ParentTaskConverter.toDto(parentTaskService.get(id)));
	}
	
	@PostMapping("/parent/tasks")
	public ResponseEntity<TaskVO> save(@RequestBody TaskVO task) throws Exception {
		int id = parentTaskService.save(ParentTaskConverter.toPojo(task));
		task.setTaskId(id);
		return ResponseEntity.ok().body(task);
	}
}
