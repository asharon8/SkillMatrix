package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.SearchEmployeeSkill;

public interface SearchService {

	public List<EmployeeSkill> getSearchResult(List<SearchEmployeeSkill> searchEmployeeSkillList);
}
