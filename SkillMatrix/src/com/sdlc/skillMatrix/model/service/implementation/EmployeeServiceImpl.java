package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.EmployeeDao;
import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	
	/**
	 * Insert a new Employee in DB
	 */
	public Employee addEmployee(Employee employee) {
		return employeeDao.insertEmployee(employee);
	}

	/**
	 * Update an existing employee
	 */
	public Employee editEmployee(Employee employee) {
		return employeeDao.updateEmployeeDetails(employee);
	}

	/**
	 * return all the Employee Lists from the DB
	 */
	public List<Employee> getAllEmployees() {
		return employeeDao.selectAllEmployees();
	}

	/**
	 * return Industry Employee by Employee Id
	 */
	public Employee getEmployeebyId(Integer employeeId) {
		return employeeDao.selectByEmployeeId(employeeId);
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
