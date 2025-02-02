package packageComponents;

import java.util.HashMap;
import java.util.Map;

import handler.FlightsHandler;

public class Flight {
	private boolean direct;
	private String departureCity,arrivalCity,airline,ticketClass,flightID,stopoverCity,Date;
	private String departureTime,arrivalTime, leg1depTime,leg2depTime,leg1arrTime,leg2arrTime;
	private float price;
	private int totalSeats;
	private Map<String, Integer> seatsBooked= new HashMap();
	
	//constructor for flight
	//isDirect passed as a string so it should be parsed to a boolean and other fields should be set accordingly
	public Flight(String departureCity, String arrivalCity, String isDirect, String airline) {
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		if(isDirect.equals("true")) {
			direct=true;
			departureTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 4);
			arrivalTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 5);
		}else{
			direct=false;
			stopoverCity= FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 9);
			leg1depTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 11);
			leg2depTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 13);
			leg1arrTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 12);
			leg2arrTime=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 14);
		}
		flightID=getFlightID();
		price= getPrice();
		ticketClass= getTicketClass();
		//parsing because totalSeats should be an integer
		totalSeats= Integer.parseInt(FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 8)); 	
		FlightManager.getAllBooked().putIfAbsent(flightID, seatsBooked);
	}
	//bookseat method. if no other bookings for that day, exist, add a booking to the map.
	public boolean bookSeat(String selectedDate) {
		int bookedSeats;
		if(FlightManager.getAllBooked().get(flightID).get(selectedDate)==null) {
			FlightManager.getAllBooked().get(flightID).put(selectedDate, 0);
			bookedSeats=0;
		}
		else {
			bookedSeats=FlightManager.getAllBooked().get(flightID).get(selectedDate);
		}
		if(bookedSeats<totalSeats) {
			FlightManager.getAllBooked().get(flightID).put(selectedDate, bookedSeats+1);
			FlightManager.saveBookings("flight-booking.txt", FlightManager.getAllBooked());
			return true;
		}
		else {
			System.out.println("The flight is full. No seats available.");
			return false;
		}
	}
	
	
	//avaialble seats on a specific date. scans the map for the date and gets value.
	public int getSeatsAvailable(String selectedDate) {
		if(seatsBooked.get(selectedDate)==null) {
			return totalSeats;
		}
		else {
			return totalSeats-seatsBooked.get(selectedDate);
		}
	}
	@Override
	public String toString() {
		return "Departure City: "+departureCity+ "\nArrival City: "+arrivalCity
				+ "\nAirlines: "+airline
				+ "\nTicket Class: "+ticketClass
				+ "\nTicket Price: $"+String.valueOf(price)
				+ "\nDate of Flight: "+Date;
	}
	//get ticket class by filtering
	public String getTicketClass() {
		return FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 6);//getIndexInfo for index 6 because it contains ticket class info for the flights
	}
	//a method to get price by filtering
	public float getPrice() {//getter fot the price of the flight
		System.out.println(airline);
		String price=FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 7);//getIndexInfo at index 7 because that contains the prices of the flights
		return Float.parseFloat(price);
		//return Float.parseFloat(price);//parses the string to a float
	}
	public String getFlightID() {
		return FlightsHandler.getIndexInfo(departureCity, arrivalCity, direct, airline, 0);//getIndexInfo for index 0 because it contains flight ID for the flights
	}
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public String getStopoverCity() {
		return stopoverCity;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public String getAirline() {
		return airline;
	}
	public boolean isDirect() {
		return direct;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public String getLeg1depTime() {
		return leg1depTime;
	}
	public String getLeg2depTime() {
		return leg2depTime;
	}
	public String getLeg1arrTime() {
		return leg1arrTime;
	}
	public String getLeg2arrTime() {
		return leg2arrTime;
	}
	
	
	
	
}
