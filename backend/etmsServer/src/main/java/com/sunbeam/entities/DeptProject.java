package com.sunbeam.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//@Table(name = "deptProject")
public class DeptProject {

	private int dept_id;
	private int project_id;
	@ManyToOne
	@JoinColumn(name="deptId")
	private Department department;
	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;
	
	public DeptProject() {
		// TODO Auto-generated constructor stub
	}

	public DeptProject(int dept_id, int project_id) {
		super();
		this.dept_id = dept_id;
		this.project_id = project_id;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	@Override
	public String toString() {
		return "DeptProject [dept_id=" + dept_id + ", project_id=" + project_id + "]";
	}
	
	
}
