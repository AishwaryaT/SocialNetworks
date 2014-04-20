import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.*;


public class Global {
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/database1";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "mysql";
	   static  Connection conn = null;
	   
	   //Output data structures
	 	   
	   static Map<Integer, ArrayList<String>> Users = new HashMap<Integer, ArrayList<String>>();
	   static Map<String, ArrayList<Integer>> C = new HashMap<String, ArrayList<Integer>>();
	   
	   public static Connection getConnection(){
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			 return conn;
		}
			
		     
		     
	   }
	

}
