package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.Competency;

public interface CompetencyService {
	
	public Competency addCompetency(Competency competency);

	public List<Competency> getAllCompetencys();

	public Competency editCompetency(Competency competency);

	public Competency getCompetencybyId(Integer competencyId);
	
	public List<Competency> getCompetencysByCompetencyGroupId(Integer competencyGroupId);
}
