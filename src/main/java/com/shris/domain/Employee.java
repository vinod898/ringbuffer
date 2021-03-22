package com.shris.domain;

import java.util.UUID;

public class Employee {

	private String id;
	private String empName;
	private String empId;
	private String address;

	public Employee(String empName, String empId, String address) {
		super();
		this.id = UUID.randomUUID().toString();
		this.empName = empName;
		this.empId = empId;
		this.address = address;
	}
	
	

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + ", address=" + address + "]";
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
