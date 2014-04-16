package com.sdlc.skillMatrix.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sdlc.skillMatrix.model.domain.IndustryDomain;
import com.sdlc.skillMatrix.model.service.IndustryDomainService;

public class DomainTest {
	
	private IndustryDomainService industryDomainService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllIndustryDomains() {
		
		IndustryDomain domain = industryDomainService.getIndustryDomainbyId(1);
		fail("Not yet implemented");
	}

	@Test
	public void testGetIndustryDomainbyId() {
		fail("Not yet implemented");
	}

	public void setIndustryDomainService(IndustryDomainService industryDomainService) {
		this.industryDomainService = industryDomainService;
	}

}
