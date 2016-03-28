/**
 * 
 */
package com.lifeo2.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lifeo2.database.model.Doctor;
import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.User;

/**
 * @author erasent
 *
 */
public class DoctorDAO extends ConnectionDAO{

	private static final String UDATE_DOCTOR_DETAILS_TABLE = "UPDATE Doctors SET ";
	private static final String SELECT_ALL_DOCTORS = "SELECT * FROM Doctors";
	private static final String SELECT_ALL_DOCTORS_BY_DEPT_ID = "SELECT * FROM Doctors where departmentid='%s'";
	
	/**
	 * 
	 */
	public DoctorDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Result updateDoctorProfile(User user, Connection connectionDB) throws SQLException {
		
		
		Result result = new Result();
		
        try {
        	
        	
        	Statement stmt = connectionDB.createStatement();
        	StringBuilder statement=new StringBuilder();
        	statement.append(UDATE_DOCTOR_DETAILS_TABLE);
        	if(user.getDoctor().getExperience() !=null && user.getDoctor().getExperience() !="")
        		statement.append("experience='"+user.getDoctor().getExperience()+"'");
        	if(user.getDoctor().getQualification() !=null && user.getDoctor().getQualification() !="")
        		statement.append(",qualification='"+user.getDoctor().getQualification()+"'");
        	if(user.getDoctor().getFirst_name() !=null && user.getDoctor().getFirst_name() !="")
        		statement.append(",first_name='"+user.getDoctor().getFirst_name()+"'");
        	if(user.getDoctor().getLast_name() !=null && user.getDoctor().getLast_name() !="")
        		statement.append(",last_name='"+user.getDoctor().getLast_name()+"'");
        	if(user.getDoctor().getMobile_number() !=null && user.getDoctor().getMobile_number() !="")
        		statement.append(",mobile_number='"+user.getDoctor().getMobile_number()+"'");
        	if(user.getDoctor().getAlternate_mobile_number() !=null && user.getDoctor().getAlternate_mobile_number() !="")
        		statement.append(",alternate_mobile_number='"+user.getDoctor().getAlternate_mobile_number()+"'");
        	if(user.getDoctor().getEmail() !=null && user.getDoctor().getEmail() !="")
        		statement.append(",email='"+user.getDoctor().getEmail()+"'");
        	if(user.getDoctor().getDob() !=null && user.getDoctor().getDob() !="")
        		statement.append(",dob='"+user.getDoctor().getDob()+"'");
        	if(user.getDoctor().getAvailable_start_time() !=null && user.getDoctor().getAvailable_start_time() !="")
        		statement.append(",available_start_time='"+user.getDoctor().getAvailable_start_time()+"'");
        	if(user.getDoctor().getAvailable_end_time() !=null && user.getDoctor().getAvailable_end_time() !="")
        		statement.append(",available_end_time='"+user.getDoctor().getAvailable_end_time()+"'");
        	
        	statement.append(" where doctorid='"+user.getDoctor().getDoctorid()+"';");
        	System.out.println("Update statement to be sent is :"+statement.toString());
        	if(stmt.executeUpdate(statement.toString()) > 0)
        	{
        		
        			result.setMessage("Doctor details updated successfully in Doctor Table");
    				result.setType(Result.SUCCESS);
    				return  result;	
        	}
        	
        	
         }
        finally
        {
        	System.out.println("finally");
        	connectionDB.close();
        }
        return null;
    	
    }

	public static Result getAllDoctors() throws SQLException {
		
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		try {
        	
        	ResultSet rs = stmt.executeQuery(SELECT_ALL_DOCTORS);
        	List<Doctor> returnList=convertToDoctorsList(rs);
        	rs.close();
        	if(returnList.size() > 0)
        	{
        		result.setMessage("Retrieve doctors list success");
				result.setType(Result.SUCCESS);
        		result.doctorsList=returnList;
        		return result;
        	}
        	else
        	{
        		result.setMessage("Retrieve doctors list failed:");
				result.setType(Result.ERROR);
				return  result;
        		
        	}
           
        }
        finally
        {
        	System.out.println("finally");
        	
        	if(stmt != null)
        		connection.close();
        }
    	
    }
	
public static Result getAllDoctorsByDepartmentId(String departmentid) throws SQLException {
		
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		try {
        	
        	ResultSet rs = stmt.executeQuery(String.format(SELECT_ALL_DOCTORS_BY_DEPT_ID,Integer.parseInt(departmentid)));
        	List<Doctor> returnList=convertToDoctorsList(rs);
        	rs.close();
        	if(returnList.size() > 0)
        	{
        		result.setMessage("Retrieve doctors list by department id success");
				result.setType(Result.SUCCESS);
        		result.doctorsList=returnList;
        		return result;
        	}
        	else
        	{
        		result.setMessage("Retrieve doctors list by department id failed:");
				result.setType(Result.ERROR);
				return  result;
        		
        	}
           
        }
        finally
        {
        	System.out.println("finally");
        	
        	if(stmt != null)
        		connection.close();
        }
    	
    }

	public static List<Doctor> convertToDoctorsList(ResultSet rs) throws SQLException {
	    List<Doctor> list = new ArrayList<Doctor>();

	    while (rs.next()) {
	        Doctor d = new Doctor();
	        
	        d.setDoctorid(Integer.toString(rs.getInt(1)));
	        d.setHospitalid(Integer.toString(rs.getInt(2)));
	        d.setUserId(Integer.toString(rs.getInt(3)));
	        d.setExperience(Integer.toString(rs.getInt(4)));
	        d.setQualification(rs.getString(5));
	        d.setFirst_name(rs.getString(6));
	        d.setLast_name(rs.getString(7));
	        d.setDob(rs.getDate(8).toString());
	        d.setMobile_number(Double.toString(rs.getDouble(9)));
	        d.setAlternate_mobile_number(Double.toString(rs.getDouble(10)));
	        d.setEmail(rs.getString(11));
	        d.setDepartmentid(Integer.toString(rs.getInt(12)));
	        list.add(d);
	    }

	    return list;
	}
}
