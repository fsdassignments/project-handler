package com.sba.projecthandler.convertors;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.vo.ProjectVO;

public class ProjectConverter {
	public static ProjectVO toDto(Project project) {
		ProjectVO projectVO = new ProjectVO();
		projectVO.setProjectId(project.getProjectId());
		projectVO.setProject(project.getProject());
		projectVO.setPriority(project.getPriority());
		projectVO.setStartDate(project.getStartDate());
		projectVO.setEndDate(project.getEndDate());
		projectVO.setNoOfTasks(project.getTasks().size());
		projectVO.setCompleted(project.getTasks().stream().filter(task -> (!task.isStatus())).collect(Collectors.toList()).size());
		projectVO.setUsers(project.getUsers().stream().map(user->user.getUserId()).collect(Collectors.toList()));
		return projectVO;
	}
	
	public static List<ProjectVO> toDtos(List<Project> projects) {
		return projects.stream().map(project -> toDto(project)).collect(Collectors.toList());
	}
	
	public static Project toPojo(ProjectVO projectVO) {
		Project project = new Project();
		project.setProjectId(projectVO.getProjectId());
		project.setProject(projectVO.getProject());
		project.setPriority(projectVO.getPriority());
		project.setStartDate(projectVO.getStartDate());
		project.setEndDate(projectVO.getEndDate());
		project.setUsers(new HashSet<User>(projectVO.getUsers().stream().map(id -> new User(id)).collect(Collectors.toList())));
		return project;
	}
}
