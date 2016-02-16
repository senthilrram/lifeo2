/**
 * 
 */
package com.lifeo2.database.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author erasent
 *
 */
public class Hospital  {

	/**
	 * 
	 */
	private String hospitalid				="";
	private String hospital_name            ="";
	private String hospital_address         ="";
	private String facilties                ="";
	private String phone_number             ="";
	private String mobile_number            ="";
	private String alternate_number         ="";
	private String email                    ="";
	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getHospital_address() {
		return hospital_address;
	}

	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}

	public String getFacilties() {
		return facilties;
	}

	public void setFacilties(String facilties) {
		this.facilties = facilties;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getAlternate_number() {
		return alternate_number;
	}

	public void setAlternate_number(String alternate_number) {
		this.alternate_number = alternate_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Hospital() {
		// TODO Auto-generated constructor stub
	}

}
