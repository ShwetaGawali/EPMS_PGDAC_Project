package com.sunbeam.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.daos.EmployeeDao;
import com.sunbeam.dtos.DtoEntityConverter;
import com.sunbeam.dtos.EmployeeDTO;
import com.sunbeam.dtos.ProjectDTO;
import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.TaskDTO;
import com.sunbeam.entities.Department;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.Project;
import com.sunbeam.entities.Task;
import com.sunbeam.services.DepartmentServiceImpl;
import com.sunbeam.services.EmployeeServiceImpl;
import com.sunbeam.services.ManagerServiceImpl;
import com.sunbeam.services.ProjectServiceImpl;

@CrossOrigin
@RestController
public class ManagerControllerImpl {
	@Autowired
	private ManagerServiceImpl managerService;
	@Autowired
	private EmployeeServiceImpl empService;
	@Autowired
	private DtoEntityConverter converter;
	@Autowired
	private DepartmentServiceImpl deptService;

	@Autowired
	private ProjectServiceImpl projectService;

	@PostMapping("/manager/createProject")
	public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO) {
		System.out.println("Creating: " + projectDTO);
		System.out.println("Desc : " + projectDTO.getProjectDescription());
		Project project = new Project();
		Department dept = deptService.getDepartment(projectDTO.getDeptId());
		project.setDepartment(dept);
		System.out.println(project);
		projectService.createProject(project, projectDTO);
		return Response.success(project);
	}
	
	@GetMapping("/manager/projectList/{deptId}")
	public ResponseEntity<?> sortedProjects(@PathVariable("deptId") int deptId) {
		List<Project> projectList = new ArrayList<>();
		projectList = projectService.getSortedProjects(deptId);
		return Response.success(projectList);
	}

	@GetMapping("/manager/projectList")
	public ResponseEntity<?> listAllProjects() {
		List<Project> result = new ArrayList<>();
		result = projectService.listAllProjects();
		return Response.success(result);
	}

	@PatchMapping("/manager/projectList/{projectId}")
	public ResponseEntity<?> listAllProjects(@PathVariable("projectId") int projectId,
			@RequestBody ProjectDTO projectDTO) {
		Project project = projectService.getProject(projectId);

		project.setStatus(projectDTO.getStatus());
		Project updatedStatus = projectService.save(project);
		if (updatedStatus != null)
			return Response.success(updatedStatus);
		return Response.error(null);

	}

//	@GetMapping("/manager")
	public ResponseEntity<?> findAllTasks() {

		List<TaskDTO> result = new ArrayList<>();
		result = managerService.findAllTasks();
		return Response.success(result);
	}
	
	@GetMapping("/manager/unassignedTasks")
	public ResponseEntity<?> findAllUnassignedTasks() {

		List<TaskDTO> result = new ArrayList<>();
		result = managerService.findAllUnassignedTasks();
		return Response.success(result);
	}

//	@PutMapping("/manager/assignTask/{taskId}")
//	public ResponseEntity<?> assignTask(@PathVariable("taskId") int taskId,@RequestBody TaskDTO taskDto){
//		Task task = managerService.findByTaskId(taskId);
//		task.setStatus(taskDto.getStatus());
//		
//		Task updatedTask = managerService.save(task);
//		if(updatedTask != null)
//			return Response.success(updatedTask);
//		return Response.error(null);
//	}
	@GetMapping("/manager/assignTask/{deptId}")
	public ResponseEntity<?> completedTasks(@PathVariable("deptId") int deptId) {
		
		List<EmployeeDTO> employeeList = new ArrayList<>();
		employeeList = empService.findAllEmployees(deptId);
		return Response.success(employeeList);
		
	}
	
	
	@PatchMapping("/manager/markAsComplete/{taskId}")
	public ResponseEntity<?> markAsComplete(@PathVariable("taskId") int taskId) {
		
		managerService.markAsComplete("Completed", taskId);
		
		return Response.status(HttpStatus.OK);
	}
	
	
	@PatchMapping("/manager/assignTask/{taskId}")
	public ResponseEntity<?> assignTask(@PathVariable("taskId") int taskId,@RequestBody TaskDTO taskDto) {
		Task task = managerService.findByTaskId(taskId);

//		boolean needUpdate = false;
//		if (StringUtils.hasLength(taskDto.getStatus())) {
//			task.setStatus(taskDto.getStatus());
//			needUpdate = true;
//		}
//		if(needUpdate) {
//			managerService.save(task);
//			return Response.success(task);
//		}
//		return null;
//		===========================================================
		
		task.setStatus(taskDto.getStatus());
		//task.setEmployee(taskDto.getEmployee());
		task.setEmployee(empService.findByEmpId(taskDto.getEmployee().getEmpId()));
		Task updatedTask = managerService.save(task);
		if (updatedTask != null)
			return Response.success(updatedTask);
		return Response.error(null);
	}
}
