package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Skill;

public interface SkillService {
	public Skill addSkill(Skill skill);

	public List<Skill> getAllSkills();

	public Skill editSkill(Skill skill);

	public Skill getSkillbyId(Integer skillId);
	
	public List<Skill> getSkillsByCompetencyId(Integer competencyId);
}
