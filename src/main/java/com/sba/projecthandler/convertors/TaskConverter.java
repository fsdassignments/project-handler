package com.sba.projecthandler.convertors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.vo.TaskVO;


public class TaskConverter {
	public static Task toPojo(TaskVO taskVO) {
		Task task = new Task();
		task.setTaskId(taskVO.getTaskId());
		task.setTask(taskVO.getTask());
		task.setParentTask(new ParentTask(taskVO.getParentTaskId()));
		task.setProject(new Project(taskVO.getProjectId()));
		List<User> lstUsers = new ArrayList<>();
		for(Integer id: taskVO.getUserIds()) {
			User user = new User(id);
			lstUsers.add(user);
		}
		task.setUsers(new HashSet<User>(lstUsers));
		task.setPriority(taskVO.getPriority());
		task.setStartDate(taskVO.getStartDate());
		task.setEndDate(taskVO.getEndDate());
		task.setStatus(taskVO.getIsFinished());
		return task;
	}

	public static List<TaskVO> toDtos(List<Task> tasks) {
		return tasks.stream().map(task-> toDto(task)).collect(Collectors.toList());
	}

	public static TaskVO toDto(Task task) {
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskId(task.getTaskId());
		taskVO.setTask(task.getTask());
		taskVO.setParentTaskId(task.getParentTask().getParentId());
		taskVO.setProjectId(task.getProject().getProjectId());
		taskVO.setPriority(task.getPriority());
		taskVO.setStartDate(task.getStartDate());
		taskVO.setEndDate(task.getEndDate());
		taskVO.setIsFinished(task.isStatus());
		taskVO.setUserIds(task.getUsers().stream().map(User::getUserId).collect(Collectors.toList()));
		return taskVO;
	}
}
