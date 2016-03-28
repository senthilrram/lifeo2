package com.lifeo2.database.model;

import java.util.List;

public class Department {

	private String hospitalid;
	private String departmentid;
	private String department_name;
	private String department_details;
	private String department_head;
	private List<Doctor> doctorsList;
	public String getHospitalid() {
		return hospitalid;
	}
	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_details() {
		return department_details;
	}
	public void setDepartment_details(String department_details) {
		this.department_details = department_details;
	}
	public String getDepartment_head() {
		return department_head;
	}
	public void setDepartment_head(String department_head) {
		this.department_head = department_head;
	}
	public List<Doctor> getDoctorsList() {
		return doctorsList;
	}
	public void setDoctorsList(List<Doctor> doctorsList) {
		this.doctorsList = doctorsList;
	}
	
	
}
