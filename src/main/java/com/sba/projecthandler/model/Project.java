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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", catalog = "fsd_sba")
public class Project implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2152796909986958275L;
	private Integer projectId;
	private String project;
	private Date startDate;
	private Date endDate;
	private Integer priority;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<User> users = new HashSet<User>(0);

	public Project() {
	}
	
	public Project(Integer id) {
		this.projectId = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "project", nullable = false, length = 500)
	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "priority")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, priority, project, projectId, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(priority, other.priority)
				&& Objects.equals(project, other.project) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(startDate, other.startDate);
	}
}
