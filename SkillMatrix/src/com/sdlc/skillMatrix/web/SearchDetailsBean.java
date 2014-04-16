package com.sdlc.skillMatrix.web;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.SearchEmployeeSkill;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.EmployeeService;
import com.sdlc.skillMatrix.model.service.IndustryDomainService;
import com.sdlc.skillMatrix.model.service.SearchService;
import com.sdlc.skillMatrix.model.service.SkillService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class SearchDetailsBean {

	private CompetencyService competencyService ;
	
	private EmployeeService employeeService;
	
	private SkillService skillService;
	
	private SearchService searchService;
	
	private List<SearchEmployeeSkill> searchEmployeeSkillList =  new ArrayList<SearchEmployeeSkill>();
	
	private List<EmployeeSkill> searchResultList = new ArrayList<EmployeeSkill>();
	
	
	
	/**
	 * initialization method
	 * @return
	 */
	public String init(){
		
		if(this.searchEmployeeSkillList!=null){
			if(this.searchEmployeeSkillList.size()>0){
				this.searchEmployeeSkillList.clear();
			}
		}
		
		SearchEmployeeSkill tempSearchEmployeeSkill = new SearchEmployeeSkill();
		this.searchEmployeeSkillList.add(tempSearchEmployeeSkill);
		
		SkillService skillService = (SkillService) BeanUtil.getBean("skillServiceId");
		CompetencyService competencyService = (CompetencyService) BeanUtil.getBean("competencyServiceId");
		
		for (SearchEmployeeSkill ses:this.searchEmployeeSkillList){
			ses.setSkillService(skillService);
			ses.setCompetencyService(competencyService);
		}
		
		if(searchResultList!=null && searchResultList.size()>0){
			searchResultList.clear();
		}
		
		return "search";
	}
	
	/**
	 * Add a new Search Criteria
	 */
	public void addANDNewSearchCriteria(){
		SearchEmployeeSkill tempSearchEmployeeSkill = new SearchEmployeeSkill();
		tempSearchEmployeeSkill.setAndOrIndicator("AND");
		this.searchEmployeeSkillList.add(tempSearchEmployeeSkill);
		
		SkillService skillService = (SkillService) BeanUtil.getBean("skillServiceId");
		CompetencyService competencyService = (CompetencyService) BeanUtil.getBean("competencyServiceId");
		
		for (SearchEmployeeSkill ses:this.searchEmployeeSkillList){
			ses.setSkillService(skillService);
			ses.setCompetencyService(competencyService);
		}
	}
	
	/**
	 * Add a new Search Criteria
	 */
	public void addORNewSearchCriteria(){
		SearchEmployeeSkill tempSearchEmployeeSkill = new SearchEmployeeSkill();
		tempSearchEmployeeSkill.setAndOrIndicator("OR");
		this.searchEmployeeSkillList.add(tempSearchEmployeeSkill);
		
		SkillService skillService = (SkillService) BeanUtil.getBean("skillServiceId");
		CompetencyService competencyService = (CompetencyService) BeanUtil.getBean("competencyServiceId");
		
		for (SearchEmployeeSkill ses:this.searchEmployeeSkillList){
			ses.setSkillService(skillService);
			ses.setCompetencyService(competencyService);
		}
	}
	
	/**
	 * Search Method to find results based on search Criteria
	 */
	public void searchAction(){
		
		if(searchResultList!=null && searchResultList.size()>0){
			searchResultList.clear();
		}
		searchResultList = searchService.getSearchResult(searchEmployeeSkillList);
		
		EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
		
		
		for(EmployeeSkill es:searchResultList){
			
			es.setCallbackTarget(employeePersonalDetailsBean);
			es.setEmployeeName(getEmployeeNameByEmployeeId(es.getEmployeeId()));
			es.setSkillName(getSkillNameBySkillId(es.getSkillId()));
			es.setExperienceName(getExperienceNameByExperienceId(es.getExperience()));
			es.setRecencyName(getRecencyNameByRecencyId(es.getRecency()));
			es.setProficiencyName(getProficiencyNameByProficiencyId(es.getProficiency()));		
			es.setDomainName(getDomainNameByDomainId(es.getDomainId()));
			
		}
		
	}
	
	
	/**
	 * Get the domain Name based on the domain Id passed
	 * 
	 * @param domainId
	 * @return DomainName
	 */
	public String getDomainNameByDomainId(Integer domainId) {

		CodesUtility cm =(CodesUtility) BeanUtil.getBean("codesUtility");
		List<SelectItem> si = cm.getDomainCodes();
		String domainName = "";

		if(null!=domainId){
			for (int i = 0; i < si.size(); i++) {
				if (domainId.equals(si.get(i).getValue())) {
					domainName = si.get(i).getLabel();
				}
			}
		}
		
		return domainName;
	}
	
	/**
	 * Get the proficiency Name based on the proficiency code passed
	 * 
	 * @param proficiencyId
	 * @return proficiencyName
	 */
	public String getProficiencyNameByProficiencyId(Integer proficiencyId) {

		CodesUtility cm = new CodesUtility();
		List<SelectItem> si = cm.getProficiencyCodes();
		String proficiencyName = "";

		if(null!=proficiencyId){
			for (int i = 0; i < si.size(); i++) {
				if (proficiencyId.equals(si.get(i).getValue())) {
					proficiencyName = si.get(i).getLabel();
				}
			}
		}
		
		return proficiencyName;
	}
	
	/**
	 * Get the Experience Name based on the experienceId passed
	 * 
	 * @param experienceId
	 * @return experienceName
	 */
	public String getExperienceNameByExperienceId(Integer experienceId) {

		CodesUtility cm = new CodesUtility();
		List<SelectItem> si = cm.getExperienceCodes();
		String experienceName = "";

		if(experienceId!=null){
			for (int i = 0; i < si.size(); i++) {
				if (experienceId.equals(si.get(i).getValue())) {
					experienceName = si.get(i).getLabel();
				}
			}
		}
		
		return experienceName;
	}
	
	/**
	 * Get the Recency Name based on the recencyId passed
	 * 
	 * @param recencyId
	 * @return recencyName
	 */
	public String getRecencyNameByRecencyId(Integer recencyId) {

		CodesUtility cm = new CodesUtility();
		List<SelectItem> si = cm.getRecencyCodes();
		String recencyName = "";

		if(null!=recencyId){
			for (int i = 0; i < si.size(); i++) {
				if (recencyId.equals(si.get(i).getValue())) {
					recencyName = si.get(i).getLabel();
				}
			}
		}
		
		return recencyName;
	}
	
	/**
	 * Get the employee Name based on the employeeId passed
	 * 
	 * @param employeeId
	 * @return employeeName
	 */
	public String getEmployeeNameByEmployeeId(Integer employeeId) {

		List<Employee> employeeList = employeeService.getAllEmployees();
		String employeeName = "";

		if(null!=employeeId){
			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeId.equals(employeeList.get(i).getEmployeeId())) {
					employeeName = employeeList.get(i).getFirstName()+" "+employeeList.get(i).getLastName();
				}
			}
		}
		
		return employeeName;
	}
	
	/**
	 * Get the Skill Name based on theSkill Id passed
	 * 
	 * @param skillId
	 * @return skillName
	 */
	public String getSkillNameBySkillId(Integer skillId) {

		List<Skill> skillList = skillService.getAllSkills();
		String skillName = "";

		if(null!=skillId){
			for (int i = 0; i < skillList.size(); i++) {
				if (skillId.equals(skillList.get(i).getSkillId())) {
					skillName = skillList.get(i).getSkillName();
				}
			}
		}
		
		return skillName;
	}
	
	public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }
	
	

	public List<SearchEmployeeSkill> getSearchEmployeeSkillList() {
		return searchEmployeeSkillList;
	}

	public void setSearchEmployeeSkillList(
			List<SearchEmployeeSkill> searchEmployeeSkillList) {
		this.searchEmployeeSkillList = searchEmployeeSkillList;
	}

	

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public List<EmployeeSkill> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<EmployeeSkill> searchResultList) {
		this.searchResultList = searchResultList;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
