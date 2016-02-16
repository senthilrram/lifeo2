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
import com.lifeo2.database.model.Patient;
import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.SuperAdmin;
import com.lifeo2.database.model.User;

/**
 * @author erasent
 *
 */
public class PatientDAO extends ConnectionDAO{
	
	private static final Object UDATE_PATIENT_DETAILS_TABLE = "UPDATE Patients SET ";
	private static final String QUERY_EMAILS = "SELECT email FROM Patients";

	/**
	 * 
	 */
	public PatientDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Result updatePatientProfile(User user, Connection connectionDB) throws SQLException {
		
		
		Result result = new Result();
		
        try {
        	
        	
        	Statement stmt = connectionDB.createStatement();
        	StringBuilder statement=new StringBuilder();
        	statement.append(UDATE_PATIENT_DETAILS_TABLE);
        	if(user.getPatient().getBloodgroup() !=null && user.getPatient().getBloodgroup() !="")
        		statement.append("bloodgroup='"+user.getPatient().getBloodgroup()+"'");
        	if(user.getPatient().getFirst_name() !=null && user.getPatient().getFirst_name() !="")
        		statement.append("first_name='"+user.getPatient().getFirst_name()+"'");
        	if(user.getPatient().getLast_name() !=null && user.getPatient().getLast_name() !="")
        		statement.append("last_name='"+user.getPatient().getLast_name()+"'");
        	if(user.getPatient().getDob() !=null && user.getPatient().getDob() !="")
        		statement.append(",dob='"+user.getPatient().getDob()+"'");
        	if(user.getPatient().getGender() !=null && user.getPatient().getGender() !="")
        		statement.append(",gender='"+user.getPatient().getGender()+"'");
        	if(user.getPatient().getMobile_number() !=null && user.getPatient().getMobile_number() !="")
        		statement.append(",mobile_number='"+user.getPatient().getMobile_number()+"'");
        	if(user.getPatient().getAlternate_mobile_number() !=null && user.getPatient().getAlternate_mobile_number() !="")
        		statement.append(",alternate_mobile_number='"+user.getPatient().getAlternate_mobile_number()+"'");
        	if(user.getPatient().getEmail() !=null && user.getPatient().getEmail() !="")
        		statement.append(",email='"+user.getPatient().getEmail()+"'");
        	if(user.getPatient().getAddress() !=null && user.getPatient().getAddress() !="")
        		statement.append(",address='"+user.getPatient().getAddress()+"'");
        	
        	statement.append(" where patientid='"+user.getPatient().getPatientid()+"';");
        	System.out.println("Update statement to be sent is :"+statement.toString());
        	if(stmt.executeUpdate(statement.toString()) > 0)
        	{
        		
        			result.setMessage("Patient details updated successfully in Patient Table");
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

	public static ArrayList getEmails() throws SQLException {
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement stmt = connection.createStatement();
		try {

			rs = stmt.executeQuery(QUERY_EMAILS);
			return convertResultSetToArrayList(rs);

		} finally {
			System.out.println("finally");
			rs.close();
			if (stmt != null)
				connection.close();
		}

	}

	private static ArrayList<String> convertResultSetToArrayList(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columns; ++i) {
				list.add(rs.getString(1));
			}

		}

		return list;
	}
}
