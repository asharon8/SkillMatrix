package com.sdlc.skillMatrix.web.util;

import java.util.ArrayList;
import java.util.List;

import com.sdlc.skillMatrix.model.domain.EmployeeSkill;

public class SearchResultView {

	private String employeeName;
	private List<EmployeeSkill> listES = new ArrayList<EmployeeSkill>();
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public List<EmployeeSkill> getListES() {
		return listES;
	}
	public void setListES(List<EmployeeSkill> listES) {
		this.listES = listES;
	}
}
