/**
 * 
 */
package com.lifeo2.database.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author erasent
 *
 */
public class Doctor{

	
	/**
	 * 
	 */
	private String doctorid					="";
	private String hospitalid               ="";
	private String userId                   ="";
	private String experience               ="";
	private String qualification            ="";
	private String first_name               ="";
	private String last_name                ="";
	private String dob                      ="";
	private String mobile_number            ="";
	private String alternate_mobile_number  ="";
	private String available_start_time     ="";
	private String available_end_time       ="";
	private String email                    ="";
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getHospitalid() {
		return hospitalid;
	}
	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getAlternate_mobile_number() {
		return alternate_mobile_number;
	}
	public void setAlternate_mobile_number(String alternate_mobile_number) {
		this.alternate_mobile_number = alternate_mobile_number;
	}
	public String getAvailable_start_time() {
		return available_start_time;
	}
	public void setAvailable_start_time(String available_start_time) {
		this.available_start_time = available_start_time;
	}
	public String getAvailable_end_time() {
		return available_end_time;
	}
	public void setAvailable_end_time(String available_end_time) {
		this.available_end_time = available_end_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Doctor() {
		// TODO Auto-generated constructor stub
	}

}
