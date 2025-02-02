package Users;

import java.util.ArrayList;
import java.util.List;

import packageComponents.Flight;
import packageComponents.Hotel;
import packageComponents.Taxi;
import travelPackage.TravelPackageBuilder;

public class User {
	private String username;
	private String password;
	private int reservationsMade,flightsBooked, hotelsBooked,taxisBooked;
	private float totalMoneySpent, flightMoney,hotelMoney,taxiMoney;
	private List<Flight> flights;
	private List<Hotel> hotels;
	private List<Taxi> taxis;
	
	//Constructor
	public User(String username, String password) {
		this.password=password;
		this.username=username;
		this.flightsBooked=0;
		this.hotelsBooked=0;
		this.reservationsMade=0;
		this.taxisBooked=0;
		this.totalMoneySpent=0;
		this.taxiMoney=0;
		this.hotelMoney=0;
		this.flightMoney=0;
		hotels= new ArrayList();
		taxis=new ArrayList();
		flights=new ArrayList();
	}
	//methods to increment the flightsBooked, numer of reservations, etc.
	public void incrementFlight() {flightsBooked++;}
	public void incrementHotel() {hotelsBooked++;}
	public void incrementTaxi() {taxisBooked++;}
	public void incrementReservations() {reservationsMade++;}
	public void addMoneySpent(float money) {
		this.totalMoneySpent+=money;
	}
	//methods to add flight money 
	public void addFlightMoney(float money) {
		this.flightMoney+=money;
	}
	public void addTaxiMoney(float money) {
		this.taxiMoney+=money;
	}
	public void addHotelMoney(float money) {
		this.hotelMoney+=money;
	}
	public void addHotel(Hotel hotel) {
		hotels.add(hotel);
	}
	public void addFlight(Flight flight) {
		flights.add(flight);
	}public void addTaxi(Taxi taxi) {
		taxis.add(taxi);
	}
	public String toTXT() {
		return username+","+password;
	}
	//read CSV
	public static User fromCSV(String csv) {
		String[] parts = csv.split(",");
		return new User(parts[0],parts[1]);
	}
	//set details when reading csv or text file
	public void setDetails(int flightsBooked, int hotelsBooked, int reservationsMade, 
            int taxisBooked, float totalMoneySpent, 
            float taxiMoney, float hotelMoney,float flightMoney) {
this.flightsBooked = flightsBooked;
this.hotelsBooked = hotelsBooked;
this.reservationsMade = reservationsMade;
this.taxisBooked = taxisBooked;
this.totalMoneySpent = totalMoneySpent;
this.taxiMoney = taxiMoney;
this.hotelMoney = hotelMoney;
this.flightMoney = flightMoney;
}
	//from text formatter and User initializer
	public static User fromText(String textLine) {
	    String[] data = textLine.split(" ");
	    String username = data[0];
	    String password = data[1];
	    
	    // Create the User object with just username and password
	    User user = new User(username, password);
	    
	    // Now set the additional fields
	    int flightsBooked = Integer.parseInt(data[2]);
	    int hotelsBooked = Integer.parseInt(data[3]);
	    int reservationsMade = Integer.parseInt(data[4]);
	    int taxisBooked = Integer.parseInt(data[5]);
	    float totalMoneySpent = Float.parseFloat(data[6]);
	    float taxiMoney = Float.parseFloat(data[7]);
	    float hotelMoney = Float.parseFloat(data[8]);
	    float flightMoney =Float.parseFloat(data[9]);
	    
	    user.setDetails(flightsBooked, hotelsBooked, reservationsMade, 
	                    taxisBooked, totalMoneySpent, taxiMoney, hotelMoney, flightMoney);
	    
	    return user;
	}
	@Override
	public String toString() {
		return "Username: "+username+"\nPassword: "+password+"\nTotal Spent: $"+totalMoneySpent
				+"\nReservations Made: "+reservationsMade+"\nTaxis Booked: "+taxisBooked
				+"\nHotels Booked: "+ hotelsBooked+"\nFlights Booked: "+flightsBooked
				+"\nMoney spent on Taxi: $"+ taxiMoney+"\nMoney Spent on Hotels: $"+ hotelMoney
				+"\nMoney spent on flights: $"+ flightMoney;
	}
	//getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	public int getReservationsMade() {
		return reservationsMade;
	}
	public int getFlightsBooked() {
		return flightsBooked;
	}
	public int getHotelsBooked() {
		return hotelsBooked;
	}
	public int getTaxisBooked() {
		return taxisBooked;
	}
	public float getTotalMoneySpent() {
		return totalMoneySpent;
	}
	public float getFlightMoney() {
		return flightMoney;
	}
	public float getHotelMoney() {
		return hotelMoney;
	}
	public float getTaxiMoney() {
		return taxiMoney;
	}
	
	
	
}
