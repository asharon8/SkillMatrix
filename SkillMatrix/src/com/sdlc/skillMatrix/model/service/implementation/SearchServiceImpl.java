package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.SearchDao;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.SearchEmployeeSkill;
import com.sdlc.skillMatrix.model.service.SearchService;

public class SearchServiceImpl implements SearchService {

	private SearchDao searchDao;
	
	public List<EmployeeSkill> getSearchResult(List<SearchEmployeeSkill> searchEmployeeSkillList) {
		return searchDao.search(searchEmployeeSkillList);
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

}
