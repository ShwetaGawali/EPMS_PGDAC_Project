package com.sunbeam.dtos;

import org.springframework.stereotype.Component;

import com.sunbeam.entities.Department;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.Project;
import com.sunbeam.entities.Task;

@Component
public class DtoEntityConverter {

	public EmployeeDTO toEmployeeDto(Employee entity) {
		if(entity == null)
			return null;
		EmployeeDTO dto = new EmployeeDTO();
//		dto.setDeptId(entity.getDept().getdeptId());
		dto.setEmpId(entity.getEmpId());
		dto.setDept(entity.getDept());
		dto.setDesignation(entity.getDesignation());
		dto.setEmail(entity.getEmail());
		dto.setManagerId(entity.getManagerId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setPhone(entity.getPhone());
		dto.setRole(entity.getRole());
		dto.setTaskList(entity.getTaskList());
		return dto;
	}
	
	public Employee toEmployeeEntity(EmployeeDTO dto) {
		if(dto == null)
			return null;
		Employee entity = new Employee();
		
//		Department dept=new Department();
//		dept.setdeptId(dto.getDeptId());
		
		entity.setDept(dto.getDept());
		entity.setEmpId(dto.getEmpId());
		entity.setDesignation(dto.getDesignation());
		entity.setEmail(dto.getEmail());
		entity.setManagerId(dto.getManagerId());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setPhone(dto.getPhone());
		entity.setRole(dto.getRole());
		entity.setTaskList(dto.getTaskList());
		return entity;
	}
	
	public TaskDTO toTaskDto(Task entity) {
		if(entity == null)
			return null;
		TaskDTO dto= new TaskDTO();
		dto.setTaskId(entity.gettaskId());
		dto.setProjectId(entity.getProject().getprojectId());
		dto.setEmployee(entity.getEmployee());
		dto.setProject(entity.getProject());
		dto.setTaskName(entity.gettaskName());
		dto.setStartDate(entity.getstartDate());
		dto.setDueDate(entity.getdueDate());
		dto.setStatus(entity.getStatus());
		dto.setDescription(entity.getDescription());
		return dto;
	}
	
	public Task toTaskEntity(TaskDTO dto) {
		if(dto == null)
			return null;
		Task entity = new Task();
		entity.settaskId(dto.getTaskId());
//		Project proj = new Project();
//		proj.setprojectId(dto.getProjectId());
		entity.setEmployee(dto.getEmployee());
		entity.setProject(dto.getProject());
		entity.settaskName(dto.getTaskName());
		entity.setstartDate(dto.getStartDate());
		entity.setdueDate(dto.getDueDate());
		entity.setStatus(dto.getStatus());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public Project toProjectEntity(Project project, ProjectDTO dto) {
		project.setprojectName(dto.getProjectName());
		project.setstartDate(dto.getStartDate());
		project.setdueDate(dto.getDueDate());
		project.setStatus("In-progress");
		project.setprojectDescription(dto.getProjectDescription());
		return project;
	}
	
	public Task toTaskEntity(Task task, TaskDTO dto) {
		task.settaskName(dto.getTaskName());
		task.setstartDate(dto.getStartDate());
		task.setdueDate(dto.getDueDate());
		task.setDescription(dto.getDescription());
		task.setStatus("Unassigned");
		System.out.println(task);
		return task;
	}
}
