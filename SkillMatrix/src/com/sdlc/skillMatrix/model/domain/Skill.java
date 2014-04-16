package com.sdlc.skillMatrix.model.domain;

import com.sdlc.skillMatrix.web.CompetencyDetailsBean;
import com.sdlc.skillMatrix.web.SkillDetailsBean;

public class Skill {

	private Integer skillId;
	private String skillName;
	private Integer competencyId;
	private String competencyName;
	private Integer competencyGroupId;
	private String competencyGroupName;
	private String activeFlag;
	
	/**
	 * Used by a client-tier entity to get around mvc framework limitations
	 */
	private SkillDetailsBean callbackTarget;
	
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Integer getCompetencyId() {
		return competencyId;
	}
	public void setCompetencyId(Integer competencyId) {
		this.competencyId = competencyId;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	public Integer getCompetencyGroupId() {
		return competencyGroupId;
	}
	public void setCompetencyGroupId(Integer competencyGroupId) {
		this.competencyGroupId = competencyGroupId;
	}
	public String getCompetencyGroupName() {
		return competencyGroupName;
	}
	public void setCompetencyGroupName(String competencyGroupName) {
		this.competencyGroupName = competencyGroupName;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public SkillDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(SkillDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	
	public String editSkillAction(){
		
		if (callbackTarget != null) {
			return callbackTarget.editSkillAction(this);
		}

		return null;
	}

	public String inactivateSkillAction(){
	
		if (callbackTarget != null) {
			return callbackTarget.inactivateSkillAction(this);
		}
	
		return null;
	}
	
	
}
