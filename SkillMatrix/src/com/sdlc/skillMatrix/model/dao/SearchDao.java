package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.SearchEmployeeSkill;

public interface SearchDao {

	public List<EmployeeSkill> search(List<SearchEmployeeSkill> searchEmployeeSkillList);
}
