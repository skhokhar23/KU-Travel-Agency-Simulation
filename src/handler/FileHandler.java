package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Users.User;

public class FileHandler {
	//method scans the users and loads them returning a map of string to users.
	public static Map<String,User> loadUsers(String filePath){
		Map<String, User> users= new HashMap();
		try {
		Scanner scanner = new Scanner(new FileReader("users.txt"));
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner lineScan= new Scanner(line).useDelimiter(",");
			if(lineScan.hasNext()) {
				String username= lineScan.next();
				if(lineScan.hasNext()) {
					String password= lineScan.next();
					User user = new User(username,password);
					users.put(user.getUsername(), user);
				}
			}
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	//This method saves Users to the text file with username and password
	public static void saveUsers(String filePath, Map<String,User> users) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			for (User u: users.values()) {
				writer.write(u.getUsername()+","+u.getPassword());
				writer.newLine();
			}
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
