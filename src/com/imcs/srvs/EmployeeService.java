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
				System.out.println("Ivalid inpur from the client");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return empDto;
	}

}