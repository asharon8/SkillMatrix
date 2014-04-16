package com.sdlc.skillMatrix.model.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.sdlc.skillMatrix.model.dao.SearchDao;
import com.sdlc.skillMatrix.model.dao.implementation.CompetencyDaoImpl.CompetencyRowMapper;
import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.SearchEmployeeSkill;

public class SearchDaoImpl implements SearchDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	private static final StringBuffer SQL_SEARCH_SELECT = new StringBuffer();
	
	static{
		SQL_SEARCH_SELECT.append("SELECT * FROM EMPLOYEE_SKILL_TBL AS e1");  
	}
	
	public final class SearchResultEmployeeSkillRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			EmployeeSkill employeeSkill = new EmployeeSkill();
			
			employeeSkill.setEmployeeId((Integer)rs.getObject("EMPLOYEE_ID"));
			employeeSkill.setSkillId((Integer)rs.getObject("SKILL_ID"));
			employeeSkill.setDomainId((Integer)rs.getObject("DOMAIN_ID"));
			employeeSkill.setProficiency((Integer) rs.getObject("PROFIENCY"));
			employeeSkill.setExperience((Integer) rs.getObject("EXPERIENCE"));
			employeeSkill.setRecency((Integer)rs.getObject("RECENCY"));
			employeeSkill.setComments((String) rs.getObject("COMMENT"));
			
			return employeeSkill;
		}
	}
	
	/**
	 * Method that dynamically build the search query and executes it...
	 */
	public List<EmployeeSkill> search(List<SearchEmployeeSkill> searchEmployeeSkillList) {
		System.out.println("searchEmployeeSkillList.size() -- "+searchEmployeeSkillList.size());
		List<Integer> skillIdList =  new ArrayList();
		int counter = 1;
		String skillIdAppendQuery = "";
		
		StringBuffer queryStringBuff = new StringBuffer();
		queryStringBuff.append(SQL_SEARCH_SELECT);
		
		if(searchEmployeeSkillList.size()>0){
			queryStringBuff.append(" WHERE ");
		}
		
		
		queryStringBuff.append(" ( ");
		for (SearchEmployeeSkill ses:searchEmployeeSkillList){
			
			
			if(ses.getAndOrIndicator()!=null && ses.getAndOrIndicator().equalsIgnoreCase("OR")){
				queryStringBuff.append(" OR ");
			}else if(ses.getAndOrIndicator()!=null && ses.getAndOrIndicator().equalsIgnoreCase("AND")){
				queryStringBuff.append(" AND ");
			}
			
			queryStringBuff.append(" (");
			queryStringBuff.append(" SELECT count(*) FROM EMPLOYEE_SKILL_TBL AS inner_e");
			queryStringBuff.append(" WHERE e1.EMPLOYEE_ID = inner_e.EMPLOYEE_ID");
			queryStringBuff.append(" AND ");
			
			if(ses.getSkillId()!=null){
				queryStringBuff.append(" SKILL_ID ").append("=").append(ses.getSkillId());
				skillIdList.add(ses.getSkillId());
			}
			
			
			if(ses.getSkillProficiency()!=null){
				queryStringBuff.append(" AND ");
				queryStringBuff.append(" PROFIENCY ").append(">=").append(Integer.parseInt(ses.getSkillProficiency()));
			}
			
			if(ses.getSkillExperience()!=null){
				queryStringBuff.append(" AND ");
				queryStringBuff.append(" EXPERIENCE ").append(">=").append(Integer.parseInt(ses.getSkillExperience().trim()));
			}
			
			if(ses.getSkillRecency()!=null){
				queryStringBuff.append(" AND ");
				queryStringBuff.append(" RECENCY ").append("<=").append(Integer.parseInt(ses.getSkillRecency().trim()));
			}
			
			queryStringBuff.append(" ) ");
			queryStringBuff.append(" >=1 ");
			
			
		}
		
		queryStringBuff.append(" ) ");
		
		queryStringBuff.append(" AND");
		queryStringBuff.append(" e1.SKILL_ID IN ");
		
		skillIdAppendQuery = skillIdAppendQuery + "(";
		 for(Integer i:skillIdList){
			 
			 skillIdAppendQuery = skillIdAppendQuery+i;
			 
			 if((skillIdList.size()-counter)>=1){
				 skillIdAppendQuery = skillIdAppendQuery+","; 
			 }
			 
			 counter = counter+1;
		 }
		 skillIdAppendQuery = skillIdAppendQuery + ")";
		 
		 queryStringBuff.append(skillIdAppendQuery);
		 
		
		List<EmployeeSkill> allCompetency = (List<EmployeeSkill>) jdbcTemplate.query(queryStringBuff.toString(), new SearchResultEmployeeSkillRowMapper());

		System.out.println("queryStringBuff - "+queryStringBuff.toString());
		
		return allCompetency;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
