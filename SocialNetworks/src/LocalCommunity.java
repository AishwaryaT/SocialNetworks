import java.util.ArrayList;
import java.util.Iterator;


public class LocalCommunity {
	static int counter = 0;
	
		public static void detectLocalCommunity(int u, int v){
				
				String C_name = "";
				//queue represents the community list of a user
	   			ArrayList<String> queue_u = new ArrayList<String>();
	   			ArrayList<String> queue_v = new ArrayList<String>();
	   			
	   			//friends represent direct neighbors of a user
	   			ArrayList<Integer> friends_u = new ArrayList<Integer>();
	   			ArrayList<Integer> friends_v = new ArrayList<Integer>();
	   			
	   			//C_uv will contain the members of a community
	   			ArrayList<Integer> C_uv = new ArrayList<Integer>();
	   			ArrayList<String> temp;
	   			boolean commonFlag;
	   			
	   			if(Global.Users.containsKey(u) ) 
	   				queue_u = Global.Users.get(u);
   			
	   			if(Global.Users.containsKey(v) ) 
	   				queue_v = Global.Users.get(v);
	   			
	   			commonFlag = MySQLAccess.checkCommonCommunity(queue_u, queue_v);
		   		if(!commonFlag){
	   				
		   			//calculate common friends
		   			friends_u = MySQLAccess.findFriends(u);
		   			friends_v = MySQLAccess.findFriends(v);
		 			C_uv = MySQLAccess.findCommonFriends(friends_u, friends_v);
		   			C_uv.add(u);
		   			C_uv.add(v);
		   			
		   			if(checkEligibility(C_uv)){
			  			//update C 
			   			C_name = ("C"+ counter++);
			   	   		Global.C.put(C_name, C_uv);
			   		   		
			   		   	//update Users
				   		//PriorityQueue<Integer> queue = null;
			   		   	// if the user is already existing get the array list of the communities 
			   		  	//he belongs to and 
			   		   	//add this community to the list
			   		 	//else, add the user to the hash map an add this community to 
			   		   	//the community list
			   		   	
				   		if(Global.Users.containsKey( u ) ){
				   				temp = Global.Users.get(u);
				   		} 
				   		else{
				   			temp = new ArrayList<String>();
				   		}
				   		temp.add(C_name);
				   		Global.Users.put(u,temp);
			   		   		
				   		
				   		if(Global.Users.containsKey( v ) ){
				   			temp = Global.Users.get(v);
				   		} 
				   		else{
				   			temp = new ArrayList<String>();
				   		}
				   		temp.add(C_name);
				   		Global.Users.put(v,temp);
			   		}
			   			
			   		MySQLAccess.displayCommunity(C_name);
			   		MySQLAccess.displayUsers(u);
			   		MySQLAccess.displayUsers(v);
			   		checkEligibility(C_uv);
	   			
		   		}
		   		else {
		   			System.out.println(u +" and "+ v +" have a common community");
		   		}
		}
		
		
		public static boolean checkEligibility(ArrayList<Integer> C_uv){
			 ArrayList<Integer> C_uv_temp = new ArrayList<Integer>(C_uv);
			 double delta = 0.0;
			 double tow = 0.0;
			 int clique_size = 0;
			 int C_in_counter = 0;
			 int size_c_uv = C_uv_temp.size();
			//System.out.println("c_uv"+C_uv.size());
			
			Iterator<Integer> it = C_uv_temp.iterator();
			while (it.hasNext()) {
				//System.out.println("Processing member:" + it.next());
				ArrayList<Integer> frn = MySQLAccess.findFriends(it.next());
				for(int friend : frn){
					if(C_uv_temp.contains(friend))
						C_in_counter++;
				}
				it.remove();
			}
			
			
			System.out.println("The C_in value is " + C_in_counter);
			
			// Calculate Delta
			clique_size = (size_c_uv* (size_c_uv - 1)) / 2;
			System.out.println("Clique size is " + clique_size);
			delta = C_in_counter /clique_size;
			System.out.println(" Value of delta is "+delta);
			
			// Calculate tow
			
			tow = Math.pow(clique_size, - 1/clique_size); 
			System.out.println(" Value of tow is "+ tow);
			
			if (delta >= tow)
				return true;
			else
				return false;
			
		}
		
		
		
}
 

	
