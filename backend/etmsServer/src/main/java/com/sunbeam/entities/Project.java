package com.sunbeam.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "project")
public class Project {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int projectId;
	private String projectName;
	private String status;
	private String projectDescription;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@OneToMany(mappedBy = "project")
	private List<Task> taskList;

	@ManyToOne
	@JoinColumn(name = "deptId")
	private Department department;

	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(int projectId, String projectName, String status, String projectDescription, Date startDate,
			Date dueDate, Department department) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.status = status;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.department = department;
	}

	public int getprojectId() {
		return projectId;
	}

	public void setprojectId(int projectId) {
		this.projectId = projectId;
	}

	public String getprojectName() {
		return projectName;
	}

	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getprojectDescription() {
		return projectDescription;
	}

	public void setprojectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", status=" + status
				+ ", projectDescription=" + projectDescription + ", startDate=" + startDate + ", dueDate=" + dueDate
				+ "]";
	}

}
