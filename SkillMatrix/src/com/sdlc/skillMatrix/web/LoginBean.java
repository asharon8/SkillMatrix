package com.sdlc.skillMatrix.web;

import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.service.EmployeeService;
import com.sdlc.skillMatrix.web.util.BeanUtil;

public class LoginBean {
	
	private EmployeeService employeeService;

	private Integer loginEmployeeId;
	
	private Employee loginUser = new Employee();
	
	private String loginErrorMessage;
	
	private Boolean validUser;
	
	/**
	 * @return
	 */
	public String loginAction(){
		
		loginUser = employeeService.getEmployeebyId(this.loginEmployeeId);
		
		if(loginUser==null || loginUser.getEmployeeId()==null){
			validUser = false;
			loginErrorMessage = "This is not a valid SDLC Employee ID" ;
		}else{
			validUser = true;
			EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
			employeePersonalDetailsBean.init(loginUser);
			employeePersonalDetailsBean.setLoginUser(loginUser);
			return "employeePersonalPage";
		}
		
		return "";
	}
	
	/**
	 * @return
	 */
	public String homeAction(){
		
		if(null!=this.loginUser){
			EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
			employeePersonalDetailsBean.init(loginUser);
			employeePersonalDetailsBean.setLoginUser(loginUser);
			return "employeePersonalPage";
		}else{
			return "";
		}
		
	}
	
	/**
	 * @return
	 */
	public String logout(){
		this.loginUser = null;
		return "welcome";
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


	public Employee getLoginUser() {
		return loginUser;
	}


	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}


	public Boolean getValidUser() {
		return validUser;
	}
	
}
