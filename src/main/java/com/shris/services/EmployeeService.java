package com.shris.services;

import com.shris.domain.Employee;

public class EmployeeService {

	final UtilServices utilServices = new UtilServices();

	public Employee generate(int index) {

		String name = utilServices.getRandName();
		String id = String.valueOf(++index);
		String address = utilServices.getRandAddress();

		return new Employee(name, id, address);

	}

}
