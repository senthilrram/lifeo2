/**
 * 
 */
package com.lifeo2.Services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.lifeo2.database.dao.DoctorDAO;
import com.lifeo2.database.dao.HospitalDAO;
import com.lifeo2.database.model.Result;

/**
 * @author erasent
 *
 */
@Path("/doctor")
public class DoctorService {

	/**
	 * 
	 */
	public DoctorService() {
		// TODO Auto-generated constructor stub
	}
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDoctors(){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
				System.out.println("retrieving the doctors list:");
				return Response.status(200).entity(gson.toJson(DoctorDAO.getAllDoctors())).build();
			
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at getAllDoctors");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at getAllDoctors");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
		}
		catch (Exception exe)
		{
			System.out.println("Exception at getAllDoctors");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
		
	}
	
	@Path("/list/{deptid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDoctorsByDeptId(@PathParam("deptid") String deptId){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
				System.out.println("retrieving the doctors list:");
				return Response.status(200).entity(gson.toJson(DoctorDAO.getAllDoctorsByDepartmentId(deptId))).build();
			
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at getAllDoctors by dept id");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at getAllDoctors  by dept id");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
		}
		catch (Exception exe)
		{
			System.out.println("Exception at getAllDoctors  by dept id");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
		
	}

}
