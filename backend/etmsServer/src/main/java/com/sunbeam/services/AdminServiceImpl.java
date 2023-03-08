package com.sunbeam.services;

import java.util.Collections;
import java.util.Map;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.sunbeam.daos.EmployeeDao;
import com.sunbeam.dtos.Credentials;
import com.sunbeam.dtos.EmployeeDTO;
import com.sunbeam.dtos.DtoEntityConverter;
import com.sunbeam.entities.Employee;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Transactional
@Service
public class AdminServiceImpl {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DtoEntityConverter converter;
	public Employee findEmployeeByEmailAndPassword(Credentials cred) {
		Employee emp = employeeDao.findByEmail(cred.getEmail());
		String rawPassword = cred.getPassword();
		if(emp != null && rawPassword.equals(emp.getPassword())) {
//			emp.setPassword("********");
			return emp;
		}
		return null;
	}
	
	public Map<String, Object> saveEmployee(Employee emp) {
		String rawPassword = emp.getPassword();
		emp.setPassword(rawPassword);
		
		emp = employeeDao.save(emp);
		return Collections.singletonMap("insertedId", emp.getEmpId());
	}
	public List<EmployeeDTO> listAllEmployees() {
		List<Employee> employeeList = employeeDao.findAll();
		return employeeList.stream()
			.map(emp -> converter.toEmployeeDto(emp))
			.collect(Collectors.toList());
	}
//	public Employee update(Employee emp) {
//		return employeeDao.saveEmployee(emp);
//	}

	public Employee findById(int empId) {
		Employee e = employeeDao.findByEmpId(empId);
		return e;
	}
//	public Employee update(Employee emp) {
//		return employeeDao.save(emp);
//	}
	public Employee save(Employee emp) {
		return employeeDao.save(emp);
	}
//	public Map<String, Object> editEmployee(int empId, EmployeeDTO EmployeeDTO) {
//		if(employeeDao.existsById(empId)) {
//			EmployeeDTO.setEmpId(empId);
//			Employee emp = converter.toUserEntity(EmployeeDTO);
//			emp.setName(employeeDao.findByEmpId(EmployeeDTO.getEmpId()));
//			emp.setEmail(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setPassword(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setPhone(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setDesignation(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setDept(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setManagerId(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp.setRole(employeeDao.findById(EmployeeDTO.getEmpId()));
//			emp = employeeDao.save(emp);
//			return Collections.singletonMap("changedRows", 1);
//		}
//		return Collections.singletonMap("changedRows", 0);
//	}
	public int deleteEmployeeById(int EmpId) {
		if(employeeDao.existsById(EmpId)) {
			employeeDao.deleteById(EmpId);
			return 1;
		}
		return 0;
	}
}
//	public Employee update(Employee emp) {
//		return EmployeeDao.save(emp);
//	}


