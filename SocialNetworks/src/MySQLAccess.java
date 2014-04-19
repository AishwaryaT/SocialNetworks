//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;


public class MySQLAccess {
	
	
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/database1";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "mysql";
   static  Connection conn = null;
   
   
   public static void main(String[] args) {
	   ArrayList<Integer> friends = new ArrayList<Integer>();
	   ArrayList<Integer> friends1 = new ArrayList<Integer>();
	   ArrayList<Integer> commonFriends = new ArrayList<Integer>();
	   
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	     //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM test";
	      ResultSet rs = stmt.executeQuery(sql);
	
	     //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int v1  = rs.getInt("A");
	         int v2 = 2;      
	          friends = findFriends(v1,conn);
	          friends1 = findFriends(v2,conn);
	          commonFriends = findCommonFriends(friends, friends1);
	          displayFriends(friends);
	          displayFriends(friends1);
	          displayFriends(commonFriends);
	          break;
	  
	      }
      
	      String sql1;
	      sql = "SELECT * FROM test";
	      ResultSet rs1 = stmt.executeQuery(sql);
	      
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }
	   
	   catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	   
	   finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	


private static ArrayList<Integer> findCommonFriends(ArrayList<Integer> friends,
		ArrayList<Integer> friends1) {
	
	ArrayList<Integer> temp = new ArrayList<Integer>();
	System.out.println("hiiiiii");
	for (int counter = 0; counter < friends.size(); counter++) {
        if (friends1.contains(friends.get(counter))) {
        	System.out.println("Im inside if block");
            temp.add(friends.get(counter));
        }
    }
	return temp;
}



private static void displayFriends(ArrayList<Integer> friends) {
	if (friends.size() > 0){
	for(int a: friends)
   	 System.out.print(a + " ");
    System.out.println();
	}
}



private static ArrayList<Integer> findFriends(int v1, Connection conn) {
	ArrayList<Integer> temp = new ArrayList<Integer>();
	Statement stmt = null;
	 try {
		stmt = conn.createStatement();
		 String sql;
	     sql = "SELECT B FROM test where A =" + v1;
	     ResultSet rs = stmt.executeQuery(sql);
	     
	     while(rs.next()){
	         //Retrieve by column name
	        temp.add(rs.getInt("B"));
	        
	      }
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		 return temp;
	 }
	 
}


public static boolean isFriend(int u, int v, Connection conn){
	boolean friendship = false;
	Statement stmt = null;
	 try {
		stmt = conn.createStatement();
		 String sql;
	     sql = "SELECT B FROM test where A =" + u;
	     ResultSet rs = stmt.executeQuery(sql);
	     
	     while(rs.next()){
	         //Retrieve by column name
	    	 if(v == rs.getInt("B")){
	    		 friendship = true;
	    		 break;
	    	 }
	        
	       }
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		 return friendship;
	 }
	
}
}//end FirstExample