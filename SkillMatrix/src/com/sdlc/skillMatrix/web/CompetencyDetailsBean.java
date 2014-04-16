package com.sdlc.skillMatrix.web;

import java.util.List;

import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.SkillService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class CompetencyDetailsBean {

	private CompetencyService competencyService;
	
	private SkillService skillService;
	
	private CodesUtility codesUtility;
	
	private Competency competency;
	
	private List<Competency> competencyList;
	
	private String addEditPageMode;
	
	
	/**
	 * initialization method for Competency  Detail bean
	 * @return
	 */
	public String init(){
		
		if(competencyList!=null){
			if(competencyList.size()>0){
				competencyList.clear();
			}
		}
		
		viewAllCompetency();
		
		this.competency =  new Competency();
		return "allCompetencys";
	}
	
	/**
	 * Method that gets all the Competency from the Db and displays in the Competency  details page
	 */
	public void viewAllCompetency(){
		
		competencyList = competencyService.getAllCompetencys();
		
		CompetencyDetailsBean competencyDetailsBean = (CompetencyDetailsBean) BeanUtil.getBean("competencyDetailsBean");
		
		for (Competency competency:competencyList){
			competency.setCallbackTarget(competencyDetailsBean );
			competency.setCompetencyGroupName(getCompetencyGroupNameById(competency.getCompetencyGroupId()));
		}
	}
	
	/**
	 * Method to add a new Competency 
	 */
	public String addCompetency(){
		Competency tempCompetency = competencyService.addCompetency(this.competency);
		
		//refresh the dropdowns
		codesUtility.clearDropDowns();
				
		this.competency= tempCompetency;
		viewAllCompetency();
		return "allCompetencys";
	}
	
	/**
	 * this method redirects the system to the add edit Competency  screen
	 * @return
	 */
	public String addCompetencyAction(){
		addEditPageMode = "add";
		return "addEditCompetency";
	}
	
	/**
	 * this method redirects the system to the add edit Competency  screen
	 * @return
	 */
	public String inactivateCompetencyAction(Competency competency){
		competency.setActiveFlag("N");
		competencyService.editCompetency(competency);
		
		SkillDetailsBean skillDetailsBean = (SkillDetailsBean) BeanUtil.getBean("skillDetailsBean");
		
		List<Skill> skillByComId = skillService.getSkillsByCompetencyId(competency.getCompetencyId());
		
		if(!skillByComId.isEmpty()){
			for(Skill skill : skillByComId){
				skillDetailsBean.inactivateSkillAction(skill);
			}
		}
		
		return "allCompetencys";
	}
	
	/**
	 * method that redirects the system to the add edit screen
	 * @param Competency 
	 * @return
	 */
	public String editCompetencyAction(Competency competency){
		addEditPageMode = "edit";
		this.competency = competency;
		return "addEditCompetency";
	}
	
	/**
	 * method that updates Competency  details in DB by calling appropriate service method
	 */
	public String editCompetency(){
		Competency tempCompetency = competencyService.editCompetency(this.competency);
		this.competency = tempCompetency;
		viewAllCompetency();
		return "allCompetencys"; 
	}
	
	/**
	 * method returns competency group name for a competency group Id passed
	 * @param competencyGroupId
	 * @return
	 */
	public String getCompetencyGroupNameById(Integer competencyGroupId){
		
		String competencyGroupName = "";
		List<SelectItem> competencyGroupList = codesUtility.getCompetencyGroupCodes();
		
		for(SelectItem si:competencyGroupList){
			if(si.getValue().equals(competencyGroupId)){
				competencyGroupName = si.getLabel();
			}
		}
		return competencyGroupName;
	}

	public Competency getCompetency() {
		return competency;
	}

	public void setCompetency(Competency competency) {
		this.competency = competency;
	}

	public List<Competency> getCompetencyList() {
		return competencyList;
	}

	public void setCompetencyList(List<Competency> competencyList) {
		this.competencyList = competencyList;
	}

	public String getAddEditPageMode() {
		return addEditPageMode;
	}

	public void setAddEditPageMode(String addEditPageMode) {
		this.addEditPageMode = addEditPageMode;
	}

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}

	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
}
