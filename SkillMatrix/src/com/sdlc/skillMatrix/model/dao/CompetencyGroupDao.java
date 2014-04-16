package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.CompetencyGroup;


public interface CompetencyGroupDao {

	public CompetencyGroup insertCompetencyGroup(CompetencyGroup competencyGroup);

	public List<CompetencyGroup> selectAllCompetencyGroups();

	public CompetencyGroup updateCompetencyGroupDetails(CompetencyGroup competencyGroup);

	public CompetencyGroup selectByCompetencyGroupId(Integer competencyGroupId);
	
	public CompetencyGroup selectBySkillId(Integer skillId);
}
