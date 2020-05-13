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
}
