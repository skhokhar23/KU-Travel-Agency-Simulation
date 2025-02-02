package Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import handler.FileHandler;

public class UserManager {
	private static Map<String, User> users;
	private static List<User> allUserStats;
	private static User activeUser;
	
	public UserManager() {
		users= FileHandler.loadUsers("users.txt");
		allUserStats=loadUsersFromTextFile("user-stats.txt");
	}
	public static void addUser(User user) {
		if(!users.containsKey(user.getUsername())) {
			users.put(user.getUsername(), user);
		}
	}
	
	// Method to rank users based on total money spent
    public void rankUsersByTotalMoneySpent() {
        Collections.sort(allUserStats, new UserMoneySpentComparator());
    }

    // Method to print ranked users
    public static String printRankedUsers() {
    	String s="";
        for (User  user :allUserStats) {
            s+=user.getUsername();
            s+=": " ;
            s+=user.getTotalMoneySpent();
            s+="\n";
        }
        return s;
    }

	public static User getUser(String username) {
		if(users.containsKey("")) {
			return users.get(username);
		}
		else {
			return null;
		}
	}
	public static Map<String, User> getAllUsers() {
		return users;
	}

	public void loadUsers(String filePath) {
		users= FileHandler.loadUsers(filePath);
	}
	public void saveUsers(String filePath) {
		FileHandler.saveUsers(filePath,users);
	}
	
	public static User login(String username , String password) {
		User active = null;
		for(String u: users.keySet()) {
			if(u.equals(username)) {
				active= users.get(u);
			}
		}
		return active;
	}
	public static User getActiveUser() {
		return activeUser;
	}
	public static void setActiveUser(User act) {
		activeUser = act;
		
		
	}public static void saveUsersToTextFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User  user : allUserStats) {
                writer.write(userToText(user)); // Convert user to text format
                writer.newLine(); // Add a new line after each user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert User object to text line
    private static String userToText(User user) {
        return String.join(" ",
                user.getUsername(), 
                user.getPassword(), 
                String.valueOf(user.getFlightsBooked()), 
                String.valueOf(user.getHotelsBooked()),
                String.valueOf(user.getReservationsMade()), 
                String.valueOf(user.getTaxisBooked()), 
                String.valueOf(user.getTotalMoneySpent()), 
                String.valueOf(user.getTaxiMoney()), 
                String.valueOf(user.getHotelMoney()), 
                String.valueOf(user.getFlightMoney())
        );
    }
	
	
	public List<User> loadUsersFromTextFile(String filename) {
		allUserStats= new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromText(line); // Create User from text line
                allUserStats.add(user);// Add User to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUserStats;
    }
	public static Map<String, User> getUsers() {
		return users;
	}
	public static List<User> getAllUserStats() {
		return allUserStats;
	}
	 public static void  updateUser (User updatedUser ) {
		 boolean isPresent = false;
		 if(allUserStats==null) {
			 allUserStats=new ArrayList();
			 allUserStats.add(updatedUser);
		 }
		 else {
			 for (int i = 0; i < allUserStats.size(); i++) {
		          
		            if (allUserStats.get(i) .getUsername().equals(updatedUser .getUsername())) {
		            	allUserStats.set(i, updatedUser );
		            	isPresent=true;
		            }
		        }
			 if(!isPresent) {
				 allUserStats.add(updatedUser);
			 }
			 System.out.println(allUserStats);
		 }
	        
	    }
	
	
	
	
	
	
	
}
