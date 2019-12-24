package com.sba.projecthandler.model;
// Generated Dec 22, 2019 12:04:14 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Task generated by hbm2java
 */
@Entity
@Table(name = "task", catalog = "fsd_sba")
public class Task implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7899248282467278053L;
	private Integer taskId;
	private ParentTask parentTask;
	private Project project;
	private String task;
	private Date startDate;
	private Date endDate;
	private int priority;
	private boolean status;
	private Set<User> users = new HashSet<>(0);

	public Task() {
	}
	
	public Task(Integer id) {
		this.taskId = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "task_id", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = false)
	public ParentTask getParentTask() {
		return this.parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "task", nullable = false, length = 500)
	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "priority", nullable = false)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, parentTask, priority, project, startDate, status, task, taskId, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(parentTask, other.parentTask)
				&& priority == other.priority && Objects.equals(project, other.project)
				&& Objects.equals(startDate, other.startDate) && status == other.status
				&& Objects.equals(task, other.task) && Objects.equals(taskId, other.taskId)
				&& Objects.equals(users, other.users);
	}

}
