package com.sdlc.skillMatrix.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.sdlc.skillMatrix.model.dao.IndustryDomainDao;
import com.sdlc.skillMatrix.model.domain.IndustryDomain;

public class IndustryDomainDaoImpl implements IndustryDomainDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_MAX_DOMAIN_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_DOMAIN_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_DOMAINS = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_DOMAIN = new StringBuffer();
	
	static{
		
		SQL_INSERT.append("insert into domain_tbl (domain_id,domain_name,active_flag)");
		SQL_INSERT.append("values(?,?,?)");
		
		SQL_SELECT_MAX_DOMAIN_ID.append("select max(domain_id) from domain_tbl");
		
		SQL_SELECT_BY_DOMAIN_ID.append("select * from domain_tbl where domain_id = ?");
		
		SQL_SELECT_ALL_DOMAINS.append("select * from domain_tbl");
		
		SQL_UPDATE_EXISITING_DOMAIN.append("update domain_tbl ");
		SQL_UPDATE_EXISITING_DOMAIN.append("set ");
		SQL_UPDATE_EXISITING_DOMAIN.append("domain_name  = ? ,");
		SQL_UPDATE_EXISITING_DOMAIN.append("active_flag  = ? ");
		SQL_UPDATE_EXISITING_DOMAIN.append("where ");
		SQL_UPDATE_EXISITING_DOMAIN.append(" domain_id  = ?");
		
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public final class IndustryDomainRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			IndustryDomain industryDomain = new IndustryDomain();
			
			industryDomain.setDomainId((Integer) rs.getObject("DOMAIN_ID"));
			industryDomain.setDomainName((String) rs.getObject("DOMAIN_NAME"));
			industryDomain.setInactivate((String) rs.getObject("ACTIVE_FLAG"));
			
			return industryDomain;
		}
	}
	/**
	 * Get the max Industry Domain Id and assign it to the Industry Domain during the insertion of Industry Domain details in DB
	 * 
	 * @return
	 */
	public Integer selectMaxIndustryDomainId() {

		return jdbcTemplate.queryForInt(SQL_SELECT_MAX_DOMAIN_ID.toString());
	}

	/**
	 * method to insert a new Industry domain in DB
	 */
	public IndustryDomain insertIndustryDomain(final IndustryDomain industryDomain) {

		final Integer tempIndustryDomainId = selectMaxIndustryDomainId() + 1;
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
				
				int param = 1;
				
				ps.setObject(param++, tempIndustryDomainId);
				ps.setObject(param++, industryDomain.getDomainName());
				ps.setObject(param++, industryDomain.getInactivate());

				return ps;

			}
		});
		
		return selectByIndustryDomainId(tempIndustryDomainId);
	}

	/**
	 * Method query DB and return all the Industry Domain details
	 */
	public List<IndustryDomain> selectAllIndustryDomains() {
		
		List<IndustryDomain> allIndustryDomain = (List<IndustryDomain>) jdbcTemplate.query(SQL_SELECT_ALL_DOMAINS.toString(), new IndustryDomainRowMapper());

		return (List<IndustryDomain>) allIndustryDomain;
	}

	/**
	 * Select industry Domain from the database based on the domain Id provided
	 */
	public IndustryDomain selectByIndustryDomainId(Integer domainId) {
		
		IndustryDomain industryDomain = new IndustryDomain();

		try {
			industryDomain = (IndustryDomain) jdbcTemplate.queryForObject(SQL_SELECT_BY_DOMAIN_ID.toString(), new Object[] { domainId },
					new IndustryDomainRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (industryDomain != null)
			return (IndustryDomain) industryDomain;
		else
			return null;
	}

	/**
	 * Update an existing Industry Domain , domain Id provided
	 */
	public IndustryDomain updateIndustryDomainDetails(IndustryDomain industryDomain) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_DOMAIN.toString(), new Object[] { industryDomain.getDomainName(),industryDomain.getInactivate(), industryDomain.getDomainId() });

		return selectByIndustryDomainId(industryDomain.getDomainId());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
