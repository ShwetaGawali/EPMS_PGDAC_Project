package com.sunbeam.entities;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "department")
public class Department {

	@Id
	private int deptId;
	@NotNull
	private String deptName;
	@NotNull
	private int managerId;

	@OneToMany(mappedBy="dept")
	private List<Employee> employeeList;
	
	@OneToMany(mappedBy="department")
	private List<Project> projectList;
	
	
	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(int deptId, String deptName, int managerId) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.managerId = managerId;
	}

	public int getdeptId() {
		return deptId;
	}

	public void setdeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getdeptName() {
		return deptName;
	}

	public void setdeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getmanagerId() {
		return managerId;
	}

	public void setmanagerId(int managerId) {
		this.managerId = managerId;
	}

	
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", managerId=" + managerId + "]";
	}
	
	
}
