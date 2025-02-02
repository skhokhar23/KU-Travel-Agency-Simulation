package travelPackage;

import packageComponents.Flight;
import packageComponents.Hotel;
import packageComponents.Taxi;

public class TravelPackage {
	private String packageName, departureDate,returnDate;
	private Flight flight,returnFlight;
	private Hotel hotel;
	private Taxi taxi;
	private float totalCost;
	private long daysOfStay;
	private int taxiDays, dailyKM;
	//constructor
	public TravelPackage(String packageName,Flight flight, Flight returnFlight, 
			Hotel hotel, Taxi taxi, float totalCost, long daysOfStay,
			int taxiDays, String departureDate, String returnDate, int dailyKM) {
		
		this.daysOfStay=daysOfStay;
		this.packageName=packageName;
		this.flight=flight;
		this.returnFlight=returnFlight;
		this.taxi=taxi;
		this.totalCost=totalCost;
		this.hotel=hotel;
		this.daysOfStay=daysOfStay;
		this.taxiDays=taxiDays;
		this.departureDate=departureDate;
		this.returnDate=returnDate;
		this.dailyKM= dailyKM;
	}
//to string the travel package
	@Override
	public String toString() {
		return "\n"+packageName+": \nDeparture City "+flight.getDepartureCity()+"\nArrival City: "+flight.getArrivalCity()+
				"\nDeparture: "+departureDate+" with "+flight.getAirline()+"\nArrival: "+returnDate+" with "+returnFlight.getAirline()+
				"\nHotel: "+hotel.getHotelName()+"\nDuration of Stay: "+daysOfStay+
				" nights \nTaxi Type: "+taxi.getTaxiType()+" \nUse: "+dailyKM+" km daily for "+taxiDays+" days"+
				"\nTotal Cost: $"+totalCost;
	}

	//getters and setters
	public String getPackageName() {
		return packageName;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public Flight getFlight() {
		return flight;
	}

	public Flight getReturnFlight() {
		return returnFlight;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public long getDaysOfStay() {
		return daysOfStay;
	}

	public int getTaxiDays() {
		return taxiDays;
	}

	public int getDailyKM() {
		return dailyKM;
	}

	
	
	
}
