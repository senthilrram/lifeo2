/**
 * 
 */
package com.lifeo2.database.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author erasent
 *
 */

public class Patient extends User {

	/**
	 * 
	 */
	 private String patientid				="";  	
	 private String userId				    ="";
	 private String first_name			    ="";
	 private String last_name				="";
	 private String bloodgroup			    ="";
	 private String dob					    ="";
	 private String gender				    ="";
	 private String mobile_number			="";
	 private String alternate_mobile_number ="";
	 private String email					="";
	 private String address				    ="";
	 
	 public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Patient() {
		// TODO Auto-generated constructor stub
	}

}
