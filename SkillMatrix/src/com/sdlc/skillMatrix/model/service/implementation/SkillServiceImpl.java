package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.SkillDao;
import com.sdlc.skillMatrix.model.domain.Skill;
import com.sdlc.skillMatrix.model.service.SkillService;

public class SkillServiceImpl implements SkillService {

	private SkillDao skillDao;
	
	/**
	 * Insert a new Skill  in DB
	 */
	public Skill addSkill(Skill skill) {
		return skillDao.insertSkill(skill);
	}

	/**
	 * Update an existing Skill in DB
	 */
	public Skill editSkill(Skill skill) {
		return skillDao.updateSkillDetails(skill);
	}

	/**
	 * get a list of all Skills from the DB
	 */
	public List<Skill> getAllSkills() {
		return skillDao.selectAllSkills();
	}

	/**
	 * Get a Skill by its Id
	 */
	public Skill getSkillbyId(Integer skillId) {
		return skillDao.selectBySkillId(skillId);
	}


	/* (non-Javadoc)
	 * @see com.sdlc.skillMatrix.model.service.SkillService#getSkillsByCompetencyId(java.lang.Integer)
	 */
	public List<Skill> getSkillsByCompetencyId(Integer competencyId) {
		return skillDao.selectSkillsByCompetencyId(competencyId);
	}
	
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

}
