package com.sdlc.skillMatrix.model.service.implementation;

import java.util.List;

import com.sdlc.skillMatrix.model.dao.IndustryDomainDao;
import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.service.IndustryDomainService;

public class IndustryDomainServiceImpl implements IndustryDomainService {

	private IndustryDomainDao industryDomainDao;
	/**
	 * Insert a new Industry Domain in DB
	 */
	public IndustryDomain addIndustryDomain(IndustryDomain industryDomain) {
		return industryDomainDao.insertIndustryDomain(industryDomain);
	}

	/**
	 * Update an existing Industry Domain
	 */
	public IndustryDomain editIndustryDomain(IndustryDomain industryDomain) {
		return industryDomainDao.updateIndustryDomainDetails(industryDomain);
	}

	/**
	 * return all the Industry Domain details from the DB
	 */
	public List<IndustryDomain> getAllIndustryDomains() {
		return industryDomainDao.selectAllIndustryDomains();
	}

	/**
	 * return Industry Domain by Domain Id
	 */
	public IndustryDomain getIndustryDomainbyId(Integer domainId) {
		return industryDomainDao.selectByIndustryDomainId(domainId);
	}
	
	/**
	 * Setter method for Dao, set by Spring
	 * @param industryDomainDao
	 */
	public void setIndustryDomainDao(IndustryDomainDao industryDomainDao) {
		this.industryDomainDao = industryDomainDao;
	}

}
