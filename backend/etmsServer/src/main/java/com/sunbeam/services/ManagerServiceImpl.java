package com.sunbeam.services;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.EmployeeDao;
import com.sunbeam.daos.TaskDao;
import com.sunbeam.dtos.DtoEntityConverter;
import com.sunbeam.dtos.TaskDTO;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.Task;

@Transactional
@Service
public class ManagerServiceImpl {
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private EmployeeDao empDao;
	@Autowired
	private DtoEntityConverter converter;

	public List<TaskDTO> findAllTasks() {
		List<Task> taskList = taskDao.findAll();
		return taskList.stream().map(task -> converter.toTaskDto(task)).collect(Collectors.toList());
	}

	public Task findByTaskId(int taskId) {
		Task task = taskDao.findByTaskId(taskId);
		return task;
	}

	public void markAsComplete(String statusC, int taskId) {
		taskDao.markAsComplete(statusC, taskId);

	}

	public Task save(Task task) {
		return taskDao.save(task);
	}

	public List<TaskDTO> findAllUnassignedTasks() {
		List<TaskDTO> taskList = findAllTasks();
		List<TaskDTO> unassTaskList = new ArrayList<>();
		for (TaskDTO t : taskList) {
			if (t.getStatus().equals("Unassigned")) {
				unassTaskList.add(t);
			}
		}

		return unassTaskList;
	}

	public List<TaskDTO> getSortedTasks(int empId) {
//		Employee emp= empDao.findByEmpId(empId);
		List<Task> sortedTaskList = taskDao.sortedTasks(empId);
		
		List<TaskDTO> sortedTaskDtoList= new ArrayList<>();
		for(Task task : sortedTaskList) {
			sortedTaskDtoList.add(converter.toTaskDto(task));
			
		}
		
		return sortedTaskDtoList;
	}
}
