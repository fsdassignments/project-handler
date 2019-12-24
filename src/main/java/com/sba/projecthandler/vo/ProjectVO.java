package com.sba.projecthandler.vo;

import java.util.Date;
import java.util.List;

public class ProjectVO {
	private Integer projectId;
	private String project;
	private Date startDate;
	private Date endDate;
	private Integer priority;
	private Integer noOfTasks;
	private Integer completed;
	private List<Integer> users;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getNoOfTasks() {
		return noOfTasks;
	}
	public void setNoOfTasks(Integer noOfTasks) {
		this.noOfTasks = noOfTasks;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	public List<Integer> getUsers() {
		return users;
	}
	public void setUsers(List<Integer> users) {
		this.users = users;
	}
}
