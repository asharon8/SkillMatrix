package com.sdlc.skillMatrix.web;

import java.util.List;
import java.util.TimeZone;

import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.service.EmployeeService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class EmployeeDetailsBean {
	
	private EmployeeService employeeService;
	private CodesUtility codesUtility;
	
	private Employee employee;
	
	private List<Employee> employeeList;
	
	private Integer loginEmployeeId;
	
	private String addEditPageMode;
	
	private String backPageReference ;
	
	
	public String getEmployeeDetails(){
		return "";
	}
	/**
	 * initialization method for Employee bean
	 * @return
	 */
	public String init(){
		
		if(backPageReference!=null && backPageReference.equalsIgnoreCase("employeePersonalDetailPage")){
			backPageReference = null;
			EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
			return employeePersonalDetailsBean.init(this.employee);
		}
		
		if(employeeList!=null){
			if(employeeList.size()>0){
				employeeList.clear();
			}
		}
		viewAllEmployees();
		employee =  new Employee();
		
		return "allEmployees";
	}
	
	/**
	 * method that fetched list of all employee by calling apprpriate service method
	 */
	public void viewAllEmployees(){
		this.employeeList = employeeService.getAllEmployees();
		
		EmployeeDetailsBean employeeDetailsBean = (EmployeeDetailsBean) BeanUtil.getBean("employeeDetailsBean");
		
		for (Employee employee:employeeList){
			employee.setCallbackTarget(employeeDetailsBean );
			employee.setEmployeeTypeDescription(getEmployeeTypeDescById(employee.getEmployeeType()));
			employee.setEmployeeRoleDescription(getEmployeeRoleDescById(employee.getEmployeeRole()));
		}
	}
	
	/**
	 * Method to add a new employee
	 */
	public String addEmployee(){
		Employee tempEmployee = employeeService.addEmployee(this.employee);
		this.employee = tempEmployee;
		viewAllEmployees();
		return "allEmployees";
	}
	
	/**
	 * this method redirects the system to the add edit employee screen
	 * @return
	 */
	public String addEmployeeAction(){
		addEditPageMode = "add";
		return "addEditEmployee";
	}
	
	/**
	 * method that redirects the system to the add edit screen
	 * @param employee
	 * @return
	 */
	public String editEmployeeAction(Employee employee){
		addEditPageMode = "edit";
		this.employee = employee;
		return "addEditEmployee";
	}
	
	/**
	 * method that updates Employee details in DB by calling appropriate service method
	 */
	public String editEmployee(){
		Employee tempEmployee = employeeService.editEmployee(this.employee);
		this.employee = tempEmployee;
		viewAllEmployees();
		return init();
	}
	
	/**
	 * takes the system to the user personal screen
	 * @param employee
	 * @return
	 */
	public String gotoEmployeePersonalPage(Employee employee){
		
		EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
		return employeePersonalDetailsBean.init(employee);
	}
	
	/**
	 * method returns employee type description for a type  Id passed
	 * @param employee type description
	 * @return
	 */
	public String getEmployeeTypeDescById(String employeeType){
		
		String employeeTypeDesc = "";
		List<SelectItem> employeeTypeDesList = codesUtility.getEmployeeType();
		
		for(SelectItem si:employeeTypeDesList){
			if(si.getValue().equals(employeeType)){
				employeeTypeDesc = si.getLabel();
			}
		}
		return employeeTypeDesc;
	}
	
	/**
	 * method returns employee type description for a type  Id passed
	 * @param employee type description
	 * @return
	 */
	public String getEmployeeRoleDescById(String employeeRole){
		
		String employeeRoleDesc = "";
		List<SelectItem> employeeRoleDesList = codesUtility.getEmployeeRoles();
		
		for(SelectItem si:employeeRoleDesList){
			if(si.getValue().equals(employeeRole)){
				employeeRoleDesc = si.getLabel();
			}
		}
		return employeeRoleDesc;
	}
	
	public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }
	
	
	public Integer getLoginEmployeeId() {
		return loginEmployeeId;
	}

	public void setLoginEmployeeId(Integer loginEmployeeId) {
		this.loginEmployeeId = loginEmployeeId;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public String getAddEditPageMode() {
		return addEditPageMode;
	}
	public void setAddEditPageMode(String addEditPageMode) {
		this.addEditPageMode = addEditPageMode;
	}
	public String getBackPageReference() {
		return backPageReference;
	}
	public void setBackPageReference(String backPageReference) {
		this.backPageReference = backPageReference;
	}
	
}
