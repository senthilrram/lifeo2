package com.lifeo2.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.lifeo2.Services.Utilities.PropsUtil;


public class ConnectionDAO {

	private static String dbDriverClassName;
	private static String dbHost;
	private static String dbPort;
	private static String dbName;
	private static String dbUsername;
	private static String dbPassword;
	private static boolean local;
	
	public static Connection getConnection()
	{
		try {
			try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
			dbDriverClassName = PropsUtil.getValue("jdbc.driver.class.name");
			local = PropsUtil.getValue("environment").equalsIgnoreCase("local")?true:false;
			local=true;
			if(local){
				dbHost = PropsUtil.getValue("jdbc.host");
				dbPort = PropsUtil.getValue("jdbc.port");
				dbUsername = PropsUtil.getValue("jdbc.user.username");
				dbPassword = PropsUtil.getValue("jdbc.user.password");
				
			}
			else{
				dbHost = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
				dbPort = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
				dbUsername = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
				dbPassword = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
				System.out.println("Host: " + System.getenv("OPENSHIFT_MYSQL_DB_HOST"));
		        System.out.println("Port: " + System.getenv("OPENSHIFT_MYSQL_DB_PORT"));
		        System.out.println("Username: " + System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
		        System.out.println("Password: " + System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
			}
			
			dbName = PropsUtil.getValue("jdbc.database");
			
			
			
			String conString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
			
			System.out.println("conn string : " + conString);
			System.out.println(dbUsername+":"+dbPassword);
			Connection connection = DriverManager.getConnection(conString,dbUsername,dbPassword);
			
			return connection;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}
