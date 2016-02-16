/**
 * 
 */
package com.lifeo2.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.User;

/**
 * @author erasent
 *
 */
public class DoctorDAO {

	private static final String UDATE_DOCTOR_DETAILS_TABLE = "UPDATE Doctors SET ";
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

}
