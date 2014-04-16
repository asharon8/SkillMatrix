package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.CompetencyGroup;


public interface CompetencyGroupService {

	public CompetencyGroup addCompetencyGroup(CompetencyGroup competencyGroup);

	public List<CompetencyGroup> getAllCompetencyGroups();

	public CompetencyGroup editCompetencyGroup(CompetencyGroup competencyGroup);

	public CompetencyGroup getCompetencyGroupbyId(Integer competencyGroupId);
	
	public CompetencyGroup getCompetencyGroupBySkillId(Integer skillId);
}
