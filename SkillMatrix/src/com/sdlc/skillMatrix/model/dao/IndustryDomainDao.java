package com.sdlc.skillMatrix.model.dao;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.IndustryDomain;

public interface IndustryDomainDao {

	public IndustryDomain insertIndustryDomain(IndustryDomain industryDomain);

	public List<IndustryDomain> selectAllIndustryDomains();

	public IndustryDomain updateIndustryDomainDetails(IndustryDomain industryDomain);

	public IndustryDomain selectByIndustryDomainId(Integer domainId);
	
}
