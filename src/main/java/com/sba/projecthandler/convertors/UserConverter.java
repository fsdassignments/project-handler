package com.sba.projecthandler.convertors;

import java.util.List;
import java.util.stream.Collectors;

import com.sba.projecthandler.model.Project;
import com.sba.projecthandler.model.Task;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.vo.UserVO;

public class UserConverter {
	public static UserVO toDto(User user) {
		UserVO userVO = new UserVO();
		userVO.setUserId(user.getUserId());
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		userVO.setEmployeeId(user.getEmployeeId());
		userVO.setTaskId(user.getTask().getTaskId());
		userVO.setProjectId(user.getProject().getProjectId());
		return userVO;
	}
	
	public static List<UserVO> toDtos(List<User> users) {
		return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
	}

	public static User toPojo(UserVO vo) {
		User user = new User();
		user.setUserId(vo.getUserId());
		user.setFirstName(vo.getFirstName());
		user.setLastName(vo.getLastName());
		user.setEmployeeId(vo.getEmployeeId());
		user.setTask(new Task(vo.getTaskId()));
		user.setProject(new Project(vo.getProjectId()));
		return user;
	}
}
