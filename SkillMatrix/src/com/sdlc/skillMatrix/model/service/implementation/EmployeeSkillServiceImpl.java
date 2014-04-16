package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.EmployeeSkillDao;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.service.EmployeeSkillService;

public class EmployeeSkillServiceImpl implements EmployeeSkillService {

	private EmployeeSkillDao employeeSkillDao;
	
	/**
	 * insert a record in Database
	 */
	public void addEmployeeSkill(EmployeeSkill employeeSkill) {
		employeeSkillDao.insertEmployeeSkill(employeeSkill);
	}

	/**
	 * Update existing record in DB
	 */
	public EmployeeSkill editEmployeeSkill(EmployeeSkill employeeSkill) {
		return employeeSkillDao.updateEmployeeSkillDetails(employeeSkill);
	}

	/**
	 * get a list of all employee skill records from DB
	 */
	public List<EmployeeSkill> getAllEmployeeSkills() {
		return employeeSkillDao.selectAllEmployeeSkills();
	}

	/**
	 * Get employee- skill records from db based on Employee Id
	 */
	public List<EmployeeSkill> getEmployeeSkillbyEmployeeId(Integer employeeId) {
		return employeeSkillDao.selectByEmployeeId(employeeId);
	}

	/**
	 * Get employee- skill records from db based on Skill Id
	 */
	public List<EmployeeSkill> getEmployeeSkillbySkillId(Integer skillId) {
		return employeeSkillDao.selectBySkillId(skillId);
	}

	/**
	 * Get employee- skill records from db based on Skill Id and employee Id
	 */
	public EmployeeSkill getEmployeeSkillbyEmployeeIdAndSkillId(EmployeeSkill aEmployeeSkill){
		return employeeSkillDao.selectByEmployeeSkillId(aEmployeeSkill);
	}
	
	
	public void setEmployeeSkillDao(EmployeeSkillDao employeeSkillDao) {
		this.employeeSkillDao = employeeSkillDao;
	}

	public void removeEmployeeSkill(EmployeeSkill employeeSkill) {
		this.employeeSkillDao.deleteEmployeeSkillDetails(employeeSkill);
	}

}
