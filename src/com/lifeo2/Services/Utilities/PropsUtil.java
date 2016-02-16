package com.lifeo2.Services.Utilities;

import java.util.ResourceBundle;



/**
 * @author 
 *
 */
public final class PropsUtil {

	private static String DB_PROPERTY_FILE = "dataservice";
	
	public static String getValue(String key) {
		String rtnStr = "";
		try {
			ResourceBundle myBundle = ResourceBundle.getBundle(DB_PROPERTY_FILE);
			if (myBundle != null)
				rtnStr = myBundle.getString(key).trim();

		} catch (Exception e) {
			
		}
		return rtnStr;
	}
	
	
}
