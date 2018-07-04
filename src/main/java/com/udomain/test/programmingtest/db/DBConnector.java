package com.udomain.test.programmingtest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection connectDB() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// DB connection config
			// In action application, they should be put into config file instead of hardcode.
			Connection con=DriverManager.getConnection("jdbc:mysql://18.222.158.181:3306/test","root","gKldscaj#dcds-$");
			return con;
		} catch (ClassNotFoundException ce)
		{
			ce.printStackTrace();
			return null;
		}
	}
	
	public static boolean disconnectDB(Connection con){
		boolean success = false;
		try{
			con.close();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return success;
	}
}
