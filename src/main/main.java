package main;

import Users.Admin;
import Users.UserManager;
import gui.LoginPage;
import handler.HotelsHandler;
import handler.PackageManager;
import packageComponents.FlightManager;
import packageComponents.HotelManager;
import packageComponents.TaxiManager;
import travelPackage.TravelPackageManager;

public class main {

	public static void main(String[] args) {
		/************** Pledge of Honor ******************************************
		I hereby certify that I have completed this programming project on my own
		without any help from anyone else. The effort in the project thus belongs
		completely to me. I did not search for a solution, or I did not consult any
		program written by others or did not copy any program from other sources. I
		read and followed the guidelines provided in the project description.
		READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
		SIGNATURE: <SOFIIA KHOKHAR, 86015>
		*************************************************************************/
		
		
		UserManager u= new UserManager(); //creating an instance of usermanager
		Admin admin = new Admin(); //creating an instance of admin
		u.addUser(admin); // adding admin to the users in usermananger
		
		
		PackageManager p= new PackageManager(); //creating an instance of projectmanager
		FlightManager f= new FlightManager();
		HotelManager h = new HotelManager();
		TaxiManager t = new TaxiManager();
		TravelPackageManager tpm = new TravelPackageManager();
		System.out.println(tpm.getAllPackages().toString().toString());
		
		
		LoginPage l= new LoginPage(); //creating an instance of the loginpage
		l.getFrame().setVisible(true); //making the login page visible
	}
}