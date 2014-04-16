package com.sdlc.skillMatrix.model.domain;

import com.sdlc.skillMatrix.web.CompetencyGroupDetailsBean;


public class CompetencyGroup {

	private Integer competencyGroupId;
	private String competencyGroupName;
	private String activeFlag;
	
	/**
	 * Used by a client-tier entity to get around mvc framework limitations
	 */
	private CompetencyGroupDetailsBean callbackTarget;
	
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
	
	public CompetencyGroupDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(CompetencyGroupDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	
	public String editCompetencyGroupAction(){
		
		if (callbackTarget != null) {
			return callbackTarget.editCompetencyGroupAction(this);
		}

		return null;
	}

	public String inactivateCompetencyGroupAction(){
	
		if (callbackTarget != null) {
			return callbackTarget.inactivateCompetencyGroupAction(this);
		}
	
		return null;
	}
	
	
}
