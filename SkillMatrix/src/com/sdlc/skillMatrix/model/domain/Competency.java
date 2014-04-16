package com.sdlc.skillMatrix.model.domain;

import com.sdlc.skillMatrix.web.CompetencyDetailsBean;
import com.sdlc.skillMatrix.web.CompetencyGroupDetailsBean;

public class Competency {

	private Integer competencyId;
	private String competencyName;
	private Integer competencyGroupId;
	private String competencyGroupName;
	private String activeFlag;
	
	/**
	 * Used by a client-tier entity to get around mvc framework limitations
	 */
	private CompetencyDetailsBean callbackTarget;
	
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
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public CompetencyDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(CompetencyDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	
	public String editCompetencyAction(){
		
		if (callbackTarget != null) {
			return callbackTarget.editCompetencyAction(this);
		}

		return null;
	}

	public String inactivateCompetencyAction(){
	
		if (callbackTarget != null) {
			return callbackTarget.inactivateCompetencyAction(this);
		}
	
		return null;
	}
	public String getCompetencyGroupName() {
		return competencyGroupName;
	}
	public void setCompetencyGroupName(String competencyGroupName) {
		this.competencyGroupName = competencyGroupName;
	}
	
}
