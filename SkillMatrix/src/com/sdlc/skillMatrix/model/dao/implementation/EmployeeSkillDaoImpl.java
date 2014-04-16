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

import com.sdlc.skillMatrix.model.dao.EmployeeSkillDao;
import com.sdlc.skillMatrix.model.dao.implementation.SkillDaoImpl.SkillRowMapper;
import com.sdlc.skillMatrix.model.domain.EmployeeSkill;
import com.sdlc.skillMatrix.model.domain.Skill;

public class EmployeeSkillDaoImpl implements EmployeeSkillDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_EMPLOYEE_ID_SKILL_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_SKILL_ID  = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_EMPLOYEE_ID  = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_EMPLOYEE_SKILLS = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_EMPLOYEE_SKILL = new StringBuffer();
	private static final StringBuffer SQL_DELETE_EXISITING_EMPLOYEE_SKILL = new StringBuffer();
	
	static{

		SQL_INSERT.append("INSERT INTO EMPLOYEE_SKILL_TBL (EMPLOYEE_ID,SKILL_ID,DOMAIN_ID,PROFIENCY,EXPERIENCE,RECENCY,COMMENT)");
		SQL_INSERT.append("VALUES(?,?,?,?,?,?,?)");
		
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID.append("SELECT * FROM EMPLOYEE_SKILL_TBL ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID.append(" WHERE ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID.append(" SKILL_ID  = ? ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID.append(" AND ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID.append(" EMPLOYEE_ID  = ? ");
		
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.append("SELECT count(*) FROM EMPLOYEE_SKILL_TBL ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.append(" WHERE ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.append(" SKILL_ID  = ? ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.append(" AND ");
		SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.append(" EMPLOYEE_ID  = ? ");
		
		SQL_SELECT_BY_SKILL_ID.append("SELECT * FROM EMPLOYEE_SKILL_TBL ");
		SQL_SELECT_BY_SKILL_ID.append(" WHERE ");
		SQL_SELECT_BY_SKILL_ID.append(" SKILL_ID  = ? ");
		
		SQL_SELECT_BY_EMPLOYEE_ID.append("SELECT * FROM EMPLOYEE_SKILL_TBL ");
		SQL_SELECT_BY_EMPLOYEE_ID.append(" WHERE ");
		SQL_SELECT_BY_EMPLOYEE_ID.append(" EMPLOYEE_ID  = ? ");
		
		SQL_SELECT_ALL_EMPLOYEE_SKILLS.append("SELECT * FROM EMPLOYEE_SKILL_TBL");
		
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append("UPDATE EMPLOYEE_SKILL_TBL");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" SET ");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" DOMAIN_ID  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" PROFIENCY  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" EXPERIENCE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" RECENCY  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" COMMENT  = ? ");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" WHERE ");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" SKILL_ID  = ? ");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" AND ");
		SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.append(" EMPLOYEE_ID  = ? ");
		
		SQL_DELETE_EXISITING_EMPLOYEE_SKILL.append("DELETE FROM EMPLOYEE_SKILL_TBL");
		SQL_DELETE_EXISITING_EMPLOYEE_SKILL.append(" WHERE ");
		SQL_DELETE_EXISITING_EMPLOYEE_SKILL.append(" EMPLOYEE_ID  = ? ");
		SQL_DELETE_EXISITING_EMPLOYEE_SKILL.append(" AND ");
		SQL_DELETE_EXISITING_EMPLOYEE_SKILL.append(" SKILL_ID  = ? ");
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public final class EmployeeSkillRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			EmployeeSkill employeeSkill = new EmployeeSkill();
			
			employeeSkill.setSkillId((Integer) rs.getObject("SKILL_ID"));
			employeeSkill.setEmployeeId((Integer) rs.getObject("EMPLOYEE_ID"));
			employeeSkill.setDomainId((Integer) rs.getObject("DOMAIN_ID"));
			employeeSkill.setProficiency((Integer) rs.getObject("PROFIENCY"));
			employeeSkill.setExperience((Integer) rs.getObject("EXPERIENCE"));
			employeeSkill.setRecency((Integer)rs.getObject("RECENCY"));
			employeeSkill.setComments((String) rs.getObject("COMMENT"));
			
			
			return employeeSkill;
		}
	}
	
	/**
	 * This method checks the database whether the employee Id and Skill Id combonation is already present or not in DB
	 * 
	 * @return
	 */
	public Integer checkExistenceByEmployeeIdSkillId(EmployeeSkill employeeSkill) {

		return jdbcTemplate.queryForInt(SQL_SELECT_EMPLOYEE_ID_SKILL_ID_CHECK.toString(),new Object[] { employeeSkill.getSkillId(),employeeSkill.getEmployeeId() });
	}
	
	/**
	 * To insert a new Skill entered by Employee in DB
	 */
	public void insertEmployeeSkill(final EmployeeSkill employeeSkill) {
		
		Integer tempEmployeeSkillId = checkExistenceByEmployeeIdSkillId(employeeSkill);
		
		if(tempEmployeeSkillId>0){
			
		}else{
			
			jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

					PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
					
					int param = 1;
					
					ps.setObject(param++, employeeSkill.getEmployeeId());
					ps.setObject(param++, employeeSkill.getSkillId());
					ps.setObject(param++, employeeSkill.getDomainId());
					ps.setObject(param++, employeeSkill.getProficiency());
					ps.setObject(param++, employeeSkill.getExperience());
					ps.setObject(param++, employeeSkill.getRecency());
					ps.setObject(param++, employeeSkill.getComments());

					return ps;

				}
			});
		}

	}

	/**
	 * this method selects all employees and their skills from the database
	 */
	public List<EmployeeSkill> selectAllEmployeeSkills() {

		List<EmployeeSkill> allEmployeeSkill = (List<EmployeeSkill>) jdbcTemplate.query(SQL_SELECT_ALL_EMPLOYEE_SKILLS.toString(), new EmployeeSkillRowMapper());
		return (List<EmployeeSkill>) allEmployeeSkill;
	}

	/**
	 * Select records based on Employee Id
	 */
	public List<EmployeeSkill> selectByEmployeeId(Integer employeeId) {
		
		List<EmployeeSkill> allEmployeeSkill = (List<EmployeeSkill>)jdbcTemplate.query(SQL_SELECT_BY_EMPLOYEE_ID.toString(),new Object[] { employeeId }, new EmployeeSkillRowMapper());
		return (List<EmployeeSkill>) allEmployeeSkill;
	}
	
	/**
	 * Select records based on Skill Id
	 */
	public List<EmployeeSkill> selectBySkillId(Integer skillId) {
		
		List<EmployeeSkill> allEmployeeSkill = (List<EmployeeSkill>) jdbcTemplate.query(SQL_SELECT_BY_SKILL_ID.toString(),new Object[] { skillId }, new EmployeeSkillRowMapper());
		return (List<EmployeeSkill>) allEmployeeSkill;
	}
	
	/**
	 * Select records based on Skill Id  and employee Id
	 */
	public EmployeeSkill selectByEmployeeSkillId(EmployeeSkill aEmployeeSkill) {
		
		EmployeeSkill employeeSkill = new EmployeeSkill();

		try {
			employeeSkill = (EmployeeSkill) jdbcTemplate.queryForObject(SQL_SELECT_EMPLOYEE_ID_SKILL_ID.toString(), new Object[] { aEmployeeSkill.getSkillId(),aEmployeeSkill.getEmployeeId() },
					new EmployeeSkillRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (employeeSkill != null)
			return (EmployeeSkill) employeeSkill;
		else
			return null;
	}


	/**
	 * Update an existing record based on employee and skill id
	 */
	public EmployeeSkill updateEmployeeSkillDetails(EmployeeSkill employeeSkill) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_EMPLOYEE_SKILL.toString(), new Object[] {
			employeeSkill.getDomainId(),
			employeeSkill.getProficiency(),
			employeeSkill.getExperience(),
			employeeSkill.getRecency(),
			employeeSkill.getComments(),
			employeeSkill.getSkillId(),
			employeeSkill.getEmployeeId() });

		return selectByEmployeeSkillId(employeeSkill);
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void deleteEmployeeSkillDetails(EmployeeSkill employeeSkill) {
		jdbcTemplate.update(SQL_DELETE_EXISITING_EMPLOYEE_SKILL.toString(), new Object[] {
			employeeSkill.getEmployeeId(),
			employeeSkill.getSkillId()
			 });
		
	}

}
