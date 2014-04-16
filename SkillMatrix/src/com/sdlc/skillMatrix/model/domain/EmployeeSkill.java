package com.sdlc.skillMatrix.model.domain;

import java.util.Date;

import com.sdlc.skillMatrix.web.EmployeePersonalDetailsBean;
import com.sdlc.skillMatrix.web.SkillDetailsBean;
import com.sdlc.skillMatrix.web.util.BeanUtil;

public class EmployeeSkill {

	private Integer employeeId;
	private String employeeName;
	private Integer skillId;
	private String skillName;
	private Integer domainId;
	private String DomainName;
	private Integer proficiency;
	private String proficiencyName;
	private Integer experience;
	private String experienceName;
	private Integer recency;
	private String recencyName;
	private String comments;
	private Date lastUpdatedTimeStamp;
	private String activeFlag;

	
	/**
	 * Used by a client-tier entity to get around mvc framework limitations
	 */
	private EmployeePersonalDetailsBean callbackTarget;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Integer getDomainId() {
		return domainId;
	}
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	public Integer getProficiency() {
		return proficiency;
	}
	public void setProficiency(Integer proficiency) {
		this.proficiency = proficiency;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getRecency() {
		return recency;
	}
	public void setRecency(Integer recency) {
		this.recency = recency;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	public EmployeePersonalDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(EmployeePersonalDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	
	public String editEmployeeSkillAction(){
		
		if (callbackTarget != null) {
			return callbackTarget.editEmployeeSkillAction(this);
		}

		return null;
	}

	public String inactivateEmployeeSkillAction(){
	
		if (callbackTarget != null) {
			return callbackTarget.deleteEmployeeSkillAction(this);
		}
	
		return null;
	}
	
	/**
	 * takes the system to the user personal screen
	 * @param employee
	 * @return
	 */
	public String gotoEmployeePersonalPage(){
		
		if (callbackTarget != null) {
			return callbackTarget.gotoEmployeePersonalPage(this);
		}

		return null;
	}
	
	
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDomainName() {
		return DomainName;
	}
	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getProficiencyName() {
		return proficiencyName;
	}
	public void setProficiencyName(String proficiencyName) {
		this.proficiencyName = proficiencyName;
	}
	public String getExperienceName() {
		return experienceName;
	}
	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}
	public String getRecencyName() {
		return recencyName;
	}
	public void setRecencyName(String recencyName) {
		this.recencyName = recencyName;
	}
	
}
