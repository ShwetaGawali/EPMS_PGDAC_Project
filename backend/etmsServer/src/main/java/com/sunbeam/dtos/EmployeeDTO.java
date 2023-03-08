package com.sunbeam.dtos;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.entities.Department;
import com.sunbeam.entities.Task;

public class EmployeeDTO {

	private int empId;
	private String name;
	private String email ;
	private String password ;
	private long phone; 
	private String role ;
	private String designation ;
	private int managerId ;
	private Department dept;
	private List<Task> taskList;
	
	public EmployeeDTO() {
		this.taskList = new ArrayList<>();
	}

	public EmployeeDTO(int empId, String name, String email, String password, long phone, String role,
			String designation, int managerId, Department dept) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.designation = designation;
		this.managerId = managerId;
		this.dept = dept;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

//	public int getDept() {
//		return dept;
//	}
//
//	public void setDeptId(int deptId) {
//		this.dept = deptId;
//	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	public List<Task> getTaskList() {
		return taskList;
	}


	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return String.format(
				"EmployeeDTO [empId=%s, name=%s, email=%s, password=%s, phone=%s, role=%s, designation=%s, managerId=%s, dept=%s, taskList=%s]",
				empId, name, email, password, phone, role, designation, managerId, dept, taskList);
	}

	

	
}
