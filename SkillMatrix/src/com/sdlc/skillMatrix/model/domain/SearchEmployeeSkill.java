package com.sdlc.skillMatrix.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.SkillService;

public class SearchEmployeeSkill {
	
	private SkillService skillService;
	private CompetencyService competencyService;

	private Integer competencyGroupId;
	private Integer competencyId;
	private Integer skillId;
	private String skillProficiency;
	private String skillExperience;
	private String skillRecency;
	private String andOrIndicator;
	
	private  List<SelectItem> competencyCodes = new ArrayList<SelectItem>();
	
	private  List<SelectItem> skillCodes = new ArrayList<SelectItem>();
	
	public Integer getCompetencyGroupId() {
		return competencyGroupId;
	}
	public void setCompetencyGroupId(Integer competencyGroupId) {
		this.competencyGroupId = competencyGroupId;
	}
	public Integer getCompetencyId() {
		return competencyId;
	}
	public void setCompetencyId(Integer competencyId) {
		this.competencyId = competencyId;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public String getSkillProficiency() {
		return skillProficiency;
	}
	public void setSkillProficiency(String skillProficiency) {
		this.skillProficiency = skillProficiency;
	}
	public String getSkillExperience() {
		return skillExperience;
	}
	public void setSkillExperience(String skillExperience) {
		this.skillExperience = skillExperience;
	}
	public String getSkillRecency() {
		return skillRecency;
	}
	public void setSkillRecency(String skillRecency) {
		this.skillRecency = skillRecency;
	}
	public String getAndOrIndicator() {
		return andOrIndicator;
	}
	public void setAndOrIndicator(String andOrIndicator) {
		this.andOrIndicator = andOrIndicator;
	}
	
	/**
	 * method that updates the competency drop down in the screen based on competency Group Selected
	 * @param event
	 */
	public void getAllCompetencyByCompetencyGroupId(ValueChangeEvent event) {
		
		Integer competencyGroupId = (Integer) event.getNewValue();
		
		
		if(competencyCodes.size()>0){
			competencyCodes.clear();
		}
		
		//List<Competency> selectedCompetency = new ArrayList<Competency>();
		
		List<Competency> allCompetency = competencyService.getAllCompetencys();
		
		for(Competency competency:allCompetency){
			if(competency.getCompetencyGroupId().equals(competencyGroupId)){
				this.competencyCodes.add(new SelectItem(competency.getCompetencyId(), competency.getCompetencyName()));
			}
		}
	}
	
	/**
	 * method that updates the SKill drop down in the screen based on competency  Selected
	 * @param event
	 */
	public void getAllSkillByCompetencyId(ValueChangeEvent event) {
		
		Integer competencyId = (Integer) event.getNewValue();
		
		if(skillCodes.size()>0){
			skillCodes.clear();
		}
		
		//List<Competency> selectedCompetency = new ArrayList<Competency>();
		
		List<Skill> allSkill = skillService.getAllSkills();
		
		for(Skill skill:allSkill){
			if(skill.getCompetencyId().equals(competencyId)){
				this.skillCodes.add(new SelectItem(skill.getSkillId(), skill.getSkillName()));
			}
		}
	}
	public List<SelectItem> getCompetencyCodes() {
		return competencyCodes;
	}
	public void setCompetencyCodes(List<SelectItem> competencyCodes) {
		this.competencyCodes = competencyCodes;
	}
	public List<SelectItem> getSkillCodes() {
		return skillCodes;
	}
	public void setSkillCodes(List<SelectItem> skillCodes) {
		this.skillCodes = skillCodes;
	}
	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}
	
	
	
}
