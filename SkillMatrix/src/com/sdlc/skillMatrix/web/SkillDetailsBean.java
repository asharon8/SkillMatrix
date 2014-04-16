package com.sdlc.skillMatrix.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.CompetencyGroup;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.CompetencyGroupService;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.SkillService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class SkillDetailsBean {

	private SkillService skillService;
	
	private CodesUtility codesUtility;
	
	private CompetencyService competencyService;
	
	private CompetencyGroupService competencyGroupService;
	
	private Skill skill;
	
	private List<Skill> skillList;
	
	private String addEditPageMode;
	
	private Integer competencyGroupId;
	
	private  List<SelectItem> competencyCodes = new ArrayList<SelectItem>();
	
	private  List<Competency> allCompetencys = new ArrayList<Competency>();
	
	/**
	 * initialization method for Skill  Detail bean
	 * @return
	 */
	public String init(){
		
		if(skillList!=null){
			if(skillList.size()>0){
				skillList.clear();
			}
		}
		
		viewAllSkill();
		
		this.getAllCompetency();
		
		this.skill =  new Skill();
		return "allSkills";
	}
	
	/**
	 * Method that gets all the Skill from the Db and displays in the Skill  details page
	 */
	public void viewAllSkill(){
		
		skillList = skillService.getAllSkills();
		
		SkillDetailsBean skillDetailsBean = (SkillDetailsBean) BeanUtil.getBean("skillDetailsBean");
		
		for (Skill skill:skillList){
			skill.setCallbackTarget(skillDetailsBean );
			skill.setCompetencyName(getCompetencyNameById(skill.getCompetencyId()));
		}
	}
	
	/**
	 * Method to add a new Skill 
	 */
	public String addSkill(){
		Skill tempSkill = skillService.addSkill(this.skill);
		//refresh the dropdowns
		codesUtility.clearDropDowns();
				
		this.skill= tempSkill;
		viewAllSkill();
		return "allSkills";
	}
	
	/**
	 * this method redirects the system to the add edit Skill  screen
	 * @return
	 */
	public String addSkillAction(){
		addEditPageMode = "add";
		return "addEditSkill";
	}
	
	/**
	 * this method redirects the system to the add edit Skill screen
	 * @return
	 */
	public String inactivateSkillAction(Skill skill){
		skill.setActiveFlag("N");
		skillService.editSkill(skill);
		return "allSkills";
	}
	
	/**
	 * method that redirects the system to the add edit screen
	 * @param Skill 
	 * @return
	 */
	public String editSkillAction(Skill skill){
		addEditPageMode = "edit";
		this.skill = skill;
		CompetencyGroup competencyGroup = competencyGroupService.getCompetencyGroupBySkillId(skill.getSkillId());
		
		//get the competency group id for the skill
		this.competencyGroupId  = competencyGroup.getCompetencyGroupId();
		
		this.competencyCodes.clear();
		//update the competency codes based on competency group id
		for(Competency competency:this.allCompetencys){
			if(competency.getCompetencyGroupId().equals(competencyGroupId)){
				this.competencyCodes.add(new SelectItem(competency.getCompetencyId(), competency.getCompetencyName()));
			}
		}
		
		return "addEditSkill";
	}
	
	/**
	 * method that updates Skill details in DB by calling appropriate service method
	 */
	public String editSkill(){
		Skill tempSkill = skillService.editSkill(this.skill);
		this.skill = tempSkill;
		viewAllSkill();
		return "allSkills"; 
	}
	
	/**
	 * method returns competetncy name for a competency  Id passed
	 * @param competencyId
	 * @return
	 */
	public String getCompetencyNameById(Integer competencyId){
		
		String competencyName = "";
		List<SelectItem> competencyList = codesUtility.getCompetencyCodes();
		
		for(SelectItem si:competencyList){
			if(si.getValue().equals(competencyId)){
				competencyName = si.getLabel();
			}
		}
		return competencyName;
	}
	/**
	 * method that updates the competency drop down in the screen based on competency Group Selected
	 * @param event
	 */
	public void updateDisplayedCompetency(ValueChangeEvent event) {
		Integer competencyGroupId = (Integer) event.getNewValue();
		
		if(competencyCodes.size()>0){
			competencyCodes.clear();
		}
		
		//List<Competency> selectedCompetency = new ArrayList<Competency>();
		
		List<Competency> allCompetency = this.allCompetencys;//competencyService.getAllCompetencys();
		
		for(Competency competency:allCompetency){
			if(competency.getCompetencyGroupId().equals(competencyGroupId)){
				this.competencyCodes.add(new SelectItem(competency.getCompetencyId(), competency.getCompetencyName()));
			}
		}
		
	}
	/**
	 * Get all competency
	 */
	private void getAllCompetency(){
		this.allCompetencys = competencyService.getAllCompetencys();
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	public String getAddEditPageMode() {
		return addEditPageMode;
	}

	public void setAddEditPageMode(String addEditPageMode) {
		this.addEditPageMode = addEditPageMode;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}

	public Integer getCompetencyGroupId() {
		return competencyGroupId;
	}

	public void setCompetencyGroupId(Integer competencyGroupId) {
		this.competencyGroupId = competencyGroupId;
	}

	

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}

	public List<SelectItem> getCompetencyCodes() {
		return competencyCodes;
	}

	public void setCompetencyCodes(List<SelectItem> competencyCodes) {
		this.competencyCodes = competencyCodes;
	}

	public void setCompetencyGroupService(
			CompetencyGroupService competencyGroupService) {
		this.competencyGroupService = competencyGroupService;
	}
}
