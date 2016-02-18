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
@Path("/Patient")
public class PatientService {

	/**
	 * 
	 */
	
	public PatientService() {
		// TODO Auto-generated constructor stub
	}






}
