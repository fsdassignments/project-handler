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

import com.sba.projecthandler.errors.ParentNotFoundException;
import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.repositories.ParentTaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskServiceImplTest {
	@InjectMocks
	private ParentTaskServiceImpl parentTaskServiceImpl;
	
	@Mock
	private ParentTaskRepository parentTaskRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void list() {
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("Parent 1");
		
		ParentTask pt2 = new ParentTask();
		pt2.setParentId(2);
		pt2.setParentTask("Parent 2");
		
		when(parentTaskRepository.findAll()).thenReturn(newArrayList(pt1, pt2));
		
		List<ParentTask> lstTasks = parentTaskServiceImpl.list();
		
		assertEquals(2, lstTasks.size());
		assertEquals(newArrayList(pt1, pt2), lstTasks);
		
		verify(parentTaskRepository, times(1)).findAll();
	}
	
	@Test
	public void save() throws Exception {
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("Parent 1");
		
		when(parentTaskRepository.save(pt1)).thenReturn(pt1);
		
		int id = parentTaskServiceImpl.save(pt1);
		
		assertEquals(1, id);
		
		verify(parentTaskRepository, times(1)).save(pt1);
	}
	
	@Test
	public void get() throws ParentNotFoundException {
		ParentTask pt1 = new ParentTask();
		pt1.setParentId(1);
		pt1.setParentTask("Parent 1");
		
		when(parentTaskRepository.findById(1)).thenReturn(Optional.of(pt1));
		
		ParentTask actual = parentTaskServiceImpl.get(1);
		
		assertEquals(actual.getParentId(), new Integer(1));
		assertEquals(actual.getParentTask(), "Parent 1");
		
		verify(parentTaskRepository, times(1)).findById(1);
	}
	
	@Test(expected = ParentNotFoundException.class)
	public void getWithException() throws ParentNotFoundException {		
		parentTaskServiceImpl.get(100);		
	}
}
