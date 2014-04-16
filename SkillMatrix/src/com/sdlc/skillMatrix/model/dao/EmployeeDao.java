package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Employee;



public interface EmployeeDao {

	public Employee insertEmployee(Employee employee);

	public List<Employee> selectAllEmployees();

	public Employee updateEmployeeDetails(Employee employee);

	public Employee selectByEmployeeId(Integer employeeId);
}
