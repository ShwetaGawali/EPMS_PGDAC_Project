package com.sunbeam.dtos;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectDTO {

	private String projectName;
	private int deptId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(value = TemporalType.DATE)
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(value = TemporalType.DATE)
	private Date dueDate;
	private String status;
	private String projectDescription;

	public ProjectDTO() {

	}

	public ProjectDTO(String projectName, int deptId, Date startDate, Date dueDate, String status,
			String projectDescription) {
		super();
		this.projectName = projectName;
		this.deptId = deptId;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.status = status;
		this.projectDescription = projectDescription;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
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

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@Override
	public String toString() {
		return "ProjectDTO [projectName=" + projectName + ", deptId=" + deptId + ", startDate=" + startDate
				+ ", dueDate=" + dueDate + ", status=" + status + ", projectDescription=" + projectDescription + "]";
	}

}
