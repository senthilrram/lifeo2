/**
 * 
 */
package com.lifeo2.Services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.lifeo2.database.dao.HospitalDAO;
import com.lifeo2.database.dao.UserDAO;
import com.lifeo2.database.model.Doctor;
import com.lifeo2.database.model.Patient;
import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.SuperAdmin;
import com.lifeo2.database.model.User;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.*;

import org.json.JSONException;

import sun.misc.BASE64Decoder;



/**
 * @author erasent
 * 
 */
@Path("/user")
public class UserService {

	/**
	 * 
	 */
	private String userName = "";
	private String passWord = "";

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doAuth(@HeaderParam("authorization") String authString)
			 {
		Result resultJSON = new Result();
		 Gson gson = new Gson();
		try
		{
			if(authString != null && authString != "")
			{
				System.out.println("Authorization String received from the Header is:" + authString);
				doRetrieveSetUserNamePassword(authString);
				return Response.status(200).entity(gson.toJson(isUserAuthenticated(userName,passWord))).build();
			}
			else
			{
				resultJSON.setMessage("Unauthorized access:Please check with Administrator");
				resultJSON.setType(Result.SUCCESS);
				return  Response.status(200).entity(gson.toJson(resultJSON)).build();
			}
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at doAuth");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
			
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at doAuth");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
			
		}
		catch (Exception exe)
		{
			System.out.println("Exception at doAuth");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return  Response.status(200).entity(gson.toJson(resultJSON)).build();
		
		
		
		
	}
	
	@Path("/register")
	@POST @Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(final User user){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
			System.out.println(user.getUsername()+user.getPassword()+user.getUserType());
			if(user!=null){
				
				String resultStr=checkMandatoryParamRegister(user);
				if(resultStr != null && resultStr!="")
				{
					resultJSON.setType(Result.ERROR).setMessage(resultStr);
					
				}
					
				else
					return Response.status(200).entity(gson.toJson(UserDAO.addNewUser(user))).build();
					
				
			}
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at addUser");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at addUser");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
		}
		catch (Exception exe)
		{
			System.out.println("Exception at addUser");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
		
	}
	
	private String checkMandatoryParamRegister(User user) {
		// TODO Auto-generated method stub
		
		//User Mandatory parameters
		if(user.getUsername() == null || user.getUsername()=="")
			return "Username Parameter is missing";
		if(user.getPassword() == null || user.getPassword()=="")
			return "Password Parameter is missing";
		if(user.getUserType() == null || user.getUserType()=="")
			return "UserType Parameter is missing";
		if(user.getPatient() != null)
		{
			Patient patient = user.getPatient();
			if(patient.getFirst_name() == null || patient.getFirst_name() == "")
				return "FirstName Parameter is missing in Patient";
			if(patient.getLast_name() == null || patient.getLast_name() == "")
				return "LastName Parameter is missing in Patient";
			if(patient.getBloodgroup() == null || patient.getBloodgroup() == "")
				return "BloodGroup Parameter is missing in Patient";
			if(patient.getDob() == null || patient.getDob() == "")
				return "Dob Parameter is missing in Patient";
			if(patient.getGender() == null || patient.getGender() == "")
				return "Gender Parameter is missing in Patient";
			if(patient.getMobile_number() == null || patient.getMobile_number() == "")
				return "Mobile Number Parameter is missing in Patient";
			
		}
		if(user.getDoctor() != null)
		{
			Doctor doctor = user.getDoctor();
			if(doctor.getExperience() == null || doctor.getExperience() == "")
				return "Experience Parameter is missing in Doctor";
			if(doctor.getMobile_number() == null || doctor.getMobile_number() == "")
				return "MobileNumber Parameter is missing in Doctor";
			if(doctor.getAlternate_mobile_number() == null || doctor.getAlternate_mobile_number() == "")
				return "Alternate Mobile Number Parameter is missing in Doctor";
		}
		if(user.getAdmin() != null)
		{
			SuperAdmin admin = user.getAdmin();
			if(admin.getFirst_name() == null || admin.getFirst_name() == "")
				return "FirstName Parameter is missing in Super Admin";
			if(admin.getLast_name() == null || admin.getLast_name() == "")
				return "LastName Parameter is missing in Super Admin";
			if(admin.getMobile_number() == null || admin.getMobile_number() == "")
				return "Mobile Number Parameter is missing in Super Admin";
			
		}
			
		return null;
	}
	@Path("/retrieveHospDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retreiveHospitalDetails(){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
				System.out.println("retrieving the Hospital Details:");
				return Response.status(200).entity(gson.toJson(HospitalDAO.getHospitalDetails())).build();
			
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at retreiveHospitalDetails");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at retreiveHospitalDetails");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
		}
		catch (Exception exe)
		{
			System.out.println("Exception at retreiveHospitalDetails");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
		
	}
	
	@Path("/retrieveUserDetails/{username}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retreiveUserDetails(@PathParam("username") String username, @PathParam("password") String password){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
				System.out.println("retrieving the User Details for showing before profile update:"+username+":"+password);
				return Response.status(200).entity(gson.toJson(UserDAO.getUserDetails(username,password))).build();
			
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at retreiveHospitalDetails");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+jsonexe.getMessage());
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at retreiveHospitalDetails");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+sqlexe.getMessage());
		}
		catch (Exception exe)
		{
			System.out.println("Exception at retreiveHospitalDetails");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Please contact system administrator for the issue:"+exe.getMessage());
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
		
	}
	@Path("/updateProfile")
	@POST @Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateUser(final User user){
		Result resultJSON = new Result();
		Gson gson = new Gson();
		try
		{
			if(user!=null){
				System.out.println("UserDetails Received:"+user.getUsername()+user.getPassword()+user.getUserId());
				if(user.getUserId() == null || user.getUserId()== "")
				{
					resultJSON.setType(Result.ERROR).setMessage("User Id is mandatory to perform update");
					return Response.status(200).entity(gson.toJson(resultJSON)).build();
				}
				if(user.getUserType() != null && user.getUserType()!= "")
				{
					resultJSON.setType(Result.ERROR).setMessage("User Type cannot be updated:Please consider registering another account");
					return Response.status(200).entity(gson.toJson(resultJSON)).build();
				}
				
			/*	if(user.getPatient() != null)
				{
					if(user.getPatient().getFirst_name() != null && user.getPatient().getFirst_name()!= "")
					{
						resultJSON.setType(Result.ERROR).setMessage("FirstName of the Patient cannot be updated");
						return Response.status(200).entity(resultJSON).build();
					}
					if(user.getPatient().getLast_name() != null && user.getPatient().getLast_name()!= "")
					{
						resultJSON.setType(Result.ERROR).setMessage("LastName of the Patient cannot be updated");
						return Response.status(200).entity(resultJSON).build();
					}
				}
				
				if(user.getAdmin()!=null)
				{
					if(user.getAdmin().getFirst_name() != null && user.getAdmin().getFirst_name()!= "")
					{
						resultJSON.setType(Result.ERROR).setMessage("FirstName of the Admin cannot be updated");
						return Response.status(200).entity(resultJSON).build();
					}
					if(user.getAdmin().getLast_name() != null && user.getAdmin().getLast_name()!= "")
					{
						resultJSON.setType(Result.ERROR).setMessage("LastName of the Admin cannot be updated");
						return Response.status(200).entity(resultJSON).build();
					}
				}*/
				return Response.status(200).entity(gson.toJson(UserDAO.updateUser(user))).build();
			}
		}
		catch(JSONException jsonexe)
		{
			System.out.println("JSONException at updateProfile");
			jsonexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("JSONException at updateProfile.");
		}
		catch(SQLException sqlexe)
		{
			System.out.println("SQLException at updateProfile");
			sqlexe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("SQLException at updateProfile");
		}
		catch (Exception exe)
		{
			System.out.println("Exception at updateProfile");
			exe.printStackTrace();
			resultJSON.setType(Result.ERROR).setMessage("Exception at updateProfile");
			
		}
		finally
		{
			System.out.println("Finally");
			
		}
		return Response.status(200).entity(gson.toJson(resultJSON)).build();
	}
		
	private void doRetrieveSetUserNamePassword(String authString) {
		// TODO Auto-generated method stub
		byte[] bytes = null;
		authString=authString.replace("Basic ", "");
        try {
            bytes = new BASE64Decoder().decodeBuffer(authString);
            String decodedAuth = new String(bytes);
            userName=decodedAuth.split(":")[0].trim();
            passWord=decodedAuth.split(":")[1].trim();
            System.out.println(userName+":"+passWord);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new JSONException("Null Pointer Exception at the doRetrieveSetUserNamePassword()");
        }
        
	}

	private Result isUserAuthenticated(String uname, String pwd) throws SQLException {
		
		return UserDAO.dologin(uname, pwd);
	}

}
