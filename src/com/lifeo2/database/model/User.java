/**
 * 
 */
package com.lifeo2.database.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * @author erasent
 *
 */
public class User {
	
	 private String userId		="";
	 
	 private String username	="";
	
	 private String password	="";
	 private String userType	="";
	 
	 @JsonProperty("Patient")
	 private Patient patient;
	 
	 @JsonProperty("Doctor")
	 private Doctor doctor;
	 
	 @JsonProperty("SuperAdmin")
	 private SuperAdmin admin;
	 
	 @JsonProperty("Hospital")
	 private Hospital hospital;
	 
	 public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	 public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public SuperAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(SuperAdmin admin) {
		this.admin = admin;
	}

}
