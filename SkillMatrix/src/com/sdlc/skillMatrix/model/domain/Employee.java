package com.sdlc.skillMatrix.model.domain;

import java.util.Date;

import com.sdlc.skillMatrix.web.EmployeeDetailsBean;

public class Employee {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String sdlcEmployee;
	private String employeeRole;
	private String employeeRoleDescription;
	private String employeeType;
	private String employeeTypeDescription;
	private Date employeeHireDate;
	private Date employeeProjectEndDate;
	private String employeeInProject;
	private String employeeWorkLocation;
	
	private EmployeeDetailsBean callbackTarget;
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSdlcEmployee() {
		return sdlcEmployee;
	}
	public void setSdlcEmployee(String sdlcEmployee) {
		this.sdlcEmployee = sdlcEmployee;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public Date getEmployeeHireDate() {
		return employeeHireDate;
	}
	public void setEmployeeHireDate(Date employeeHireDate) {
		this.employeeHireDate = employeeHireDate;
	}
	public String getEmployeeInProject() {
		return employeeInProject;
	}
	public void setEmployeeInProject(String employeeInProject) {
		this.employeeInProject = employeeInProject;
	}
	public String getEmployeeWorkLocation() {
		return employeeWorkLocation;
	}
	public void setEmployeeWorkLocation(String employeeWorkLocation) {
		this.employeeWorkLocation = employeeWorkLocation;
	}
	public EmployeeDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(EmployeeDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	
	public String editEmployeeAction(){
		
		if (callbackTarget != null) {
			return callbackTarget.editEmployeeAction(this);
		}

		return null;
	}
	
	public String gotoEmployeePersonalPage(){
		
		if (callbackTarget != null) {
			return callbackTarget.gotoEmployeePersonalPage(this);
		}

		return null;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public Date getEmployeeProjectEndDate() {
		return employeeProjectEndDate;
	}
	public void setEmployeeProjectEndDate(Date employeeProjectEndDate) {
		this.employeeProjectEndDate = employeeProjectEndDate;
	}
	public String getEmployeeTypeDescription() {
		return employeeTypeDescription;
	}
	public void setEmployeeTypeDescription(String employeeTypeDescription) {
		this.employeeTypeDescription = employeeTypeDescription;
	}
	public String getEmployeeRoleDescription() {
		return employeeRoleDescription;
	}
	public void setEmployeeRoleDescription(String employeeRoleDescription) {
		this.employeeRoleDescription = employeeRoleDescription;
	}
	
	
}
