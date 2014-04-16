package com.sdlc.skillMatrix.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.CompetencyGroup;
import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.CompetencyGroupService;
import com.sdlc.skillMatrix.model.service.CompetencyService;
import com.sdlc.skillMatrix.model.service.IndustryDomainService;
import com.sdlc.skillMatrix.model.service.SkillService;



public class CodesUtility {
	
	private CompetencyGroupService competencyGroupService;
	private CompetencyService competencyService;
	private IndustryDomainService industryDomainService;
	private SkillService skillService;
	
	private static List<SelectItem> activateInactivateDropDownCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> yesNoDropDownCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> employeeRolesCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> employeeTypeCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> competencyGroupCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> competencyCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> skillCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> domainCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> proficiencyCodes = new ArrayList<SelectItem>();
	private static List<SelectItem> experienceCodes  =  new ArrayList<SelectItem>();
	private static List<SelectItem> recencyCodes  =  new ArrayList<SelectItem>();
	
	
	public List<SelectItem> getCompetencyGroupCodes() {
		synchronized (competencyGroupCodes) {
			if(competencyGroupCodes.size() == 0) {
				
				List<CompetencyGroup> competencyGroupList= competencyGroupService.getAllCompetencyGroups();
				
				competencyGroupCodes.add(new SelectItem("0", ""));
				for(CompetencyGroup competencyGroup : competencyGroupList) {
					competencyGroupCodes.add(new SelectItem(competencyGroup.getCompetencyGroupId(), competencyGroup.getCompetencyGroupName()));
				}
			}
		}
		
		return Collections.unmodifiableList(competencyGroupCodes);
	}
	
	public List<SelectItem> getCompetencyCodes() {
		synchronized (competencyCodes) {
			if(competencyCodes.size() == 0) {
				
				List<Competency> competencyList= competencyService.getAllCompetencys();
				
				competencyCodes.add(new SelectItem("0", ""));
				for(Competency competency : competencyList) {
					competencyCodes.add(new SelectItem(competency.getCompetencyId(), competency.getCompetencyName()));
				}
			}
		}
		
		return Collections.unmodifiableList(competencyCodes);
	}
	
	public List<SelectItem> getSkillCodes() {
		synchronized (skillCodes) {
			if(skillCodes.size() == 0) {
				
				List<Skill> skillList= skillService.getAllSkills();
				
				skillCodes.add(new SelectItem("0", ""));
				for(Skill skill : skillList) {
					skillCodes.add(new SelectItem(skill.getSkillId(), skill.getSkillName()));
				}
			}
		}
		
		return Collections.unmodifiableList(skillCodes);
	}

	public List<SelectItem> getActivateInactivateCodes() {
		
		synchronized (activateInactivateDropDownCodes) {
			if (activateInactivateDropDownCodes.size() == 0) {
				activateInactivateDropDownCodes.add(new SelectItem("N", "Inactivate"));
				activateInactivateDropDownCodes.add(new SelectItem("Y", "Activate"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(activateInactivateDropDownCodes);
	}
	
	public List<SelectItem> getYesNoCodes() {
		
		synchronized (yesNoDropDownCodes) {
			if (yesNoDropDownCodes.size() == 0) {
				yesNoDropDownCodes.add(new SelectItem("Y", "Yes"));
				yesNoDropDownCodes.add(new SelectItem("N", "No"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(yesNoDropDownCodes);
	}
	
	public List<SelectItem> getEmployeeRoles() {
		
		synchronized (employeeRolesCodes) {
			if (employeeRolesCodes.size() == 0) {
				employeeRolesCodes.add(new SelectItem("", ""));
				employeeRolesCodes.add(new SelectItem("1", "Manager"));
				employeeRolesCodes.add(new SelectItem("2", "Employee"));
				employeeRolesCodes.add(new SelectItem("3", "Administrator"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(employeeRolesCodes);
	}
	
	public List<SelectItem> getEmployeeType() {
		
		synchronized (employeeTypeCodes) {
			if (employeeTypeCodes.size() == 0) {
				employeeTypeCodes.add(new SelectItem("0", ""));
				employeeTypeCodes.add(new SelectItem("1", "Consultant I"));
				employeeTypeCodes.add(new SelectItem("2", "Consultant II"));
				employeeTypeCodes.add(new SelectItem("3", "Senior Consultant"));
				employeeTypeCodes.add(new SelectItem("4", "Principal Consultant"));
				employeeTypeCodes.add(new SelectItem("5", "Others"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(employeeTypeCodes);
	}
	
	
	public List<SelectItem> getDomainCodes() {
		
		synchronized (domainCodes) {
			
			if(domainCodes.size() == 0) {
				
				List<IndustryDomain> industryDomainList= industryDomainService.getAllIndustryDomains();
				
				domainCodes.add(new SelectItem("0", ""));
				for(IndustryDomain industryDomain : industryDomainList) {
					domainCodes.add(new SelectItem(industryDomain.getDomainId(), industryDomain.getDomainName()));
				}
			}
			
		}
		
		return Collections.unmodifiableList(domainCodes);
	} 
	
	public List<SelectItem> getProficiencyCodes() {
		
		synchronized (proficiencyCodes) {
			
			if (proficiencyCodes.size() == 0) {
				
				proficiencyCodes.add(new SelectItem(0, ""));
				proficiencyCodes.add(new SelectItem(1, "1 - Novice"));
				proficiencyCodes.add(new SelectItem(2, "2 - Limited"));
				proficiencyCodes.add(new SelectItem(3, "3 - Experienced"));
				proficiencyCodes.add(new SelectItem(4, "4 - Expert"));
				proficiencyCodes.add(new SelectItem(5, "5 - Advanced"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(proficiencyCodes);
	} 
	
	public List<SelectItem> getExperienceCodes() {
		
		synchronized (experienceCodes) {
			
			if (experienceCodes.size() == 0) {
				
				experienceCodes.add(new SelectItem(0, ""));
				experienceCodes.add(new SelectItem(1, "1 year"));
				experienceCodes.add(new SelectItem(2, "2 yrs"));
				experienceCodes.add(new SelectItem(3, "3 yrs"));
				experienceCodes.add(new SelectItem(4, "4 yrs"));
				experienceCodes.add(new SelectItem(5, "5 yrs"));
				experienceCodes.add(new SelectItem(6, "6 yrs"));
				experienceCodes.add(new SelectItem(7, "7 yrs"));
				experienceCodes.add(new SelectItem(8, "8 yrs"));
				experienceCodes.add(new SelectItem(9, "9 yrs"));
				experienceCodes.add(new SelectItem(10, "10 yrs"));
				experienceCodes.add(new SelectItem(11, "10+ yrs"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(experienceCodes);
	}
	
	public List<SelectItem> getRecencyCodes() {
		
		synchronized (recencyCodes) {
			
			if (recencyCodes.size() == 0) {
				
				recencyCodes.add(new SelectItem(0, ""));
				recencyCodes.add(new SelectItem(1, "1 month"));
				recencyCodes.add(new SelectItem(2, "2 months"));
				recencyCodes.add(new SelectItem(3, "3 months"));
				recencyCodes.add(new SelectItem(6, "6 months"));
				recencyCodes.add(new SelectItem(12, "1 year"));
				recencyCodes.add(new SelectItem(18, "1.5 yrs"));
				recencyCodes.add(new SelectItem(24, "2 yrs"));
				recencyCodes.add(new SelectItem(36, "3 yrs"));
				recencyCodes.add(new SelectItem(100, "More than 3 yrs"));
			}
		}

		return (List<SelectItem>) Collections.unmodifiableList(recencyCodes);
	}
	
	public void clearDropDowns(){
		this.competencyCodes.clear();
		this.competencyGroupCodes.clear();
		this.competencyCodes.clear();
		this.skillCodes.clear();
		this.domainCodes.clear();
	}

	public static List<SelectItem> getYesNoDropDownCodes() {
		return yesNoDropDownCodes;
	}

	public static void setYesNoDropDownCodes(List<SelectItem> yesNoDropDownCodes) {
		CodesUtility.yesNoDropDownCodes = yesNoDropDownCodes;
	}

	public void setCompetencyGroupService(
			CompetencyGroupService competencyGroupService) {
		this.competencyGroupService = competencyGroupService;
	}

	public void setCompetencyService(CompetencyService competencyService) {
		this.competencyService = competencyService;
	}

	public void setIndustryDomainService(IndustryDomainService industryDomainService) {
		this.industryDomainService = industryDomainService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
}
