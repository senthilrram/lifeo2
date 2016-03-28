package com.lifeo2.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lifeo2.database.model.Department;
import com.lifeo2.database.model.Doctor;
import com.lifeo2.database.model.Result;

public class HospitalDAO extends ConnectionDAO{
	
	private static final String QUERY_HOSPITALID_NAME = "SELECT hospitalid,hospital_name FROM Hospitals";
	private static final String SELECT_ALL_DEPARTMENTS = "SELECT * FROM Departments";
	

	public static Result getHospitalDetails() throws SQLException {
	
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		try {
        	
        	ResultSet rs = stmt.executeQuery(QUERY_HOSPITALID_NAME);
        	List<HashMap<String, String>> returnList=convertResultSetToList(rs);
        	rs.close();
        	if(returnList.size() > 0)
        	{
        		result.setMessage("Retrieve Hospitals Details Success");
				result.setType(Result.SUCCESS);
        		result.hospitalList=returnList;
        		return result;
        	}
        	else
        	{
        		result.setMessage("Retrieve Hospital Details failed:");
				result.setType(Result.SUCCESS);
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
	public static List<HashMap<String, String>> convertResultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

	    while (rs.next()) {
	        HashMap<String,String> row = new HashMap<String, String>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(rs.getString(1),rs.getString(2));
	        }
	        list.add(row);
	    }

	    return list;
	}
	
public static Result getAllDepartments() throws SQLException {
		
		Connection connection = getConnection();
		Result result = new Result();
		Statement stmt = connection.createStatement();
		try {
        	
        	ResultSet rs = stmt.executeQuery(SELECT_ALL_DEPARTMENTS);
        	List<Department> returnList=convertToDepartmentsList(rs);
        	rs.close();
        	if(returnList.size() > 0)
        	{
        		result.setMessage("Retrieve deaprtments list success");
				result.setType(Result.SUCCESS);
        		result.departmentsList=returnList;
        		return result;
        	}
        	else
        	{
        		result.setMessage("Retrieve deaprtments list failed:");
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

public static List<Department> convertToDepartmentsList(ResultSet rs) throws SQLException {
    List<Department> list = new ArrayList<Department>();

    while (rs.next()) {
    	Department d = new Department();
        
        
        d.setHospitalid(Integer.toString(rs.getInt(1)));
        d.setDepartmentid(Integer.toString(rs.getInt(2)));
        d.setDepartment_name(rs.getString(3));
        d.setDepartment_details(rs.getString(4));
        d.setDepartment_head(Integer.toString(rs.getInt(5)));
        String departmentid = Integer.toString(rs.getInt(2));
        Result result = DoctorDAO.getAllDoctorsByDepartmentId(departmentid);
        d.setDoctorsList(result.doctorsList);
        
        
        list.add(d);
    }

    return list;
}
}
