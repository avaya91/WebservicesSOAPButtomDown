package com.imcs.bo;

import com.imcs.dao.EmployeeDAO;
import com.imcs.dto.EmployeeDTO;

public class EmployeeBO {

	public EmployeeDTO getEmployeeInfo(String empId) throws Exception {

		EmployeeDTO dto = null;

		if (empId != null && empId.trim().length() > 0) {
			EmployeeDAO dao = new EmployeeDAO();
			dto = dao.getEmployeeInfo(empId);
			
		} 
		return dto;
	}
	public void addEmployeeInfo(String empId, String firstName, String lastName, String emailId) throws Exception {

		if (empId != null && empId.trim().length() > 0) {
			EmployeeDAO dao = new EmployeeDAO();
			dao.addEmployeeInfo(empId, firstName, lastName, emailId);
			
		} 
	}
	public void deleteEmployeeInfo(String empId) throws Exception {

		if (empId != null && empId.trim().length() > 0) {
			EmployeeDAO dao = new EmployeeDAO();
			dao.deleteEmployeeInfo(empId);
			
		} 
	}
}
