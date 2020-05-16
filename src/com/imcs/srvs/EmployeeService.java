package com.imcs.srvs;

import com.imcs.bo.EmployeeBO;
import com.imcs.dto.EmployeeDTO;

public class EmployeeService {

	public EmployeeDTO getEmployeeInfo(String empId) {

		EmployeeDTO empDto = null;
		try {

			if (empId != null && empId.trim().length() > 0) {
				EmployeeBO bo = new EmployeeBO();
				empDto = bo.getEmployeeInfo(empId);
			} else {
				System.out.println("Ivalid input from the client");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return empDto;
	}
	
	public String addEmployeeInfo(String empId, String firstName, String lastName, String emailId) {
		try {

			if (empId != null && empId.trim().length() > 0) {
				EmployeeBO bo = new EmployeeBO();
				bo.addEmployeeInfo(empId, firstName, lastName, emailId);
			} else {
				System.out.println("Ivalid input from the client");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Successfully Added";
	}

}