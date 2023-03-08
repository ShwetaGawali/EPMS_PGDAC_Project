package com.sunbeam.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.dtos.TaskDTO;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.Task;


public interface TaskDao extends JpaRepository<Task, Integer>{

	Task findByTaskId(int taskId);
	@Modifying
	@Query("update Task t set t.status= :status where t.taskId = :taskId ")
	void markAsComplete(@Param("status") String Completed, @Param("taskId") int taskId);
	
	@Query("from Task t where t.employee.empId = :empId ORDER BY t.status DESC")
	List<Task> sortedTasks(@Param("empId") int empId);
}
