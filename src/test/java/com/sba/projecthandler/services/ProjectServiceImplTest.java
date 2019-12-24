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

import com.sba.projecthandler.errors.ProjectNotFoundException;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.repositories.ProjectRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {
	@InjectMocks
	private ProjectServiceImpl projectServiceImpl;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProjects() {
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		Project proj2 = new Project();
		proj2.setProjectId(2);
		proj2.setProject("Angular Project");
		proj2.setPriority(2);
		proj2.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj2.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectRepository.findAll()).thenReturn(newArrayList(proj1, proj2));
		
		List<Project> lstProjects = projectServiceImpl.list();
		
		assertEquals(newArrayList(proj1, proj2), lstProjects);
		
		verify(projectRepository, times(1)).findAll();
	}
	
	@Test
	public void getProject() throws ProjectNotFoundException {
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectRepository.findById(1)).thenReturn(Optional.of(proj1));
		
		Project actual = projectServiceImpl.get(1);
		
		assertEquals(proj1, actual);
		
		verify(projectRepository, times(1)).findById(1);
	}
	
	@Test(expected = ProjectNotFoundException.class)
	public void getWithException() throws ProjectNotFoundException {
		projectServiceImpl.get(100);
	}
	
	@Test
	public void saveProject() throws Exception {
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectRepository.save(proj1)).thenReturn(proj1);
		
		int id = projectServiceImpl.save(proj1);
		
		assertEquals(proj1.getProjectId().intValue(), id);
		
		verify(projectRepository, times(1)).save(proj1);
	}
	
	@Test
	public void updateProject() throws ProjectNotFoundException {
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectRepository.save(proj1)).thenReturn(proj1);
		
		Project updatedRecord = projectServiceImpl.update(1 ,proj1);
		
		assertEquals(proj1, updatedRecord);
		
		verify(projectRepository, times(1)).save(proj1);
	}
}
