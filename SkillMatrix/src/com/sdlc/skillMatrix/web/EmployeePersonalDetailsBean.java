package com.sdlc.skillMatrix.web;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.Employee;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.EmployeeService;
import com.sdlc.skillMatrix.model.service.EmployeeSkillService;
import com.sdlc.skillMatrix.model.service.SkillService;
import com.sdlc.skillMatrix.web.util.BeanUtil;
import com.sdlc.skillMatrix.web.util.CodesUtility;

public class EmployeePersonalDetailsBean {

	private EmployeeService employeeService;
	private CompetencyService competencyService;
	private SkillService skillService;
	private EmployeeSkillService employeeSkillService;
	private CodesUtility codesUtility;
	
	private Employee employee = new Employee();
	
	private EmployeeSkill employeeSkill = new EmployeeSkill();
	
	private  List<EmployeeSkill> allEmployeeSkillList = new ArrayList<EmployeeSkill>();
	
	private  List<SelectItem> competencyCodes = new ArrayList<SelectItem>();
	
	private  List<SelectItem> skillCodes = new ArrayList<SelectItem>();
	
	private boolean skillSelectionMode = false;
	
	private Integer competencyGroupId;
	private Integer competencyId;
	private Integer skillId;
	private String backPage;
	private Employee loginUser =  new Employee();
	
	
	/**
	 * Initialization method for the bean
	 * @param aEmployee
	 * @return
	 */
	public String init(Employee aEmployee){
		this.employee = aEmployee;
		this.employee.setEmployeeTypeDescription(getEmployeeTypeDescById(this.employee.getEmployeeType()));
		skillSelectionMode = false;
		viewAllSkills();
		competencyGroupId = null;
		competencyId = null;
		skillId = null;
		competencyCodes.clear();
		skillCodes.clear();
		
		return "employeePersonalPage";
	}
	
	/**
	 * get all the skill list for the login user
	 */
	public void viewAllSkills(){
		
		if(this.allEmployeeSkillList.size()>0){
			this.allEmployeeSkillList.clear();
		}
		
		this.allEmployeeSkillList = employeeSkillService.getEmployeeSkillbyEmployeeId(this.employee.getEmployeeId());
		
		
		//set the callback target in the domain object
		EmployeePersonalDetailsBean employeePersonalDetailsBean = (EmployeePersonalDetailsBean) BeanUtil.getBean("employeePersonalDetailsBean");
		
		for(EmployeeSkill tempEmployeeSkill:this.allEmployeeSkillList){
			
			tempEmployeeSkill.setCallbackTarget(employeePersonalDetailsBean);
			tempEmployeeSkill.setSkillName(getSkillNameById(tempEmployeeSkill.getSkillId()));
		}
		
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
	
	/**
	 * this method enables the skill selection section in the screen
	 * @return
	 */
	public String enableSkillSelectionAction(){
		skillSelectionMode = true;
		return "";
	}
	
	/**
	 * this method disables the skill selection section in the screen
	 * @return
	 */
	public String disableSkillSelectionAction(){
		skillSelectionMode = false;
		return "";
	}
	
	/**
	 * this method calls the appropriate Service method to add Skill for employee
	 */
	public void addSelectedSkillAction(){
		
		EmployeeSkill tempEmployeeSkill = new EmployeeSkill();
		
		tempEmployeeSkill.setEmployeeId(this.employee.getEmployeeId());
		tempEmployeeSkill.setSkillId(this.skillId);
		
		employeeSkillService.addEmployeeSkill(tempEmployeeSkill);
		viewAllSkills();
		
	}
	
	
	/**
	 * this method redirects the system to the add edit Skill screen
	 * @return
	 */
	public String deleteEmployeeSkillAction(EmployeeSkill employeeSkill){
		employeeSkillService.removeEmployeeSkill(employeeSkill);
		this.viewAllSkills();
		return "employeePersonalPage";
	}
	
	/**
	 * method that redirects the system to the add edit screen
	 * @param Skill 
	 * @return
	 */
	public String editEmployeeSkillAction(EmployeeSkill aEmployeeSkill){

		employeeSkillService.editEmployeeSkill(aEmployeeSkill);
		viewAllSkills();
		return "employeePersonalPage";
	}
	
	/**
	 * used in search Screen
	 * @param employeeSkill
	 * @return
	 */
	public String gotoEmployeePersonalPage(EmployeeSkill employeeSkill){
		this.backPage = "search";
		Employee employee  = employeeService.getEmployeebyId(employeeSkill.getEmployeeId());
		return init(employee);
	}
	
	public String returnToBackPage(){
		return this.backPage;
	}
	
	/**
	 * method that updates Skill details in DB by calling appropriate service method
	 */
	public String editEmployeeSkill(){
		EmployeeSkill tempEmployeeSkill = employeeSkillService.editEmployeeSkill(this.employeeSkill);
		this.employeeSkill = tempEmployeeSkill;
		viewAllSkills();
		return "employeePersonalPage"; 
	}
	
	/**
	 * This method redirects the page to the edit employee page where employee can edit his/her personal details
	 * @return
	 */
	public String editEmployeePersonalDetailAction(){
		EmployeeDetailsBean employeeDetailsBean = (EmployeeDetailsBean) BeanUtil.getBean("employeeDetailsBean");
		employeeDetailsBean.init();
		employeeDetailsBean.setBackPageReference("employeePersonalDetailPage");
		return employeeDetailsBean.editEmployeeAction(this.employee);
		//return "addEditEmployee";
	}
	
	/**
	 * method returns competetncy name for a competency  Id passed
	 * @param competencyId
	 * @return
	 */
	public String getSkillNameById(Integer skillId){
		
		String skillName = "";
		List<SelectItem> skillList = codesUtility.getSkillCodes();
		
		for(SelectItem si:skillList){
			if(si.getValue().equals(skillId)){
				skillName = si.getLabel();
			}
		}
		return skillName;
	}
	
	/**
	 * method returns employee type description for a type  Id passed
	 * @param employee type description
	 * @return
	 */
	public String getEmployeeTypeDescById(String employeeType){
		
		String employeeTypeDesc = "";
		List<SelectItem> employeeTypeDesList = codesUtility.getEmployeeType();
		
		for(SelectItem si:employeeTypeDesList){
			if(si.getValue().equals(employeeType)){
				employeeTypeDesc = si.getLabel();
			}
		}
		return employeeTypeDesc;
	}
	
	
	
	public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
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

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

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

	public boolean isSkillSelectionMode() {
		return skillSelectionMode;
	}

	public void setSkillSelectionMode(boolean skillSelectionMode) {
		this.skillSelectionMode = skillSelectionMode;
	}

	public EmployeeSkill getEmployeeSkill() {
		return employeeSkill;
	}

	public void setEmployeeSkill(EmployeeSkill employeeSkill) {
		this.employeeSkill = employeeSkill;
	}

	public List<EmployeeSkill> getAllEmployeeSkillList() {
		return allEmployeeSkillList;
	}

	public void setAllEmployeeSkillList(List<EmployeeSkill> allEmployeeSkillList) {
		this.allEmployeeSkillList = allEmployeeSkillList;
	}

	public void setEmployeeSkillService(EmployeeSkillService employeeSkillService) {
		this.employeeSkillService = employeeSkillService;
	}

	public void setCodesUtility(CodesUtility codesUtility) {
		this.codesUtility = codesUtility;
	}

	public String getBackPage() {
		return backPage;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}

	public Employee getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Employee loginUser) {
		this.loginUser = loginUser;
	}
}
