package com.sunbeam.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.daos.EmployeeDao;
import com.sunbeam.dtos.Credentials;
import com.sunbeam.dtos.EmployeeDTO;
import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.TaskDTO;
import com.sunbeam.entities.Employee;
import com.sunbeam.services.EmployeeServiceImpl;
import com.sunbeam.services.ManagerServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeControllerImpl {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeServiceImpl employeeService;
	@Autowired
	private ManagerServiceImpl managerService;
	
	@PostMapping("/employee/signin")
	public ResponseEntity<?> signIn(@RequestBody Credentials cred) {
//		Employee emp = employeeService.findEmployeeByEmailAndPassword(cred);
//		if(emp == null)
//			return Response.error("user not found");
//		return Response.success(emp);
		
		EmployeeDTO employeeDto = employeeService.findEmployeeByEmailAndPassword(cred);
		if(employeeDto == null)
			return Response.error("user not found");
		return Response.success(employeeDto);
		
	}
	
	@PostMapping("/admin/signup")
	public ResponseEntity<?> signUp(@RequestBody EmployeeDTO employeeDto) {
//		Map<String, Object> result = employeeService.saveEmployee(emp);
//		return Response.success(result);
		
		EmployeeDTO result = employeeService.saveEmployee(employeeDto);
//		Map<String, Object> result = employeeService.saveEmployee(employeeDto);
		return Response.success(result);
	}
	

	@GetMapping("/employee/{empId}")
	public ResponseEntity<?> sortedTasks(@PathVariable("empId") int empId) {
		
		List<TaskDTO> taskList = new ArrayList<>();
		taskList = managerService.getSortedTasks(empId);
		return Response.success(taskList);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> homepage() {
		
//		return Response.status(HttpStatus.OK);
		return Response.success("Welcome");
	}
	
	@PatchMapping("/change_password/{id}")
    public  ResponseEntity<?> patchResource(
            @PathVariable int id,
            @RequestBody EmployeeDTO eDto) {

        Employee emp = employeeDao.findByEmpId(id);

        boolean needUpdate = false;
        if (StringUtils.hasLength(eDto.getPassword())) {
            emp.setPassword(eDto.getPassword());
            needUpdate = true;
        }
        if (needUpdate) {
            employeeService.saveEmployee(emp);
            return Response.success(emp);
        }
        return null;
    }
	
	@GetMapping("/employeeProfile/{id}")
	public ResponseEntity<?> employeeProfile(@PathVariable("id") int empId) {
		Employee result = employeeService.employeeProfile(empId);
		return Response.success(result);
	}
	
	@PatchMapping("/employeeUpdate/{id}")
    public  ResponseEntity<?> employeeUpdate(
            @PathVariable int id,
            @RequestBody EmployeeDTO eDto) {

        Employee emp = employeeDao.findByEmpId(id);

        boolean needUpdate = false;

        if (StringUtils.hasLength(eDto.getName())) {
            emp.setName(eDto.getName());
            needUpdate = true;
        }

        if (StringUtils.hasLength(eDto.getEmail())) {
            emp.setEmail(eDto.getEmail());
            needUpdate = true;
        }

        if (eDto.getPhone()!= 0) {
            emp.setPhone(eDto.getPhone());
            needUpdate = true;
        }
        if (StringUtils.hasLength(eDto.getPassword())) {
            emp.setPassword(eDto.getPassword());
            needUpdate = true;
        }
        
        if (needUpdate) {
            employeeService.save(emp);
            return Response.success(emp);
        }
        return null;
    }
}
