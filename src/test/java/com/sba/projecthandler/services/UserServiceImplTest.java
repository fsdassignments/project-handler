package com.sba.projecthandler.services;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sba.projecthandler.errors.UserNotFoundException;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
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
		
		when(userRepository.findAll()).thenReturn(newArrayList(user, user1));
		
		List<User> lstUser = userServiceImpl.list();
		
		assertEquals(2, lstUser.size());
		assertEquals(newArrayList(user, user1) ,lstUser);
		
		verify(userRepository, times(1)).findAll();
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
		
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		User actual = userServiceImpl.get(1);
		
		assertEquals(user, actual);
		
		verify(userRepository, times(1)).findById(1);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void getUserWithException() throws UserNotFoundException {
		userServiceImpl.get(100);
	}
	
	@Test
	public void saveUser() throws Exception { 
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmployeeId(12345);
		user.setProject(new Project(123));
		user.setTask(new Task(456));
		user.setUserId(1);
		
		when(userRepository.save(user)).thenReturn(user);
		
		Integer id = userServiceImpl.save(user);
		
		assertEquals(user.getUserId(), id);
		
		verify(userRepository, times(1)).save(user);
	}
	
	@Test
	public void updateUser() throws UserNotFoundException { 
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmployeeId(12345);
		user.setProject(new Project(123));
		user.setTask(new Task(456));
		user.setUserId(1);
		
		when(userRepository.save(user)).thenReturn(user);
		
		User actual = userServiceImpl.update(1, user);
		
		assertEquals(user, actual);
		
		verify(userRepository, times(1)).save(user);
	}
}
