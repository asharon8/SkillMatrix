package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Competency;


public interface CompetencyDao {

	public Competency insertCompetency(Competency competency);

	public List<Competency> selectAllCompetencys();

	public Competency updateCompetencyDetails(Competency competency);

	public Competency selectByCompetencyId(Integer competencyId);
	
	public List<Competency> selectCompetencysByCompetencyGroupId(Integer competencyGroupId);
}
