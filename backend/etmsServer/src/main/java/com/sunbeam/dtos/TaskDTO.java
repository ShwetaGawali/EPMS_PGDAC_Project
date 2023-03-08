package com.sunbeam.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.Project;

@JsonIgnoreProperties(allowSetters = true, value = { "employee" })
public class TaskDTO {

	private int taskId;
	private Employee employee;
	private Project project;
	private String taskName;
	private Date startDate;
	private Date dueDate;
	private String status;
	private String description;
	private int projectId;

	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}

	public TaskDTO(int taskId, Employee employee, Project project, String taskName, Date startDate, Date dueDate,
			String status, String description, int projectId) {
		super();
		this.taskId = taskId;
		this.employee = employee;
		this.project = project;
		this.taskName = taskName;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.status = status;
		this.description = description;
		this.projectId = projectId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

//	public int getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(int projectId) {
//		this.projectId = projectId;
//	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return String.format(
				"TaskDTO [taskId=%s, employee=%s, project=%s, taskName=%s, startDate=%s, dueDate=%s, status=%s, description=%s, projectId=%s]",
				taskId, employee, project, taskName, startDate, dueDate, status, description, projectId);
	}

}
