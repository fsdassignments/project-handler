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

import com.sba.projecthandler.errors.ParentNotFoundException;
import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.services.ParentTaskService;
import com.sba.projecthandler.vo.TaskVO;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskControllerTest {
	@InjectMocks
	private ParentTaskController parentTaskController;
	
	@Mock
	private ParentTaskService parentTaskService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getParentTasks() {
		Task task = new Task(123);
		
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("Parent 1");
		pt1.setTasks(new HashSet<Task>(newArrayList(task)));
		
		ParentTask pt2 = new ParentTask();
		pt2.setParentId(2);
		pt2.setParentTask("Parent 2");
		pt2.setTasks(new HashSet<Task>(newArrayList(task)));
		
		List<ParentTask> lstParentTasks = newArrayList(pt1, pt2);
		
		when(parentTaskService.list()).thenReturn(lstParentTasks);
		
		ResponseEntity<List<TaskVO>> response = parentTaskController.list();

		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().size(), 2);
		
		TaskVO responseVO = response.getBody().get(0);
		assertEquals(responseVO.getIsParent(), true);
		
		verify(parentTaskService, times(1)).list();
	}
	
	@Test
	public void createParentTask() throws Exception {
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskId(1);
		taskVO.setTask("AnyTask");
		
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("AnyTask");
				
		when(parentTaskService.save(pt1)).thenReturn(1);
		
		ResponseEntity<TaskVO> response = parentTaskController.save(taskVO);
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().getTaskId(), 1);
		assertEquals(response.getBody().getTask(), "AnyTask");
		
		verify(parentTaskService, times(1)).save(pt1);
	}
	
	@Test
	public void getParentTask() throws ParentNotFoundException {
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskId(1);
		taskVO.setTask("AnyTask");
		
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("AnyTask");
		
		when(parentTaskService.get(1)).thenReturn(pt1);
		
		ResponseEntity<TaskVO> response = parentTaskController.get(1);
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().getTaskId(), 1);
		assertEquals(response.getBody().getTask(), "AnyTask");
		
		verify(parentTaskService, times(1)).get(1);
	}
}
