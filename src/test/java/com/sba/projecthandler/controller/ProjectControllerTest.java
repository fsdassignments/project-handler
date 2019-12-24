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

import com.sba.projecthandler.errors.ProjectNotFoundException;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.services.ProjectService;
import com.sba.projecthandler.vo.ProjectVO;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {

	@InjectMocks
	private ProjectController projectController;
	
	@Mock
	private ProjectService projectService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProjects() {
		Task task = new Task(123);
		task.setStatus(false);
		
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(task)));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		Project proj2 = new Project();
		proj2.setProjectId(2);
		proj2.setProject("Angular Project");
		proj2.setPriority(2);
		
		proj2.setTasks(new HashSet<>(newArrayList(task)));
		proj2.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectService.list()).thenReturn(newArrayList(proj1, proj2));
		
		ResponseEntity<List<ProjectVO>> response = projectController.list();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(response.getBody().size(), 2);
		
		ProjectVO responseVO = response.getBody().get(0);
		assertEquals(responseVO.getCompleted(), new Integer(1));
		assertEquals(responseVO.getNoOfTasks(), new Integer(1));
		
		verify(projectService, times(1)).list();
	}
	
	@Test
	public void getProject() throws ProjectNotFoundException {
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setTasks(new HashSet<>(newArrayList(new Task(123))));
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectService.get(1)).thenReturn(proj1);
		
		ResponseEntity<ProjectVO> response = projectController.get(1);
		
		ProjectVO vo = response.getBody();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(vo.getProjectId(), new Integer(1));
		assertEquals(vo.getProject(), "Java Project");
		assertEquals(vo.getPriority(), new Integer(1));
		assertEquals(vo.getUsers(), newArrayList(123));
		
		verify(projectService, times(1)).get(1);
	}
	
	@Test
	public void saveProject() throws Exception {
		ProjectVO vo = new ProjectVO();
		vo.setProjectId(1);
		vo.setPriority(1);
		vo.setProject("Java Project");
		vo.setUsers(newArrayList(123));
		
		Project proj1 = new Project();
		proj1.setProjectId(1);
		proj1.setProject("Java Project");
		proj1.setPriority(1);
		proj1.setUsers(new HashSet<>(newArrayList(new User(123))));
		
		when(projectService.save(proj1)).thenReturn(1);
		
		ResponseEntity<ProjectVO> response = projectController.save(vo);
		
		ProjectVO responseVO = response.getBody();
		
		assertEquals(response.getStatusCode().toString(), "200 OK");
		assertEquals(responseVO.getProjectId(), new Integer(1));
		assertEquals(responseVO.getProject(), "Java Project");
		assertEquals(responseVO.getPriority(), new Integer(1));
		assertEquals(responseVO.getUsers(), newArrayList(123));
		
		verify(projectService, times(1)).save(proj1);
	}
}
