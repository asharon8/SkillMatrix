package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.EmployeeSkill;


public interface EmployeeSkillDao {
	
	public void insertEmployeeSkill(EmployeeSkill employeeSkill);

	public List<EmployeeSkill> selectAllEmployeeSkills();

	public EmployeeSkill updateEmployeeSkillDetails(EmployeeSkill employeeSkill);
	
	public void deleteEmployeeSkillDetails(EmployeeSkill employeeSkill);

	public List<EmployeeSkill> selectBySkillId(Integer skillId);
	
	public EmployeeSkill selectByEmployeeSkillId(EmployeeSkill aEmployeeSkill);
	
	public List<EmployeeSkill> selectByEmployeeId(Integer employeeId);
}
