package com.sunbeam.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "employee")
public class Employee {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int empId;
	@NotNull
	private String name  ; 
	@Column(unique = true)
	@NotNull
	private String email ;
	@NotNull
	//@JsonIgnore
	private String password ;
	@Column(unique = true)
	private long phone; 
	private String role ;
	private String designation ;
	@Column(nullable = true) 
	private int managerId ;
	
	@ManyToOne
	@JoinColumn(name="deptId")
	private Department dept;
	
	@OneToMany(mappedBy="employee")
	@JsonManagedReference
	private List<Task> taskList;
	
	public Employee() {
		
	}
		
	public Employee(int empId, String name, String email, String password, long phone, String role, Department dept,
			String designation, int managerId) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.dept = dept;
		this.designation = designation;
		this.managerId = managerId;
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
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", role=" + role + ", deptId=" + dept.getdeptId() + ", designation=" + designation
				+ ", managerId=" + managerId + "]";
	}
}
