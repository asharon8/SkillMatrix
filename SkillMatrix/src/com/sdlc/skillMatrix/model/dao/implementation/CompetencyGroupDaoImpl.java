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

import com.sdlc.skillMatrix.model.dao.CompetencyGroupDao;
import com.sdlc.skillMatrix.model.domain.CompetencyGroup;

public class CompetencyGroupDaoImpl implements CompetencyGroupDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_MAX_COMPETENCY_GROUP_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_COMPETENCY_GROUP_ID  = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_COMPETENCY_GROUPS = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_COMPETENCY_GROUP = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_SKILL_ID  = new StringBuffer();
	static{
	
		
		SQL_INSERT.append("INSERT INTO COMPETENCY_GROUP_TBL (COMPETENCY_GROUP_ID,COMPETENCY_GROUP_NAME,ACTIVE_FLAG)");
		SQL_INSERT.append("VALUES(?,?,?)");
		
		SQL_SELECT_MAX_COMPETENCY_GROUP_ID.append("SELECT MAX(COMPETENCY_GROUP_ID) FROM COMPETENCY_GROUP_TBL");
		
		SQL_SELECT_BY_COMPETENCY_GROUP_ID.append("SELECT * FROM COMPETENCY_GROUP_TBL WHERE COMPETENCY_GROUP_ID = ?");
		
		SQL_SELECT_ALL_COMPETENCY_GROUPS.append("SELECT * FROM COMPETENCY_GROUP_TBL");
		
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append("UPDATE COMPETENCY_GROUP_TBL ");
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append("SET ");
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append("COMPETENCY_GROUP_NAME  = ? ,");
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append("ACTIVE_FLAG  = ? ");
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append("WHERE ");
		SQL_UPDATE_EXISITING_COMPETENCY_GROUP.append(" COMPETENCY_GROUP_ID  = ?");
		
		SQL_SELECT_BY_SKILL_ID.append("SELECT * FROM COMPETENCY_GROUP_TBL ");
		SQL_SELECT_BY_SKILL_ID.append("WHERE COMPETENCY_GROUP_ID IN  ");
		SQL_SELECT_BY_SKILL_ID.append("( ");
		SQL_SELECT_BY_SKILL_ID.append("		SELECT COMPETENCY_GROUP_ID  ");
		SQL_SELECT_BY_SKILL_ID.append("		FROM COMPETENCY_TBL  ");
		SQL_SELECT_BY_SKILL_ID.append("		WHERE COMPETENCY_ID IN ");
		SQL_SELECT_BY_SKILL_ID.append("			( ");
		SQL_SELECT_BY_SKILL_ID.append("				SELECT COMPETENCY_ID  ");
		SQL_SELECT_BY_SKILL_ID.append("				FROM SKILL_TBL  ");
		SQL_SELECT_BY_SKILL_ID.append("				WHERE SKILL_ID = ? ");
		SQL_SELECT_BY_SKILL_ID.append("			) ");
		SQL_SELECT_BY_SKILL_ID.append(")");
		
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public final class CompetencyGroupRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CompetencyGroup competencyGroup = new CompetencyGroup();
			
			competencyGroup.setCompetencyGroupId((Integer) rs.getObject("COMPETENCY_GROUP_ID"));
			competencyGroup.setCompetencyGroupName((String) rs.getObject("COMPETENCY_GROUP_NAME"));
			competencyGroup.setActiveFlag((String) rs.getObject("ACTIVE_FLAG"));
			
			return competencyGroup;
		}
	}
	
	/**
	 * Get the max Competency Group Id and assign it to the Competency Group during the insertion of Competency Group details in DB
	 * 
	 * @return
	 */
	public Integer selectMaxCompetencyGroupId() {

		return jdbcTemplate.queryForInt(SQL_SELECT_MAX_COMPETENCY_GROUP_ID.toString());
	}

	/**
	 * method to insert a new Competency Group in DB
	 */
	public CompetencyGroup insertCompetencyGroup(final CompetencyGroup competencyGroup) {

		final Integer tempCompetencyGroupId = selectMaxCompetencyGroupId() + 1;
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
				
				int param = 1;
				
				ps.setObject(param++, tempCompetencyGroupId);
				ps.setObject(param++, competencyGroup.getCompetencyGroupName());
				ps.setObject(param++, competencyGroup.getActiveFlag());

				return ps;

			}
		});
		
		return selectByCompetencyGroupId(tempCompetencyGroupId);
	}

	/**
	 * Method query DB and return all the Competency Groups
	 */
	public List<CompetencyGroup> selectAllCompetencyGroups() {
		
		List<CompetencyGroup> allCompetencyGroup = (List<CompetencyGroup>) jdbcTemplate.query(SQL_SELECT_ALL_COMPETENCY_GROUPS.toString(), new CompetencyGroupRowMapper());

		return (List<CompetencyGroup>) allCompetencyGroup;
	}

	/**
	 * Select Competency Group from the database based on the Competency Group Id provided
	 */
	public CompetencyGroup selectByCompetencyGroupId(Integer competencyGroupId) {
		
		CompetencyGroup competencyGroup = new CompetencyGroup();

		try {
			competencyGroup = (CompetencyGroup) jdbcTemplate.queryForObject(SQL_SELECT_BY_COMPETENCY_GROUP_ID.toString(), new Object[] { competencyGroupId },
					new CompetencyGroupRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (competencyGroup != null)
			return (CompetencyGroup) competencyGroup;
		else
			return null;
	}
	
	/**
	 * Select Competency Group from the database based on the Skill Id provided
	 */
	public CompetencyGroup selectBySkillId(Integer skillId) {
		
		CompetencyGroup competencyGroup = new CompetencyGroup();

		try {
			competencyGroup = (CompetencyGroup) jdbcTemplate.queryForObject(SQL_SELECT_BY_SKILL_ID.toString(), new Object[] { skillId },
					new CompetencyGroupRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (competencyGroup != null)
			return (CompetencyGroup) competencyGroup;
		else
			return null;
	}
	

	/**
	 * Update an existing Competency Group , Competency Group Id provided
	 */
	public CompetencyGroup updateCompetencyGroupDetails(CompetencyGroup competencyGroup) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_COMPETENCY_GROUP.toString(), new Object[] { competencyGroup.getCompetencyGroupName(),competencyGroup.getActiveFlag(), competencyGroup.getCompetencyGroupId() });

		return selectByCompetencyGroupId(competencyGroup.getCompetencyGroupId());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
