package com.sba.projecthandler.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.sba.projecthandler.errors.TaskNotFoundException;
import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.services.ParentTaskService;
import com.sba.projecthandler.services.TaskService;
import com.sba.projecthandler.vo.TaskVO;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
	@InjectMocks
	private TaskController taskController;
	
	@Mock
	private TaskService taskService;
	
	@Mock
	private ParentTaskService parentTaskService;
	

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getTasks() {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		Task task1 = new Task();
		task1.setTaskId(2);
		task1.setTask("Task2");
		task1.setPriority(3);
		task1.setStatus(true);
		task1.setProject(new Project(2));
		task1.setParentTask(new ParentTask(3));
		task1.setUsers(new HashSet<>(newArrayList(new User(4), new User(5))));
		
		when(taskService.list()).thenReturn(newArrayList(task, task1));
		
		ResponseEntity<List<TaskVO>> response = taskController.list();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().size(), 2);
		verify(taskService, times(1)).list();
	}
	
	@Test
	public void getTask() throws TaskNotFoundException {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		when(taskService.get(1)).thenReturn(task);
		
		ResponseEntity<TaskVO> response = taskController.get(1);
		
		TaskVO responseVO = response.getBody();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(responseVO.getTaskId(),  new Integer(1));
		assertEquals(responseVO.getTask(), "Task1");
		assertEquals(responseVO.getPriority(), new Integer(3));
		assertEquals(responseVO.getProjectId(), new Integer(1));
		assertEquals(responseVO.getParentTaskId(), new Integer(1));
		assertEquals(responseVO.getUserIds(), newArrayList(1, 2));
		
		verify(taskService, times(1)).get(1);
	}
	
	@Test
	public void searchTask() {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		Task task1 = new Task();
		task1.setTaskId(2);
		task1.setTask("Task2");
		task1.setPriority(3);
		task1.setStatus(true);
		task1.setProject(new Project(1));
		task1.setParentTask(new ParentTask(2));
		task1.setUsers(new HashSet<>(newArrayList(new User(4), new User(5))));
		
		Task task2 = new Task();
		task2.setTaskId(2);
		task2.setTask("Task2");
		task2.setPriority(3);
		task2.setStatus(true);
		task2.setProject(new Project(2));
		task2.setParentTask(new ParentTask(2));
		task2.setUsers(new HashSet<>(newArrayList(new User(4), new User(5))));
		
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("Parent 1");
		
		ParentTask pt2 = new ParentTask();
		pt2.setParentId(2);
		pt2.setParentTask("Parent 2");
		
		when(taskService.list()).thenReturn(newArrayList(task, task1, task2));
		when(parentTaskService.list()).thenReturn(newArrayList(pt1, pt2));
		
		ResponseEntity<List<TaskVO>> response = taskController.search(1, null);
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().size(), 4);
		verify(taskService, times(1)).list();
		verify(parentTaskService, times(1)).list();
	}
	
	@Test
	public void saveTask() throws Exception {
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskId(1);
		taskVO.setTask("Task1");
		taskVO.setPriority(3);
		taskVO.setIsFinished(true);
		taskVO.setProjectId(1);
		taskVO.setParentTaskId(1);
		taskVO.setUserIds(newArrayList(1,2));
		
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		when(taskService.save(task)).thenReturn(1);
		
		ResponseEntity<TaskVO> response = taskController.save(taskVO);
		TaskVO responseVO = response.getBody();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(responseVO.getTaskId(),  new Integer(1));
		assertEquals(responseVO.getTask(), "Task1");
		assertEquals(responseVO.getPriority(), new Integer(3));
		assertEquals(responseVO.getProjectId(), new Integer(1));
		assertEquals(responseVO.getParentTaskId(), new Integer(1));
		assertEquals(responseVO.getUserIds(), newArrayList(1, 2));
		
		verify(taskService, times(1)).save(task);
	}
	
	@Test
	public void updateTask() throws TaskNotFoundException {
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskId(1);
		taskVO.setTask("Task1111");
		taskVO.setPriority(3);
		taskVO.setIsFinished(true);
		taskVO.setProjectId(1);
		taskVO.setParentTaskId(1);
		taskVO.setUserIds(newArrayList(1,2));
		
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1111");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		when(taskService.update(1, task)).thenReturn(task);
		
		ResponseEntity<TaskVO> response = taskController.update(1, taskVO);
		TaskVO responseVO = response.getBody();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(responseVO.getTask(), "Task1111");
		
		verify(taskService, times(1)).update(1, task);
	}
}
