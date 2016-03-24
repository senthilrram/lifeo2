package com.lifeo2.database.model;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class Result {
	@JsonProperty("Patient")
	public Patient patient;
	@JsonProperty("Doctor")
	public Doctor doctor;
	@JsonProperty("SuperAdmin")
	public SuperAdmin rootuser;
	public List<HashMap<String, String>> hospitalList;
	public List<Doctor> doctorsList;
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	private String type;
	private String message;
	
	public String getType() {
		return type;
	}
	public Result setType(String type) {
		this.type = type;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public Result setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
}
