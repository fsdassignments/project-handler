package com.sba.projecthandler.services;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sba.projecthandler.errors.TaskNotFoundException;
import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.repositories.TaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

	@InjectMocks
	private TaskServiceImpl taskServiceImpl;

	@Mock
	private TaskRepository taskRepository;

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

		when(taskRepository.findAll()).thenReturn(newArrayList(task, task1));

		List<Task> lstTasks = taskServiceImpl.list();

		assertEquals(newArrayList(task, task1), lstTasks);
		verify(taskRepository, times(1)).findAll();
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

		when(taskRepository.findById(1)).thenReturn(Optional.of(task));
		
		Task actual = taskServiceImpl.get(1);
		
		assertEquals(task, actual);
		
		verify(taskRepository, times(1)).findById(1);
	}
	
	@Test(expected = TaskNotFoundException.class)
	public void getWithException() throws TaskNotFoundException {
		taskServiceImpl.get(100);
	}
	
	@Test
	public void saveTask() throws Exception {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		when(taskRepository.save(task)).thenReturn(task);
		
		int id = taskServiceImpl.save(task);
		
		assertEquals(task.getTaskId().intValue(), id);
		
		verify(taskRepository, times(1)).save(task);
	}
	
	@Test
	public void updateTask() throws Exception {
		Task task = new Task();
		task.setTaskId(1);
		task.setTask("Task1");
		task.setPriority(3);
		task.setStatus(true);
		task.setProject(new Project(1));
		task.setParentTask(new ParentTask(1));
		task.setUsers(new HashSet<>(newArrayList(new User(1), new User(2))));
		
		when(taskRepository.save(task)).thenReturn(task);
		
		Task actual = taskServiceImpl.update(1, task);
		
		assertEquals(task, actual);
		
		verify(taskRepository, times(1)).save(task);
	}
}
