import java.sql.Connection;


public class Global {
	  	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/database1";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "mysql";
	   static  Connection conn = null;
	

}
