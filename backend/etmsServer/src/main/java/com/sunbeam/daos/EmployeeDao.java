package com.sunbeam.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	Employee findByEmpId(int empId);
	Employee findByEmail(String email);
	
	@Query("from Employee e where e.dept.deptId = :deptId")
	List<Employee> findEmpInDept(@Param("deptId") int deptId);
}
