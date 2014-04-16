package com.sdlc.skillMatrix.web;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.service.IndustryDomainService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class IndustryDomainDetailsBean {

	private IndustryDomainService industryDomainService;
	private CodesUtility codesUtility;
	
	private List<IndustryDomain> industryDomainList;
	
	private IndustryDomain industryDomain;
	
	private String addEditPageMode;

	/**
	 * initialization method for Domain bean
	 * @return
	 */
	public String init(){
		
		if(industryDomainList!=null){
			if(industryDomainList.size()>0){
				industryDomainList.clear();
			}
		}
		
		viewAllIndustryDomainDetails();
		
		this.industryDomain =  new IndustryDomain();
		return "domain";
	}
	
	/**
	 * Method that gets all the domain from the Db and displays in the Domain details page
	 */
	public void viewAllIndustryDomainDetails(){
		industryDomainList = industryDomainService.getAllIndustryDomains();
		
		IndustryDomainDetailsBean industryDomainDetailsBean = (IndustryDomainDetailsBean) BeanUtil.getBean("industryDomainDetailsBean");
		
		for (IndustryDomain domain:industryDomainList){
			domain.setCallbackTarget(industryDomainDetailsBean );
		}
	}
	
	/**
	 * Method that adds a new domain in DB
	 */
	public String addNewIndustryDomain(){
		
		IndustryDomain tmpIndustryDomain = industryDomainService.addIndustryDomain(this.industryDomain);
		
		//refresh the dropdowns
		codesUtility.clearDropDowns();
		
		this.industryDomain = tmpIndustryDomain;
		viewAllIndustryDomainDetails();
		return "domain";
	}
	
	/**
	 * Method that redirects the system to the add edit page
	 * @return
	 */
	public String addDomainAction(){
		addEditPageMode = "add";
		return "addEditDomain";
	}
	
	/**
	 * callback method that inactivates any domain
	 * @param industryDomain
	 * @return
	 */
	public String inactivateDomain(IndustryDomain industryDomain){
		industryDomain.setInactivate("Y");
		industryDomainService.editIndustryDomain(industryDomain);
		return "";
	}

	/**
	 * method that redirects the system to the add edit screen
	 * @param industryDomain
	 * @return
	 */
	public String editDomainAction(IndustryDomain industryDomain){
		addEditPageMode = "edit";
		this.industryDomain = industryDomain;
		return "addEditDomain";
	}
	
	/**
	 * method that updates domain details in DB by calling appropriate service method
	 */
	public String updateDomain(){
		IndustryDomain tempIndustryDomain = industryDomainService.editIndustryDomain(this.industryDomain);
		this.industryDomain = tempIndustryDomain;
		viewAllIndustryDomainDetails();
		return "domain";
	}

	public void setIndustryDomainService(IndustryDomainService industryDomainService) {
		
		this.industryDomainService = industryDomainService;
	}
	

	public List<IndustryDomain> getIndustryDomainList() {
		
		return industryDomainList;
	}

	public void setIndustryDomainList(List<IndustryDomain> industryDomainList) {
		this.industryDomainList = industryDomainList;
	}

	public IndustryDomain getIndustryDomain() {
		return industryDomain;
	}

	public void setIndustryDomain(IndustryDomain industryDomain) {
		this.industryDomain = industryDomain;
	}

	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}

	public String getAddEditPageMode() {
		return addEditPageMode;
	}

	public void setAddEditPageMode(String addEditPageMode) {
		this.addEditPageMode = addEditPageMode;
	}
	
}
