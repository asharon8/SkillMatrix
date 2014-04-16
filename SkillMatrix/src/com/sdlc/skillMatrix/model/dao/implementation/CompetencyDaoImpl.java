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

import com.sdlc.skillMatrix.model.dao.CompetencyDao;
import com.sdlc.skillMatrix.model.domain.Competency;

public class CompetencyDaoImpl implements CompetencyDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_MAX_COMPETENCY_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_COMPETENCY_ID  = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_COMPETENCYS = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_COMPETENCY = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_COMPETENCY_GROUP_ID  = new StringBuffer();
	static{
		
		SQL_INSERT.append("INSERT INTO COMPETENCY_TBL (COMPETENCY_ID,COMPETENCY_GROUP_ID,COMPETENCY_NAME,ACTIVE_FLAG)");
		SQL_INSERT.append("VALUES(?,?,?,?)");
		
		SQL_SELECT_MAX_COMPETENCY_ID.append("SELECT MAX(COMPETENCY_ID) FROM COMPETENCY_TBL");
		
		SQL_SELECT_BY_COMPETENCY_ID.append("SELECT * FROM COMPETENCY_TBL WHERE COMPETENCY_ID = ?");
		
		SQL_SELECT_ALL_COMPETENCYS.append("SELECT * FROM COMPETENCY_TBL");
		
		SQL_UPDATE_EXISITING_COMPETENCY.append("UPDATE COMPETENCY_TBL ");
		SQL_UPDATE_EXISITING_COMPETENCY.append("SET ");
		SQL_UPDATE_EXISITING_COMPETENCY.append("COMPETENCY_GROUP_ID  = ? ,");
		SQL_UPDATE_EXISITING_COMPETENCY.append("COMPETENCY_NAME  = ? ,");
		SQL_UPDATE_EXISITING_COMPETENCY.append("ACTIVE_FLAG  = ? ");
		SQL_UPDATE_EXISITING_COMPETENCY.append("WHERE ");
		SQL_UPDATE_EXISITING_COMPETENCY.append(" COMPETENCY_ID  = ?");
		
		SQL_SELECT_BY_COMPETENCY_GROUP_ID.append("SELECT * FROM COMPETENCY_TBL WHERE COMPETENCY_GROUP_ID = ?");
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public final class CompetencyRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Competency competency = new Competency();
			
			competency.setCompetencyId((Integer) rs.getObject("COMPETENCY_ID"));
			competency.setCompetencyGroupId((Integer) rs.getObject("COMPETENCY_GROUP_ID"));
			competency.setCompetencyName((String) rs.getObject("COMPETENCY_NAME"));
			competency.setActiveFlag((String) rs.getObject("ACTIVE_FLAG"));
			
			return competency;
		}
	}
	
	/**
	 * Get the max Competency Id and assign it to the Competency  during the insertion of Competency  details in DB
	 * 
	 * @return
	 */
	public Integer selectMaxCompetencyId() {

		return jdbcTemplate.queryForInt(SQL_SELECT_MAX_COMPETENCY_ID.toString());
	}
	
	/**
	 * method to insert a new Competency  in DB
	 */
	public Competency insertCompetency(final Competency competency) {
		
		final Integer tempCompetencyId = selectMaxCompetencyId() + 1;
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
				
				int param = 1;
				
				ps.setObject(param++, tempCompetencyId);
				ps.setObject(param++, competency.getCompetencyGroupId());
				ps.setObject(param++, competency.getCompetencyName());
				ps.setObject(param++, competency.getActiveFlag());

				return ps;

			}
		});
		
		return selectByCompetencyId(tempCompetencyId);
	}

	/**
	 * Method query DB and return all the Competencies
	 */
	public List<Competency> selectAllCompetencys() {
		
		List<Competency> allCompetency = (List<Competency>) jdbcTemplate.query(SQL_SELECT_ALL_COMPETENCYS.toString(), new CompetencyRowMapper());

		return (List<Competency>) allCompetency;
	}
	
	/**
	 * Method query DB and return all the Competencies
	 */
	public List<Competency> selectCompetencysByCompetencyGroupId(Integer competencyGroupId) {
		
		List<Competency> allCompetency = (List<Competency>) jdbcTemplate.query(SQL_SELECT_BY_COMPETENCY_GROUP_ID.toString(), new Object[] { competencyGroupId }, new CompetencyRowMapper());

		return (List<Competency>) allCompetency;
	}


	/**
	 * Select Competency  from the database based on the Competency Id provided
	 */
	public Competency selectByCompetencyId(Integer competencyId) {
		
		Competency competency = new Competency();

		try {
			competency = (Competency) jdbcTemplate.queryForObject(SQL_SELECT_BY_COMPETENCY_ID.toString(), new Object[] { competencyId },
					new CompetencyRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (competency != null)
			return (Competency) competency;
		else
			return null;
	}

	/**
	 * Update an existing Competency , Competency Id provided
	 */
	public Competency updateCompetencyDetails(Competency competency) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_COMPETENCY.toString(), new Object[] {competency.getCompetencyGroupId(),competency.getCompetencyName(),competency.getActiveFlag(), competency.getCompetencyId() });

		return selectByCompetencyId(competency.getCompetencyGroupId());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
