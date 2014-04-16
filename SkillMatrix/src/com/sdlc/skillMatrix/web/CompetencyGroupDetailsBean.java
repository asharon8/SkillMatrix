package com.sdlc.skillMatrix.web;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.CompetencyGroup;
import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.service.CompetencyGroupService;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class CompetencyGroupDetailsBean {

	private CompetencyGroupService competencyGroupService;
	
	private CompetencyService competencyService;
	
	private CodesUtility codesUtility;
	
	private CompetencyGroup competencyGroup;
	
	private List<CompetencyGroup> competencyGroupList;
	
	private String addEditPageMode;
	
	/**
	 * initialization method for Competency Group Detail bean
	 * @return
	 */
	public String init(){
		
		if(competencyGroupList!=null){
			if(competencyGroupList.size()>0){
				competencyGroupList.clear();
			}
		}
		
		viewAllCompetencyGroup();
		
		this.competencyGroup =  new CompetencyGroup();
		return "allCompetencyGroups";
	}
	
	/**
	 * Method that gets all the Competency Group from the Db and displays in the Competency Group details page
	 */
	public void viewAllCompetencyGroup(){
		
		competencyGroupList = competencyGroupService.getAllCompetencyGroups();
		
		CompetencyGroupDetailsBean competencyGroupDetailsBean = (CompetencyGroupDetailsBean) BeanUtil.getBean("competencyGroupDetailsBean");
		
		for (CompetencyGroup competencyGroup:competencyGroupList){
			competencyGroup.setCallbackTarget(competencyGroupDetailsBean );
		}
	}
	
	/**
	 * Method to add a new Competency Group
	 */
	public String addCompetencyGroup(){
		CompetencyGroup tempCompetencyGroup = competencyGroupService.addCompetencyGroup(this.competencyGroup);
		this.competencyGroup = tempCompetencyGroup;
		
		//refresh the dropdowns
		codesUtility.clearDropDowns();
		
		CompetencyGroupDetailsBean competencyGroupDetailsBean = (CompetencyGroupDetailsBean) BeanUtil.getBean("competencyGroupDetailsBean");
		
		viewAllCompetencyGroup();
		return "allCompetencyGroups";
	}
	
	/**
	 * this method redirects the system to the add edit Competency Group screen
	 * @return
	 */
	public String addCompetencyGroupAction(){
		addEditPageMode = "add";
		return "addEditCompetencyGroup";
	}
	
	/**
	 * this method redirects the system to the add edit Competency Group screen
	 * @return
	 */
	public String inactivateCompetencyGroupAction(CompetencyGroup competencyGroup){
		competencyGroup.setActiveFlag("N");
		competencyGroupService.editCompetencyGroup(competencyGroup);
		
		CompetencyDetailsBean competencyDetailsBean = (CompetencyDetailsBean) BeanUtil.getBean("competencyDetailsBean");
		
		List<Competency> competencyByComGrpId = competencyService.getCompetencysByCompetencyGroupId(competencyGroup.getCompetencyGroupId());
		
		if(!competencyByComGrpId.isEmpty()){
			for(Competency competency : competencyByComGrpId){
				competencyDetailsBean.inactivateCompetencyAction(competency);
			}
		}
		
		return "allCompetencyGroups";
	}
	
	/**
	 * method that redirects the system to the add edit screen
	 * @param Competency Group
	 * @return
	 */
	public String editCompetencyGroupAction(CompetencyGroup competencyGroup){
		addEditPageMode = "edit";
		this.competencyGroup = competencyGroup;
		return "addEditCompetencyGroup";
	}
	
	/**
	 * method that updates Competency Group details in DB by calling appropriate service method
	 */
	public String editCompetencyGroup(){
		CompetencyGroup tempCompetencyGroup = competencyGroupService.editCompetencyGroup(this.competencyGroup);
		this.competencyGroup = tempCompetencyGroup;
		viewAllCompetencyGroup();
		return "allCompetencyGroups"; 
	}
	
	
	public void setCompetencyGroupService(
			CompetencyGroupService competencyGroupService) {
		this.competencyGroupService = competencyGroupService;
	}
	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}
	public CompetencyGroup getCompetencyGroup() {
		return competencyGroup;
	}
	public void setCompetencyGroup(CompetencyGroup competencyGroup) {
		this.competencyGroup = competencyGroup;
	}
	public String getAddEditPageMode() {
		return addEditPageMode;
	}
	public void setAddEditPageMode(String addEditPageMode) {
		this.addEditPageMode = addEditPageMode;
	}

	public List<CompetencyGroup> getCompetencyGroupList() {
		return competencyGroupList;
	}

	public void setCompetencyGroupList(List<CompetencyGroup> competencyGroupList) {
		this.competencyGroupList = competencyGroupList;
	}

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}
}
