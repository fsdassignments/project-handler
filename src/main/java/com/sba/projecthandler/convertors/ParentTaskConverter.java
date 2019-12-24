package com.sba.projecthandler.convertors;

import java.util.List;
import java.util.stream.Collectors;

import com.sba.projecthandler.model.ParentTask;
import com.sba.projecthandler.vo.TaskVO;

public class ParentTaskConverter {
	public static ParentTask toPojo(TaskVO taskVO) {
		ParentTask task = new ParentTask();
		task.setParentId(taskVO.getTaskId());
		task.setParentTask(taskVO.getTask());
		return task;
	}
	
	public static TaskVO toDto(ParentTask task) {
		TaskVO vo = new TaskVO();
		vo.setTaskId(task.getParentId());
		vo.setTask(task.getParentTask());
		vo.setIsParent(true);
		return vo;
	}
	
	public static List<TaskVO> toDtos(List<ParentTask> parentTasks) {
		return parentTasks.stream().map(parentTask -> toDto(parentTask)).collect(Collectors.toList());
	}
}
