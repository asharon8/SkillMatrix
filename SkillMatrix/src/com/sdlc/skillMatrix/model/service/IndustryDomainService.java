package com.sdlc.skillMatrix.model.service;

import java.util.List;

import com.sdlc.skillMatrix.model.domain.IndustryDomain;


public interface IndustryDomainService {

	public IndustryDomain addIndustryDomain(IndustryDomain industryDomain);

	public List<IndustryDomain> getAllIndustryDomains();

	public IndustryDomain editIndustryDomain(IndustryDomain industryDomain);

	public IndustryDomain getIndustryDomainbyId(Integer domainId);
	
}
