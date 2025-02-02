package packageComponents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import handler.TaxiHandler;

public class Taxi {
	String city, taxiType;
	private int totalTaxis,days;
	private float baseFare, pricePerKM, totalCost;
	private Map<String, Integer> taxiBooked= new HashMap();
	private int dailyKM;
	private String startDate, endDate;
	
	public Taxi(String city, String taxiType) {
		this.city=city;
		this.taxiType=taxiType;
		baseFare=Float.parseFloat(TaxiHandler.getIndexInfo(this.city, this.taxiType, 3));
		pricePerKM=Float.parseFloat(TaxiHandler.getIndexInfo(this.city, this.taxiType, 4));
		totalTaxis= Integer.parseInt(TaxiHandler.getIndexInfo(this.city, this.taxiType, 2));
		TaxiManager.getAllBooked().putIfAbsent(city+taxiType, taxiBooked);
	}
	//Book Taxi method to just book for one day.
	public boolean bookTaxi(String selectedDate) {
		int bookedTaxis;
		if(TaxiManager.getAllBooked().get(city+taxiType).get(selectedDate)==null) {
			TaxiManager.getAllBooked().get(city+taxiType).put(selectedDate, 0);
			bookedTaxis=0;
		}
		else {
			bookedTaxis=TaxiManager.getAllBooked().get(city+taxiType).get(selectedDate);
		}
		if(bookedTaxis<totalTaxis) {
			TaxiManager.getAllBooked().get(city+taxiType).put(selectedDate, bookedTaxis+1);
			TaxiManager.saveBookings("taxi-booking.txt", TaxiManager.getAllBooked());
			return true;
		}
		else {
			System.out.println("The flight is full. No seats available.");
			return false;
		}
	}
	//bookTaxi method to book taxi for all the dates between checkinDtae and checkOutDate
	//It uses the bookTaxi(String date) method to book
	public void bookTaxi(String checkinDate, String checkOutDate) {
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate=LocalDate.parse(checkinDate,dtf);
		LocalDate endDate=LocalDate.parse(checkOutDate,dtf);
		LocalDate c= startDate;
		while(!c.isAfter(endDate)) {
			String s=c.format(dtf);
			bookTaxi(s);
			c=c.plusDays(1);
		}
	}
	//avaialble seats on a specific date. scans the map for the date and gets value.
	public int getTaxiAvailable(String selectedDate) {
		if(taxiBooked.get(selectedDate)==null) {
			return totalTaxis;
		}
		else {
			return totalTaxis-taxiBooked.get(selectedDate);
		}
	}
	@Override
	public String toString() {
		return "City: "+city+"\nTaxi Type: "+taxiType+"\nBase Fare: "+baseFare;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	//getters and setters
	public int getDailyKM() {
		return dailyKM;
	}

	public void setDailyKM(int dailyKM) {
		this.dailyKM = dailyKM;
	}

	public String getCity() {
		return city;
	}

	public String getTaxiType() {
		return taxiType;
	}

	public int getTotalTaxis() {
		return totalTaxis;
	}

	public void setDays(int days) {
		this.days=days;
	}
	public int getDays() {
		return days;
	}

	public float getBaseFare() {
		return baseFare;
	}

	public float getPricePerKM() {
		return pricePerKM;
	}

	public Map<String, Integer> getTaxiBooked() {
		return taxiBooked;
	}
	
}
