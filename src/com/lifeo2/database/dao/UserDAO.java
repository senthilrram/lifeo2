package com.lifeo2.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

import com.lifeo2.database.model.Doctor;
import com.lifeo2.database.model.Patient;
import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.SuperAdmin;
import com.lifeo2.database.model.User;


public class UserDAO extends ConnectionDAO{
	
	private static final String QUERY_LOGIN = "SELECT username,password,userId,userType FROM UserTable WHERE username='%s' and password='%s'";
	private static final String QUERY_LOGIN_USERNAME = "SELECT password,userType,userId FROM UserTable WHERE username='%s'";
	private static final String QUERY_PATIENT_DETAILS = "select patientid,first_name,last_name,bloodgroup,dob,gender,mobile_number,alternate_mobile_number,email,address from Patients where userId = '%s';";
	private static final String QUERY_DOCTOR_DETAILS = " select doctorid,hospitalid,experience,qualification,first_name,last_name,dob,mobile_number,alternate_mobile_number,available_start_time,available_end_time,email from Doctors where userId = '%s'";
	private static final String QUERY_SUPERADMIN_DETAILS = "select adminid,first_name,last_name,hospitalid,mobile_number,email from SuperAdmin where userId = '%s'";
	
	private static final String ADD_NEW_USER = "INSERT INTO UserTable (username, password,userType) VALUES (?,?,?);";
	private static final String ADD_NEW_PATIENT = "INSERT INTO Patients (userId,first_name,last_name,bloodgroup,dob,gender,mobile_number,alternate_mobile_number,email,address) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String ADD_NEW_DOCTOR = "INSERT INTO Doctors (userId,hospitalid,experience,qualification,first_name,last_name,dob,mobile_number,alternate_mobile_number,email) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String ADD_NEW_ADMIN = "INSERT INTO SuperAdmin (first_name,last_name,hospitalid,userId,mobile_number,email) VALUES (?,?,?,?,?,?);";
	
	private static final String UDATE_USER_TABLE="UPDATE UserTable SET ";
	

	public static Result dologin(String uname, String pwd) throws SQLException
	{
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		ResultSet rsnew;
        try {
        	
        	System.out.println("uname:"+uname+"-password:"+pwd);
        	ResultSet rs = stmt.executeQuery(String.format(QUERY_LOGIN, uname,pwd));
            if(rs.next())
            {
            		//If result set has somethings, then user exist. not required to iterate all 
            	System.out.println("unamek:"+rs.getString(1)+"-passwordk:"+rs.getString(2));
        		System.out.println("UserName and Password Matched");
    			if(rs.getString(4).equalsIgnoreCase("Patient"))
    			{
    				Patient patient = new Patient();
    				patient.setUsername(rs.getString(1));
    				patient.setPassword(rs.getString(2));
    				patient.setUserId(rs.getString(3));
    				patient.setUserType(rs.getString(4));
    				
    				rsnew = stmt.executeQuery(String.format(QUERY_PATIENT_DETAILS, rs.getString(3)));
    			        				
    				if(rsnew.first())
    				{
    					patient.setPatientid(rsnew.getString(1));
        				patient.setFirst_name(rsnew.getString(2));
        				patient.setLast_name(rsnew.getString(3));
        				patient.setBloodgroup(rsnew.getString(4));
        				patient.setDob(rsnew.getString(5));
        				patient.setGender(rsnew.getString(6));
        				patient.setMobile_number(rsnew.getString(7));
        				patient.setAlternate_mobile_number(rsnew.getString(8));
        				patient.setEmail(rsnew.getString(9));
        				patient.setAddress(rsnew.getString(10));
        				System.out.println(patient.getPassword()+patient.getFirst_name());
        				
        				result.patient=patient;
        				result.setMessage("Patient Successfully Logged in ");
        				result.setType(Result.SUCCESS);
        				return  result;
    					
    				}
    				else
    				{
    					result.patient=patient;
    					result.setMessage("Patient data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
    			else if(rs.getString(4).equalsIgnoreCase("Doctor"))
    			{
    				Doctor doctor = new Doctor();
    				doctor.setUsername(rs.getString(1));
    				doctor.setPassword(rs.getString(2));
    				doctor.setUserId(rs.getString(3));
    				doctor.setUserType(rs.getString(4));
    				System.out.println("UserId:"+ rs.getString(3));
    				rsnew = stmt.executeQuery(String.format(QUERY_DOCTOR_DETAILS, rs.getString(3)));
    				if(rsnew.first())
    				{
    					doctor.setDoctorid(rsnew.getString(1));
        				doctor.setHospitalid(rsnew.getString(2));
        				doctor.setExperience(rsnew.getString(3));
        				doctor.setQualification(rsnew.getString(4));
        				doctor.setFirst_name(rsnew.getString(5));
        				doctor.setLast_name(rsnew.getString(6));
        				doctor.setDob(rsnew.getString(7));
        				doctor.setMobile_number(rsnew.getString(8));
        				doctor.setAlternate_mobile_number(rsnew.getString(9));
        				doctor.setEmail(rsnew.getString(12));
        				System.out.println(doctor.getPassword()+doctor.getFirst_name());
        				result.doctor=doctor;
        				result.setMessage("Doctor Successfully Logged in ");
        				result.setType(Result.SUCCESS);
        				return  result;
    				}
    				else
    				{
    					result.doctor=doctor;
    					result.setMessage("Doctor data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
    			else if(rs.getString(4).equalsIgnoreCase("SuperAdmin"))
    			{
    				SuperAdmin superadmin = new SuperAdmin();
    				superadmin.setUsername(rs.getString(1));
    				superadmin.setPassword(rs.getString(2));
    				superadmin.setUserId(rs.getString(3));
    				superadmin.setUserType(rs.getString(4));
    				
    				rsnew = stmt.executeQuery(String.format(QUERY_SUPERADMIN_DETAILS, rs.getString(3)));
    				if(rsnew.first())
    				{
    					superadmin.setAdminid(rsnew.getString(1));        	
        				superadmin.setFirst_name(rsnew.getString(2));
        				superadmin.setLast_name(rsnew.getString(3));
        				superadmin.setHospitalid(rsnew.getString(4));
        				superadmin.setMobile_number(rsnew.getString(5));
        				superadmin.setEmail(rsnew.getString(6));
        				result.rootuser=superadmin;
        				result.setMessage("Super Admin Successfully Logged in ");
        				result.setType(Result.SUCCESS);
        				return  result;
    				}
    				else
    				{
    					result.rootuser=superadmin;
    					result.setMessage("Super Admin data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
            	
            }
            else
            {
            	result.setMessage("Invalid UserName/Password Please check the credentials");
				result.setType(Result.SUCCESS);
				return  result;
            	
            }
           
        }
        finally
        {
        	System.out.println("finally");
        	connection.close();
        }
        result.setMessage("Server Error, Please try again later");
		return result.setType(Result.ERROR);
    	
    }

	public static Result addNewUser(User user) throws SQLException {
		
		
		Result result = new Result();
		PreparedStatement updatestmt=null;
		PreparedStatement updatestmtPatient;
		PreparedStatement updatestmtDoctor;
		PreparedStatement updatestmtSuperAdmin;
		Connection connection = null;
		ResultSet rs=null;
		
        try {
        	
        	connection = getConnection();
        	Statement stmt = connection.createStatement();
        	rs = stmt.executeQuery(String.format(QUERY_LOGIN_USERNAME, user.getUsername(),user.getPassword()));
            if(rs.next())
            {
            	
            	result.setMessage("User Already Registered,Please sign in directly");
				result.setType(Result.SUCCESS);
				return  result;
            }
            else
            {
            	updatestmt = connection.prepareStatement(ADD_NEW_USER);
            	updatestmt.setString(1, user.getUsername());
            	updatestmt.setString(2, user.getPassword());
            	updatestmt.setString(3, user.getUserType());
            	if(updatestmt.executeUpdate() > 0 && user.getUserType().equalsIgnoreCase("patient"))
    			{
            		rs.close();
            		rs = stmt.executeQuery(String.format(QUERY_LOGIN_USERNAME, user.getUsername(),user.getPassword()));
            		rs.first();
            		updatestmtPatient = connection.prepareStatement(ADD_NEW_PATIENT);
            		updatestmtPatient.setInt(1, Integer.parseInt(rs.getString(3)));
            		updatestmtPatient.setString(2, user.getPatient().getFirst_name());
            		updatestmtPatient.setString(3, user.getPatient().getLast_name());
            		updatestmtPatient.setString(4, user.getPatient().getBloodgroup());
            		updatestmtPatient.setString(5, user.getPatient().getDob());
            		updatestmtPatient.setString(6, user.getPatient().getGender());
            		updatestmtPatient.setString(7, user.getPatient().getMobile_number());
            		updatestmtPatient.setString(8, user.getPatient().getAlternate_mobile_number());
            		updatestmtPatient.setString(9, user.getPatient().getEmail());
            		updatestmtPatient.setString(10, user.getPatient().getAddress());
            		if(updatestmtPatient.executeUpdate() > 0)
            		{
            			result.setMessage("User Registration Success as Patient");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
            		else
            		{
            			result.setMessage("User Registration Failure as Patient");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
    				
    			}
            	else if (updatestmt.executeUpdate() > 0 && user.getUserType().equalsIgnoreCase("doctor"))
            	{
            		rs.close();
            		rs = stmt.executeQuery(String.format(QUERY_LOGIN_USERNAME, user.getUsername(),user.getPassword()));
            		rs.first();
            		updatestmtDoctor = connection.prepareStatement(ADD_NEW_DOCTOR);
            		updatestmtDoctor.setInt(1, Integer.parseInt(rs.getString(3)));
            		updatestmtDoctor.setInt(2, Integer.parseInt(user.getHospital().getHospitalid()));
            		
            		updatestmtDoctor.setInt(3, Integer.parseInt(user.getDoctor().getExperience()));
            		updatestmtDoctor.setString(4, user.getDoctor().getQualification());
            		updatestmtDoctor.setString(5, user.getDoctor().getFirst_name());
            		updatestmtDoctor.setString(6, user.getDoctor().getLast_name());
            		updatestmtDoctor.setString(7, user.getDoctor().getDob());
            		updatestmtDoctor.setString(8, user.getDoctor().getMobile_number());
            		updatestmtDoctor.setString(9, user.getDoctor().getAlternate_mobile_number());
            		updatestmtDoctor.setString(10, user.getDoctor().getEmail());
            		
            		if(updatestmtDoctor.executeUpdate() > 0)
            		{
            			result.setMessage("User Registration Success as Doctor");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
            		else
            		{
            			result.setMessage("User Registration Failure as Doctor");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
            		
            	}
            	else if (updatestmt.executeUpdate() > 0 && user.getUserType().equalsIgnoreCase("superadmin"))
            	{
            		
            		rs.close();
            		rs = stmt.executeQuery(String.format(QUERY_LOGIN_USERNAME, user.getUsername(),user.getPassword()));
            		rs.first();
            		//first_name,last_name,hospitalid,userId,mobile_number,email
            		updatestmtSuperAdmin = connection.prepareStatement(ADD_NEW_ADMIN);
            		updatestmtSuperAdmin.setString(1, user.getAdmin().getFirst_name());
            		updatestmtSuperAdmin.setString(2, user.getAdmin().getLast_name());
            		updatestmtSuperAdmin.setInt(3, Integer.parseInt(user.getHospital().getHospitalid()));
            		updatestmtSuperAdmin.setInt(4, Integer.parseInt(rs.getString(3)));
            		updatestmtSuperAdmin.setString(5, user.getAdmin().getMobile_number());
            		updatestmtSuperAdmin.setString(6, user.getAdmin().getEmail());
            		
            		if(updatestmtSuperAdmin.executeUpdate() > 0)
            		{
            			result.setMessage("User Registration Success as Super Admin");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
            		else
            		{
            			result.setMessage("User Registration Failure as Super Admin");
        				result.setType(Result.SUCCESS);
        				return  result;
            			
            		}
            	}
            	
            }
           
        }
        finally
        {
        	System.out.println("finally");
        	rs.close();
        	connection.close();
        }
        return null;
    	
    }

	public static Result updateUser(User user) throws SQLException {
		
		
		Result result = new Result();
		Connection connection = null;
		//UPDATE lifeo2.usertable SET password='Rramasamy09'WHERE username='senthil.rram';
		
        try {
        	
        	connection = getConnection();
        	Statement stmt = connection.createStatement();
        	StringBuilder statement=new StringBuilder();
        	statement.append(UDATE_USER_TABLE);
        	if(user.getPassword()!=null && user.getPassword()!="")
        		statement.append("password='"+user.getPassword()+"' where userId='"+user.getUserId()+"';");
        	
        	System.out.println("Update statement to be sent is :"+statement.toString());
        	if(stmt.executeUpdate(statement.toString()) > 0)
        	{
        		if(user.getPatient()!=null)
        		{
        			if(user.getPatient().getPatientid() == null || user.getPatient().getPatientid() == "")
    				{
    					result.setType(Result.ERROR).setMessage("Patient Id is mandatory to perform update");
    					return result;
    				}
        			return PatientDAO.updatePatientProfile(user,connection);
        		}
        		else if( user.getAdmin()!=null)
        		{
        			return SuperAdminDAO.updateAdminProfile(user,connection);	
        		}
        		else if(user.getDoctor()!=null)
        		{
        			return DoctorDAO.updateDoctorProfile(user,connection);	
        		}
        		else
        		{
        			result.setMessage("User details updated successfully in User Table");
    				result.setType(Result.SUCCESS);
    				return  result;
        		}
        		
        		
        	}
           
        	
        	
         }
        finally
        {
        	System.out.println("finally");
        	if(connection!=null)
        		connection.close();
        }
        return null;
    	
    }

	public static Result getUserDetails(String uname, String pwd) throws SQLException {
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		ResultSet rsnew;
        try {
        	
        	
        	ResultSet rs = stmt.executeQuery(String.format(QUERY_LOGIN, uname,pwd));
            if(rs.next())
            {
            	System.out.println("unamek:"+rs.getString(1)+"-passwordk:"+rs.getString(2));
        		System.out.println("UserName and Password Matched");
    			if(rs.getString(4).equalsIgnoreCase("Patient"))
    			{
    				Patient patient = new Patient();
    				patient.setUsername(rs.getString(1));
    				patient.setPassword(rs.getString(2));
    				patient.setUserId(rs.getString(3));
    				patient.setUserType(rs.getString(4));
    				
    				rsnew = stmt.executeQuery(String.format(QUERY_PATIENT_DETAILS, rs.getString(3)));
    			        				
    				if(rsnew.first())
    				{
    					patient.setPatientid(rsnew.getString(1));
        				patient.setFirst_name(rsnew.getString(2));
        				patient.setLast_name(rsnew.getString(3));
        				patient.setBloodgroup(rsnew.getString(4));
        				patient.setDob(rsnew.getString(5));
        				patient.setGender(rsnew.getString(6));
        				patient.setMobile_number(rsnew.getString(7));
        				patient.setAlternate_mobile_number(rsnew.getString(8));
        				patient.setEmail(rsnew.getString(9));
        				patient.setAddress(rsnew.getString(10));
        				System.out.println(patient.getPassword()+patient.getFirst_name());
        				
        				result.patient=patient;
        				result.setMessage("Patient data ");
        				result.setType(Result.SUCCESS);
        				return  result;
    					
    				}
    				else
    				{
    					result.patient=patient;
    					result.setMessage("Patient data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
    			else if(rs.getString(4).equalsIgnoreCase("Doctor"))
    			{
    				Doctor doctor = new Doctor();
    				doctor.setUsername(rs.getString(1));
    				doctor.setPassword(rs.getString(2));
    				doctor.setUserId(rs.getString(3));
    				doctor.setUserType(rs.getString(4));
    				System.out.println("UserId:"+ rs.getString(3));
    				rsnew = stmt.executeQuery(String.format(QUERY_DOCTOR_DETAILS, rs.getString(3)));
    				if(rsnew.first())
    				{
    					doctor.setDoctorid(rsnew.getString(1));
        				doctor.setHospitalid(rsnew.getString(2));
        				doctor.setExperience(rsnew.getString(3));
        				doctor.setQualification(rsnew.getString(4));
        				doctor.setFirst_name(rsnew.getString(5));
        				doctor.setLast_name(rsnew.getString(6));
        				doctor.setDob(rsnew.getString(7));
        				doctor.setMobile_number(rsnew.getString(8));
        				doctor.setAlternate_mobile_number(rsnew.getString(9));
        				doctor.setEmail(rsnew.getString(12));
        				System.out.println(doctor.getPassword()+doctor.getFirst_name());
        				result.doctor=doctor;
        				result.setMessage("Doctor data");
        				result.setType(Result.SUCCESS);
        				return  result;
    				}
    				else
    				{
    					result.doctor=doctor;
    					result.setMessage("Doctor data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
    			else if(rs.getString(4).equalsIgnoreCase("SuperAdmin"))
    			{
    				SuperAdmin superadmin = new SuperAdmin();
    				superadmin.setUsername(rs.getString(1));
    				superadmin.setPassword(rs.getString(2));
    				superadmin.setUserId(rs.getString(3));
    				superadmin.setUserType(rs.getString(4));
    				
    				rsnew = stmt.executeQuery(String.format(QUERY_SUPERADMIN_DETAILS, rs.getString(3)));
    				if(rsnew.first())
    				{
    					superadmin.setAdminid(rsnew.getString(1));        	
        				superadmin.setFirst_name(rsnew.getString(2));
        				superadmin.setLast_name(rsnew.getString(3));
        				superadmin.setHospitalid(rsnew.getString(4));
        				superadmin.setMobile_number(rsnew.getString(5));
        				superadmin.setEmail(rsnew.getString(6));
        				result.rootuser=superadmin;
        				result.setMessage("Super Admin data ");
        				result.setType(Result.SUCCESS);
        				return  result;
    				}
    				else
    				{
    					result.rootuser=superadmin;
    					result.setMessage("Super Admin data not found on the database");
    					result.setType(Result.SUCCESS);
    					return  result;
    				}
    				
    				
    			}
            	
            }
            else
            {
            	result.setMessage("Cannot update the profile, as this user is not found on DB");
				result.setType(Result.SUCCESS);
				return  result;
            	
            }
           
        }
        finally
        {
        	System.out.println("finally");
        	connection.close();
        }
        result.setMessage("Server Error, Please try again later");
		return result.setType(Result.ERROR);
    	
    }
}
