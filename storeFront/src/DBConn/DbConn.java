package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConn {
	
	public static void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception ex) {
			//handle
		}
	}
	
	 public static ResultSet connectToDB(String SQL){
	       
	        try{
	            
	        	DriverManager.getConnection("jdbc:mysql://localhost:3306/yr3test");
	            String host = "jdbc:sqlserver://localhost;databaseName=yr3test;Trusted_Connection=True";
	            String uName = "user";
	            String uPass = "pass";
	            String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
	    
	            Connection con = DriverManager.getConnection(host, uName, uPass);
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(SQL);
	            
	            return rs;
	    
	        }
	        catch(SQLException err) {
	            System.out.println(err.getMessage());
	        }
	        return null;
	             
	    }
	
	 public static void executeSQL(String SQL){
	       
	        try{
	            
	            String host = "jdbc:sqlserver://localhost;databaseName=STOREFRONT;Trusted_Connection=True";
	            String uName = "user";
	            String uPass = "pass";
	            String connectionString = "jdbc:sqlserver://localhost;Database=master;Trusted_Connection=True;";
	    
	            Connection con = DriverManager.getConnection(host, uName, uPass);
	            Statement stmt = con.createStatement();
	            stmt.executeQuery(SQL);
	            
	            
	    
	        }
	        catch(SQLException err) {
	            System.out.println(err.getMessage());
	        }
	       
	             
	    }
	 
	public static void update(String SQL) {
			
		
	}
	 
	 
	 public static void main(String SQL){
	        connectToDB(SQL);
	        
	    }

	}


