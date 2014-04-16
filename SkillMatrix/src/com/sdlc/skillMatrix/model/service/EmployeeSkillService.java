package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.EmployeeSkill;


public interface EmployeeSkillService {

	public void addEmployeeSkill(EmployeeSkill employeeSkill);

	public List<EmployeeSkill> getAllEmployeeSkills();

	public EmployeeSkill editEmployeeSkill(EmployeeSkill employeeSkill);
	
	public void removeEmployeeSkill(EmployeeSkill employeeSkill);

	public List<EmployeeSkill> getEmployeeSkillbySkillId(Integer skillId);
	
	public List<EmployeeSkill> getEmployeeSkillbyEmployeeId(Integer employeeId);
	
	public EmployeeSkill getEmployeeSkillbyEmployeeIdAndSkillId(EmployeeSkill aEmployeeSkill);
}
