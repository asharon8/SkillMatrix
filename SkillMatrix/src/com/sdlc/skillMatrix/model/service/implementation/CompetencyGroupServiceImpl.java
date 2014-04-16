package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.CompetencyGroupDao;
import com.sdlc.skillMatrix.model.domain.CompetencyGroup;
import com.sdlc.skillMatrix.model.service.CompetencyGroupService;

public class CompetencyGroupServiceImpl implements CompetencyGroupService {

	private CompetencyGroupDao competencyGroupDao;

	/**
	 * Insert a new Competency Group in DB
	 */
	public CompetencyGroup addCompetencyGroup(CompetencyGroup competencyGroup) {
		return competencyGroupDao.insertCompetencyGroup(competencyGroup);
	}

	/**
	 * Update an existing Competency Group in DB
	 */
	public CompetencyGroup editCompetencyGroup(CompetencyGroup competencyGroup) {
		return competencyGroupDao.updateCompetencyGroupDetails(competencyGroup);
	}

	/**
	 * Get a list of all competency groups from DB
	 */
	public List<CompetencyGroup> getAllCompetencyGroups() {
		return competencyGroupDao.selectAllCompetencyGroups();
	}

	/**
	 * Get a Competency Group by its ID from DB
	 */
	public CompetencyGroup getCompetencyGroupbyId(Integer competencyGroupId) {
		return competencyGroupDao.selectByCompetencyGroupId(competencyGroupId);
	}

	public void setCompetencyGroupDao(CompetencyGroupDao competencyGroupDao) {
		this.competencyGroupDao = competencyGroupDao;
	}

	public CompetencyGroup getCompetencyGroupBySkillId(Integer skillId) {
		return competencyGroupDao.selectBySkillId(skillId);
	}

}
