package com.imcs.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.imcs.dto.EmployeeDTO;

public class EmployeeDAO {

	public EmployeeDTO getEmployeeInfo(String empID) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		EmployeeDTO empDto = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employees where employee_id=" + empID);

			while (rs.next()) {
				empDto = new EmployeeDTO();
				String employeeId = rs.getString("EMPLOYEE_ID");
				String lastName = rs.getString("LAST_NAME");
				String firstName = rs.getString("FIRST_NAME");
				String emailId = rs.getString("EMAIL");

				empDto.setEmpId(employeeId);
				empDto.setLastName(lastName);
				empDto.setFirstName(firstName);
				empDto.setEmail(emailId);

				System.out.println(employeeId + " " + firstName + " " + lastName + " " + emailId);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return empDto;
	}

	private Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:51521/XEPDB1", "imcs", "imcs");
			con.setAutoCommit(false);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}
}
