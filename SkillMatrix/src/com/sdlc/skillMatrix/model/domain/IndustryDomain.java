package com.sdlc.skillMatrix.model.domain;

import com.sdlc.skillMatrix.web.IndustryDomainDetailsBean;

public class IndustryDomain {

	private Integer domainId;
	private String domainName;
	private String inactivate;
	
	/**
	 * Used by a client-tier entity to get around mvc framework limitations
	 */
	private IndustryDomainDetailsBean callbackTarget;
	
	public Integer getDomainId() {
		return domainId;
	}
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String inactivate() {

		if (callbackTarget != null) {
			return callbackTarget.inactivateDomain(this);
		}

		return null;
	}
	
	public String editDetails(){
		
		if (callbackTarget != null) {
			return callbackTarget.editDomainAction(this);
		}

		return null;
	}
	
	public IndustryDomainDetailsBean getCallbackTarget() {
		return callbackTarget;
	}
	public void setCallbackTarget(IndustryDomainDetailsBean callbackTarget) {
		this.callbackTarget = callbackTarget;
	}
	public String getInactivate() {
		return inactivate;
	}
	public void setInactivate(String inactivate) {
		this.inactivate = inactivate;
	}
}
