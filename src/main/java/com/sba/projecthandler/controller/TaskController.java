package com.sba.projecthandler.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.projecthandler.convertors.ParentTaskConverter;
import com.sba.projecthandler.convertors.TaskConverter;
import com.sba.projecthandler.errors.TaskNotFoundException;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.services.ParentTaskService;
import com.sba.projecthandler.services.TaskService;
import com.sba.projecthandler.vo.TaskVO;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private ParentTaskService parentTaskService;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<TaskVO>> list() {
		List<TaskVO> tasksList = TaskConverter.toDtos(taskService.list());
		return ResponseEntity.ok().body(tasksList);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskVO> get(@PathVariable("id") int id) throws TaskNotFoundException {
		TaskVO taskVO = TaskConverter.toDto(taskService.get(id));
		return ResponseEntity.ok().body(taskVO);
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<TaskVO> save(@RequestBody TaskVO task) throws Exception {
		Task taskToSave = TaskConverter.toPojo(task);
		int id = taskService.save(taskToSave);
		task.setTaskId(id);
		return ResponseEntity.ok().body(task);
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<TaskVO> update(@PathVariable("id") int id, @RequestBody TaskVO task)
			throws TaskNotFoundException {
		Task taskToUpdate = TaskConverter.toPojo(task);
		taskService.update(id, taskToUpdate);
		return ResponseEntity.ok().body(task);
	}
	
	@GetMapping("/tasks/search")
	public ResponseEntity<List<TaskVO>> search(@RequestParam int projectId, @RequestParam(required = false) String sortBy) {
		//get all tasks
		List<TaskVO> tasksList = TaskConverter.toDtos(taskService.list());
		
		//filter based on projectId
		List<TaskVO> projectTasks = tasksList.stream().filter(task -> projectId == task.getProjectId()).collect(Collectors.toList());
		
		//get parent task ids
		Set<Integer> parentTaskIds = projectTasks.stream().map(task -> task.getParentTaskId()).collect(Collectors.toSet());
		List<TaskVO> parentTasks = ParentTaskConverter.toDtos(parentTaskService.list().stream().filter(parentTask -> parentTaskIds.contains(parentTask.getParentId())).collect(Collectors.toList()));
		
		projectTasks.addAll(parentTasks);
		
		if(!StringUtils.isEmpty(sortBy) && sortBy.equalsIgnoreCase("startdate")) {
			Comparator<TaskVO> startDateComparator = (o1, o2)-> o1.getStartDate().compareTo(o2.getStartDate());
			projectTasks.sort(startDateComparator);
			return ResponseEntity.ok().body(projectTasks);
		} else if (!StringUtils.isEmpty(sortBy) && sortBy.equalsIgnoreCase("enddate")) {
			Comparator<TaskVO> endDateComparator = (o1, o2)-> o1.getEndDate().compareTo(o2.getEndDate());
			projectTasks.sort(endDateComparator);
			return ResponseEntity.ok().body(projectTasks);
		} else if (!StringUtils.isEmpty(sortBy) && sortBy.equalsIgnoreCase("priority")) {
			Comparator<TaskVO> priorityComparator = (o1, o2)-> o1.getPriority().compareTo(o2.getPriority());
			projectTasks.sort(priorityComparator);
			return ResponseEntity.ok().body(projectTasks);
		} else if (!StringUtils.isEmpty(sortBy) && sortBy.equalsIgnoreCase("completed")) {
			Comparator<TaskVO> priorityComparator = (o1, o2)-> o1.getPriority().compareTo(o2.getPriority());
			projectTasks.sort(priorityComparator);
			return ResponseEntity.ok().body(projectTasks);
		} else {
			return ResponseEntity.ok().body(projectTasks);
		}
	}
}
