//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;


public class MySQLAccess {
	   
   
   public static void main(String[] args) {
	  	   
	   Statement stmt = null;
	   Connection conn = Global.getConnection();
	   try{
	     
	      //System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      // Execute a query1
	      String sql;
	      sql = "SELECT * FROM test";
	      ResultSet rs = stmt.executeQuery(sql);
	
	     // Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int u  = rs.getInt("A");
	         int v = rs.getInt("B"); 
	         
	         System.out.println("Handling edge "+ u+" " + v+" :");
	         LocalCommunity.detectLocalCommunity(u,v);
	       }
      
	  
	      //Clean-up environment
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
	      }
	      // nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end catch
	   }//end finally
	   //System.out.println("Goodbye!");
	}//end main
	


public static ArrayList<Integer> findCommonFriends(ArrayList<Integer> friends,
		ArrayList<Integer> friends1) {
	
	ArrayList<Integer> temp = new ArrayList<Integer>();
	
	for (int counter = 0; counter < friends.size(); counter++) {
        if (friends1.contains(friends.get(counter))) {
        	//System.out.println("Im inside if block");
            temp.add(friends.get(counter));
        }
    }
	return temp;
}

public static boolean  checkCommonCommunity(ArrayList<String> com1,
		ArrayList<String> com2) {
	
	boolean commonFlag = false;

	for (int counter = 0; counter < com1.size(); counter++) {
        if (com2.contains(com1.get(counter))) {
        	//System.out.println("Im inside if block");
            commonFlag = true;
            break;
        }
    }
	return commonFlag;
}




public static void displayFriends(ArrayList<Integer> friends) {
	if (friends.size() > 0){
	for(int a: friends)
   	 System.out.print(a + " ");
    System.out.println();
	}
}



public static ArrayList<Integer> findFriends(int v1) {
	ArrayList<Integer> temp = new ArrayList<Integer>();
	Statement stmt = null;
	 Connection conn = Global.getConnection();
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
		e.printStackTrace();
	} finally{
		 return temp;
	 }
	 
}

public static void displayCommunity(String C_name){

		//Display Final Community List
   	
   		if(Global.C.containsKey(C_name)) {
   			ArrayList<Integer> temp = Global.C.get(C_name);
   			System.out.print(C_name+ " : " );
   			for (int s : temp )
   				System.out.print(s + " ");
   			System.out.println();	
   		}
		
}
//Display Communities a User belong to
  public static void displayUsers(int user) {	
	  
	   	if(Global.Users.containsKey(user)) {
   			ArrayList<String> temp = Global.Users.get(user);
   			System.out.print("List of communites for user"+ user +": ");
   			for (String s : temp )
   				System.out.print(s + " ");
   			System.out.println();	
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