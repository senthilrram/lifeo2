/**
 * 
 */
package com.lifeo2.Services;

import java.security.Security;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.lifeo2.Services.Utilities.SendMassEmail;
import com.lifeo2.database.dao.ConnectionDAO;
import com.lifeo2.database.dao.HospitalDAO;
import com.lifeo2.database.dao.PatientDAO;
import com.lifeo2.database.model.Result;
import com.lifeo2.database.model.SuperAdmin;


/**
 * @author erasent
 *
 */
@Path("/SuperAdmin")
public class SuperAdminService {

	/**
	 * 
	 */
	public SuperAdminService() {
		// TODO Auto-generated constructor stub
	}
	@Path("/sendEmails")
	@POST @Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrievePatientEmails(final SuperAdmin admin){
		Result resultJSON = new Result();
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		try
		{
				System.out.println("retrieving the Patient Emails:");
				;
				return Response.status(200).entity(new SendMassEmail().sendSSLMessage(PatientDAO.getEmails(), admin.getEmailSubject(),
						admin.getEmailMessage(), admin.getEmailFrom())).build();
			
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
		return Response.status(200).entity(resultJSON).build();
		
	}
	
}
