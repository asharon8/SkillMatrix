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

import com.sdlc.skillMatrix.model.dao.SkillDao;
import com.sdlc.skillMatrix.model.dao.implementation.CompetencyDaoImpl.CompetencyRowMapper;
import com.sdlc.skillMatrix.model.domain.Competency;
import com.sdlc.skillMatrix.model.domain.Skill;

public class SkillDaoImpl implements SkillDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_MAX_SKILL_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_SKILL_ID  = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_SKILLS = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_SKILL = new StringBuffer();
	private static final StringBuffer SQL_SELECT_SKILL_BY_COMPETENCY_ID = new StringBuffer();
	
	static{
		
			SQL_INSERT.append("INSERT INTO SKILL_TBL (SKILL_ID,COMPETENCY_ID,SKILL_NAME,ACTIVE_FLAG)");
			SQL_INSERT.append("VALUES(?,?,?,?)");
			
			SQL_SELECT_MAX_SKILL_ID.append("SELECT MAX(SKILL_ID) FROM SKILL_TBL");
			
			SQL_SELECT_BY_SKILL_ID.append("SELECT * FROM SKILL_TBL WHERE SKILL_ID = ?");
			
			SQL_SELECT_ALL_SKILLS.append("SELECT * FROM SKILL_TBL ");
			
			SQL_UPDATE_EXISITING_SKILL.append("UPDATE SKILL_TBL ");
			SQL_UPDATE_EXISITING_SKILL.append("SET ");
			SQL_UPDATE_EXISITING_SKILL.append("COMPETENCY_ID  = ? ,");
			SQL_UPDATE_EXISITING_SKILL.append("SKILL_NAME  = ? ,");
			SQL_UPDATE_EXISITING_SKILL.append("ACTIVE_FLAG  = ? ");
			SQL_UPDATE_EXISITING_SKILL.append("WHERE ");
			SQL_UPDATE_EXISITING_SKILL.append(" SKILL_ID  = ?");
			
			
			SQL_SELECT_SKILL_BY_COMPETENCY_ID.append("SELECT * FROM SKILL_TBL WHERE COMPETENCY_ID = ?");
		}

	private JdbcTemplate jdbcTemplate;
	
	public final class SkillRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Skill skill = new Skill();
			
			skill.setSkillId((Integer) rs.getObject("SKILL_ID"));
			skill.setCompetencyId((Integer) rs.getObject("COMPETENCY_ID"));
			skill.setSkillName((String) rs.getObject("SKILL_NAME"));
			skill.setActiveFlag((String) rs.getObject("ACTIVE_FLAG"));
			
			return skill;
		}
	}
	
	/**
	 * Get the max Skill Id and assign it to the Skill  during the insertion of Skill  details in DB
	 * 
	 * @return
	 */
	public Integer selectMaxSkillId() {

		return jdbcTemplate.queryForInt(SQL_SELECT_MAX_SKILL_ID.toString());
	}
	
	/**
	 * method to insert a new Skill  in DB
	 */
	public Skill insertSkill(final Skill skill) {
		
		final Integer tempSkillId = selectMaxSkillId() + 1;
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
				
				int param = 1;
				
				ps.setObject(param++, tempSkillId);
				ps.setObject(param++, skill.getCompetencyId());
				ps.setObject(param++, skill.getSkillName());
				ps.setObject(param++, skill.getActiveFlag());

				return ps;

			}
		});
		
		return selectBySkillId(tempSkillId);
	}

	/**
	 * Method query DB and return all the Skills
	 */
	public List<Skill> selectAllSkills() {
		
		List<Skill> allSkill = (List<Skill>) jdbcTemplate.query(SQL_SELECT_ALL_SKILLS.toString(), new SkillRowMapper());

		return (List<Skill>) allSkill;
	}

	/**
	 * Select Skill  from the database based on the Skill Id provided
	 */
	public Skill selectBySkillId(Integer skillId) {
		
		Skill skill = new Skill();

		try {
			skill = (Skill) jdbcTemplate.queryForObject(SQL_SELECT_BY_SKILL_ID.toString(), new Object[] { skillId },
					new SkillRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (skill != null)
			return (Skill) skill;
		else
			return null;
	}

	/**
	 * Select Skill  from the database based on the Skill Id provided
	 */
	public List<Skill> selectSkillsByCompetencyId(Integer competencyId) {
		
			List<Skill> allSkill = (List<Skill>) jdbcTemplate.query(SQL_SELECT_SKILL_BY_COMPETENCY_ID.toString(),new Object[] { competencyId }, new SkillRowMapper());
			return (List<Skill>) allSkill;

	}
	
	/**
	 * Update an existing Skill , Skill Id provided
	 */
	public Skill updateSkillDetails(Skill skill) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_SKILL.toString(), new Object[] {
			skill.getCompetencyId(),
			skill.getSkillName(),
			skill.getActiveFlag(), 
			skill.getSkillId() });

		return selectBySkillId(skill.getSkillId());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
