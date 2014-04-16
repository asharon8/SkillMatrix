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

import com.sdlc.skillMatrix.model.dao.EmployeeDao;
import com.sdlc.skillMatrix.model.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final StringBuffer SQL_INSERT = new StringBuffer();
	private static final StringBuffer SQL_SELECT_MAX_EMPLOYEE_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_BY_EMPLOYEE_ID = new StringBuffer();
	private static final StringBuffer SQL_SELECT_ALL_EMPLOYEES = new StringBuffer();
	private static final StringBuffer SQL_UPDATE_EXISITING_EMPLOYEE = new StringBuffer();
	
	static{
		
		SQL_INSERT.append("INSERT INTO EMPLOYEE_TBL ("); 
		SQL_INSERT.append("	EMPLOYEE_ID,");
		SQL_INSERT.append("	FIRST_NAME,");
		SQL_INSERT.append("	LAST_NAME,");
		SQL_INSERT.append("	PHONE,");
		SQL_INSERT.append("	EMAIL,");
		SQL_INSERT.append("	SDLC_EMPLOYEE,");
		SQL_INSERT.append("	EMPLOYEE_ROLE,");
		SQL_INSERT.append("	EMPLOYEE_TYPE,");
		SQL_INSERT.append("	EMPLOYEE_HIRE_DATE,");
		SQL_INSERT.append("	EMPLOYEE_PROJECT_END_DATE,");
		SQL_INSERT.append("	IN_PROJECT,");
		SQL_INSERT.append("	WORK_LOCATION ");
		SQL_INSERT.append(") ");
		SQL_INSERT.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		
		SQL_SELECT_MAX_EMPLOYEE_ID.append("SELECT MAX(EMPLOYEE_ID) FROM EMPLOYEE_TBL");
		
		SQL_SELECT_BY_EMPLOYEE_ID.append("SELECT * FROM EMPLOYEE_TBL WHERE EMPLOYEE_ID = ?");
		
		SQL_SELECT_ALL_EMPLOYEES.append("SELECT * FROM EMPLOYEE_TBL");
		
		SQL_UPDATE_EXISITING_EMPLOYEE.append("UPDATE EMPLOYEE_TBL ");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("SET ");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	FIRST_NAME  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	LAST_NAME  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	PHONE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMAIL  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	SDLC_EMPLOYEE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMPLOYEE_ROLE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMPLOYEE_TYPE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMPLOYEE_HIRE_DATE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMPLOYEE_PROJECT_END_DATE  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	IN_PROJECT  = ? ,");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	WORK_LOCATION  = ? ");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("WHERE ");
		SQL_UPDATE_EXISITING_EMPLOYEE.append("	EMPLOYEE_ID  = ?");
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public final class EmployeeRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Employee employee = new Employee();
			
			employee.setEmployeeId((Integer) rs.getObject("EMPLOYEE_ID"));
			employee.setFirstName((String) rs.getObject("FIRST_NAME"));
			employee.setLastName((String) rs.getObject("LAST_NAME"));
			employee.setPhone((String) rs.getObject("PHONE"));
			employee.setEmail((String) rs.getObject("EMAIL"));
			employee.setSdlcEmployee((String) rs.getObject("SDLC_EMPLOYEE"));
			employee.setEmployeeRole((String) rs.getObject("EMPLOYEE_ROLE"));
			employee.setEmployeeType((String) rs.getObject("EMPLOYEE_TYPE"));
			employee.setEmployeeHireDate(rs.getDate("EMPLOYEE_HIRE_DATE"));
			employee.setEmployeeProjectEndDate(rs.getDate("EMPLOYEE_PROJECT_END_DATE"));
			employee.setEmployeeInProject((String) rs.getObject("IN_PROJECT"));
			employee.setEmployeeWorkLocation((String) rs.getObject("WORK_LOCATION"));
			
			return employee;
		}
	}
	
	/**
	 * Get the max Employee Id and assign it to the EMPLOYEE during the insertion of Employee details in DB
	 * 
	 * @return
	 */
	public Integer selectMaxEmployeeId() {

		return jdbcTemplate.queryForInt(SQL_SELECT_MAX_EMPLOYEE_ID.toString());
	}
	
	/**
	 * method to insert a new Employee in DB
	 */
	public Employee insertEmployee(final Employee employee) {
		
		final Integer tempEmployeeId = selectMaxEmployeeId() + 1;
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(SQL_INSERT.toString());
				
				int param = 1;
				
				ps.setObject(param++, tempEmployeeId);
				ps.setObject(param++, employee.getFirstName());
				ps.setObject(param++, employee.getLastName());
				ps.setObject(param++, employee.getPhone());
				ps.setObject(param++, employee.getEmail());
				ps.setObject(param++, employee.getSdlcEmployee());
				ps.setObject(param++, employee.getEmployeeRole());
				ps.setObject(param++, employee.getEmployeeType());
				ps.setObject(param++, employee.getEmployeeHireDate());
				ps.setObject(param++, employee.getEmployeeProjectEndDate());
				ps.setObject(param++, employee.getEmployeeInProject());
				ps.setObject(param++, employee.getEmployeeWorkLocation());

				return ps;

			}
		});
		
		return selectByEmployeeId(tempEmployeeId);
	}

	/**
	 * Method query DB and return all the Employee details
	 */
	public List<Employee> selectAllEmployees() {
		
		List<Employee> allEmployee = (List<Employee>) jdbcTemplate.query(SQL_SELECT_ALL_EMPLOYEES.toString(), new EmployeeRowMapper());

		return (List<Employee>) allEmployee;
	}

	/**
	 * Select Employee from the database based on the Employee Id provided
	 */
	public Employee selectByEmployeeId(Integer employeeId) {
		
		Employee employee = new Employee();

		try {
			employee = (Employee) jdbcTemplate.queryForObject(SQL_SELECT_BY_EMPLOYEE_ID.toString(), new Object[] { employeeId },
					new EmployeeRowMapper());

		}
		catch(org.springframework.dao.EmptyResultDataAccessException se){
			employee = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (employee != null)
			return (Employee) employee;
		else
			return null;
	}

	/**
	 * Update an existing Employee , Employee Id provided
	 */
	public Employee updateEmployeeDetails(Employee employee) {
		
		jdbcTemplate.update(SQL_UPDATE_EXISITING_EMPLOYEE.toString(), 
				new Object[] { 
							employee.getFirstName(),
							employee.getLastName(),
							employee.getPhone(),
							employee.getEmail(),
							employee.getSdlcEmployee(),
							employee.getEmployeeRole(),
							employee.getEmployeeType(),
							employee.getEmployeeHireDate(),
							employee.getEmployeeProjectEndDate(),
							employee.getEmployeeInProject(),
							employee.getEmployeeWorkLocation(),
							employee.getEmployeeId()
							});

		return selectByEmployeeId(employee.getEmployeeId());
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
