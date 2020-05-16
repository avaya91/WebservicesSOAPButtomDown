package com.imcs.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.imcs.constants.AppConstants;
import com.imcs.dto.EmployeeDTO;
import com.imcs.props.PropertiesHelper;

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

	public void addEmployeeInfo(String empId, String firstName, String lastName, String emailId) throws Exception {
		Connection con = null;
		int employeeID = Integer.parseInt(empId);
		try {
			con = getConnection();
			PreparedStatement psmt = con
					.prepareStatement("insert into employees(EMPLOYEE_ID,LAST_NAME,FIRST_NAME,EMAIL) values(?,?,?,?)");
			psmt.setInt(1, employeeID);
			psmt.setString(2, lastName);
			psmt.setString(3, firstName);
			psmt.setString(4, emailId);
			int result = psmt.executeUpdate();
			con.commit();
			System.out.println("No of Record added: " + result);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteEmployeeInfo(String empId) throws Exception {
		Connection con = null;
		int employeeID = Integer.parseInt(empId);
		try {
			con = getConnection();
			PreparedStatement psmt = con.prepareStatement("delete employees where employee_id=?");
			psmt.setInt(1, employeeID);
			int result = psmt.executeUpdate();
			con.commit();
			System.out.println("No of Records Deleted: " + result);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateEmployeeInfo(String empId, String firstName, String lastName, String emailId) throws Exception {
		Connection con = null;
		int employeeID = Integer.parseInt(empId);
		try {
			con = getConnection();
			PreparedStatement psmt = con.prepareStatement(
					"UPDATE employees SET First_Name = ?, Last_Name = ?, Email = ? WHERE employee_id = ?");
			psmt.setString(1, firstName);
			psmt.setString(2, lastName);
			psmt.setString(3, emailId);
			psmt.setInt(4, employeeID);
			int result = psmt.executeUpdate();
			con.commit();
			System.out.println("No of Record updated: " + result);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() throws Exception {
		Connection con = null;
		PropertiesHelper props = null;
		try {
			props = PropertiesHelper.getInstance();
			Class.forName(props.getProperty("driver"));
			con = DriverManager.getConnection(props.getProperty(AppConstants.url), props.getProperty(AppConstants.user),
					props.getProperty(AppConstants.password));
			con.setAutoCommit(false);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}
}
