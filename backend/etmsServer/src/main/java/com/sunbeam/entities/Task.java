package com.sunbeam.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
public class Task {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int taskId;
	@NotNull
	private String taskName;
	private String status;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name = "empId")
	@JsonBackReference
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "projectId")
	@JsonIgnore
	private Project project;

	public Task() {

	}

	public Task(int taskId, String taskName, String status, String description, Date startDate, Date dueDate, int empId,
			int projectId, int deptId, Employee employee, Project project, Department department) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.status = status;
		this.description = description;
		this.startDate = startDate;
		this.dueDate = dueDate;
//		this.empId = empId;
//		this.projectId = projectId;
//		this.deptId = deptId;
	}

	public int gettaskId() {
		return taskId;
	}

	public void settaskId(int taskId) {
		this.taskId = taskId;
	}

	public String gettaskName() {
		return taskName;
	}

	public void settaskName(String taskName) {
		this.taskName = taskName;
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

	public Date getstartDate() {
		return startDate;
	}

	public void setstartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getdueDate() {
		return dueDate;
	}

	public void setdueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

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

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", status=" + status + ", description="
				+ description + ", startDate=" + startDate + ", dueDate=" + dueDate
				+ ", projectId=" + project.getprojectId() + "]";
	}

}
