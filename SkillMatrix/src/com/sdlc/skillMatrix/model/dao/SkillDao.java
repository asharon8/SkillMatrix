package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Skill;


public interface SkillDao {

	public Skill insertSkill(Skill skill);

	public List<Skill> selectAllSkills();

	public Skill updateSkillDetails(Skill skill);

	public Skill selectBySkillId(Integer skillId);
	
	public List<Skill> selectSkillsByCompetencyId(Integer competencyId);
}
