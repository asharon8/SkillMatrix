package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.CompetencyDao;
import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.service.CompetencyService;

public class CompetencyServiceImpl implements CompetencyService {

	private CompetencyDao competencyDao;
	
	/**
	 * Insert a new Competency  in DB
	 */
	public Competency addCompetency(Competency competency) {
		return competencyDao.insertCompetency(competency);
	}

	/**
	 * Update an existing Competency in DB
	 */
	public Competency editCompetency(Competency competency) {
		return competencyDao.updateCompetencyDetails(competency);
	}

	/**
	 * Get a list of all competency  from DB
	 */
	public List<Competency> getAllCompetencys() {
		return competencyDao.selectAllCompetencys();
	}

	/**
	 * Get a Competency  by its ID from DB
	 */
	public Competency getCompetencybyId(Integer competencyId) {
		return competencyDao.selectByCompetencyId(competencyId);
	}

	/**
	 * Get a Competency  by its competency group ID from DB
	 */
	public List<Competency> getCompetencysByCompetencyGroupId(
			Integer competencyGroupId) {
		return competencyDao.selectCompetencysByCompetencyGroupId(competencyGroupId);
	}
	
	public void setCompetencyDao(CompetencyDao competencyDao) {
		this.competencyDao = competencyDao;
	}

}
