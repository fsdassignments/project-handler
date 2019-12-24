package com.sba.projecthandler.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.sba.projecthandler.errors.UserNotFoundException;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.services.UserService;
import com.sba.projecthandler.vo.UserVO;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUsers() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmployeeId(12345);
		user.setProject(new Project(123));
		user.setTask(new Task(456));
		user.setUserId(1);
		
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Cena");
		user1.setEmployeeId(6789);
		user1.setProject(new Project(456));
		user1.setTask(new Task(789));
		user1.setUserId(2);
		
		List<User> lstUsers = newArrayList(user, user1);
		
		when(userService.list()).thenReturn(lstUsers);
		
		ResponseEntity<List<UserVO>> response = userController.list();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().size(), 2);
		verify(userService, times(1)).list();
	}
	
	@Test
	public void getUser() throws UserNotFoundException {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmployeeId(12345);
		user.setProject(new Project(123));
		user.setTask(new Task(456));
		user.setUserId(1);
		
		when(userService.get(1)).thenReturn(user);
		
		ResponseEntity<UserVO> response = userController.get(1);
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().getUserId(), new Integer(1));
		assertEquals(response.getBody().getFirstName(), "John");
		assertEquals(response.getBody().getLastName(), "Doe");
		assertEquals(response.getBody().getProjectId(), new Integer(123));
		
		verify(userService, times(1)).get(1);
	}
	
	@Test
	public void saveUser() throws Exception {
		UserVO vo = new UserVO();
		vo.setFirstName("John");
		vo.setLastName("Doe");
		vo.setEmployeeId(12345);
		vo.setProjectId(123);
		vo.setTaskId(456);
		vo.setUserId(1);
		
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Doe");
		user1.setEmployeeId(12345);
		user1.setProject(new Project(123));
		user1.setTask(new Task(456));
		user1.setUserId(1);
		
		when(userService.save(user1)).thenReturn(1);
		
		ResponseEntity<UserVO> response = userController.save(vo);
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().getUserId(), new Integer(1));
		assertEquals(response.getBody().getFirstName(), "John");
		assertEquals(response.getBody().getLastName(), "Doe");
		assertEquals(response.getBody().getProjectId(), new Integer(123));
	}
}
