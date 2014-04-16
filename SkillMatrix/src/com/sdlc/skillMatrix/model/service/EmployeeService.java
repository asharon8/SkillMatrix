package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Employee;


public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee editEmployee(Employee employee);

	public Employee getEmployeebyId(Integer employeeId);
	
}
