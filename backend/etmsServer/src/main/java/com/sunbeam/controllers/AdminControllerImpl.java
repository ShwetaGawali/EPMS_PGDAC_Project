package com.sunbeam.controllers;

import java.util.Map;

import javax.management.AttributeNotFoundException;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.Credentials;
import com.sunbeam.dtos.EmployeeDTO;
import com.sunbeam.daos.EmployeeDao;
import com.sunbeam.dtos.Response;
import com.sunbeam.entities.Employee;
import com.sunbeam.services.AdminServiceImpl;
import com.sunbeam.services.EmployeeServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class AdminControllerImpl {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/admin/signin")
	public ResponseEntity<?> signIn(@RequestBody Credentials cred) {
		Employee emp = adminService.findEmployeeByEmailAndPassword(cred);
		if(emp == null)
			return Response.error("user not found");
		return Response.success(emp);
	}
	
	@PostMapping("/admin/add_employee")
	public ResponseEntity<?> signUp(@RequestBody Employee emp) {
//		System.out.println(emp);
		Map<String, Object> result = adminService.saveEmployee(emp);
		return Response.success(result);
	}
	@GetMapping("/admin/list_employees")
	public ResponseEntity<?> listAllEmployees() {
		List<EmployeeDTO> result = new ArrayList<>();
			result = adminService.listAllEmployees();
		return Response.success(result);
	}
	@PutMapping("/admin/editEmployee/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Employee emp) {
		try {
			emp.setEmpId(id);
			Employee result = adminService.save(emp);
			return Response.success(result); // http status = 200, body = result
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		int count = adminService.deleteEmployeeById(id);
		if(count == 0)
			return ResponseEntity.notFound().build(); // http status = 404
		return ResponseEntity.ok("Record Deleted");	// http status = 200, body = "Record Deleted"
	}

	   

	    @PatchMapping("/employee/{id}")
	    public  ResponseEntity<?> editEmployee(
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
	        if (StringUtils.hasLength(eDto.getDesignation())) {
	            emp.setDesignation(eDto.getDesignation());
	            needUpdate = true;
	        }
	        if (StringUtils.hasLength(eDto.getRole())) {
	            emp.setRole(eDto.getRole());
	            needUpdate = true;
	        }
	        System.out.println(eDto);
	        System.out.println(eDto.getDept().getdeptId());
	        if ( eDto.getDept().getdeptId()!=0) {
	        	
	            emp.setDept(eDto.getDept());
	            needUpdate = true;
	        }
	        if (eDto.getManagerId()!= 0) {
	            emp.setManagerId(eDto.getManagerId());
	            needUpdate = true;
	        }

	        if (needUpdate) {
	            adminService.save(emp);
	            return Response.success(emp);
	        }
	        return null;
	    }
	    @GetMapping("/admin/employeeProfile/{id}")
		public ResponseEntity<?> employeeProfile(@PathVariable int id) {
	    	Employee emp = adminService.findById(id);
			if(emp == null)
				return Response.error("user not found");
			return Response.success(emp);
		}
	    
	    @GetMapping("/admin/updateProfile/{id}")
		public ResponseEntity<?> updateProfile(@PathVariable int id) {
	    	Employee emp = adminService.findById(id);
			if(emp == null)
				return Response.error("user not found");
			return Response.success(emp);
		}
	
}

