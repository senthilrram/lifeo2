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
public class SuperAdminDAO  extends ConnectionDAO{

	private static final String UDATE_ADMIN_DETAILS_TABLE = "UPDATE SuperAdmin SET ";
	/**
	 * 
	 */
	public SuperAdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Result updateAdminProfile(User user, Connection connectionDB) throws SQLException {
		
		
		Result result = new Result();
		
        try {
        	
        	
        	Statement stmt = connectionDB.createStatement();
        	StringBuilder statement=new StringBuilder();
        	statement.append(UDATE_ADMIN_DETAILS_TABLE);
        	if(user.getAdmin().getLast_name() !=null && user.getAdmin().getLast_name() !="")
        		statement.append(",last_name='"+user.getAdmin().getLast_name()+"'");
        	if(user.getAdmin().getMobile_number() !=null && user.getAdmin().getMobile_number() !="")
        		statement.append(",mobile_number='"+user.getAdmin().getMobile_number()+"'");
        	
        	if(user.getAdmin().getEmail() !=null && user.getAdmin().getEmail() !="")
        		statement.append(",email='"+user.getAdmin().getEmail()+"'");
        	
        	
        	statement.append(" where adminid='"+user.getAdmin().getAdminid()+"';");
        	System.out.println("Update statement to be sent is :"+statement.toString());
        	if(stmt.executeUpdate(statement.toString()) > 0)
        	{
        		
        			result.setMessage("Admin details updated successfully in Admin Table");
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
