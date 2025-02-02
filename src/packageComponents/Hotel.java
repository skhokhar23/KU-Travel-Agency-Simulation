package packageComponents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import handler.HotelsHandler;

public class Hotel {
	private String hotelName, roomType, city,Date;
	private int totalRooms;
	private float price,distanceToAirport;
	private Map<String, Integer> roomsBooked= new HashMap();
	
	public Hotel(String hotelName, String roomType, String city) {
		this.hotelName=hotelName;
		this.roomType=roomType;
		this.city=city;
		distanceToAirport= Float.parseFloat(HotelsHandler.getIndexInfo(hotelName, roomType, city, 5));
		price= Float.parseFloat(HotelsHandler.getIndexInfo(hotelName, roomType, city, 4));
		totalRooms= Integer.parseInt(HotelsHandler.getIndexInfo(hotelName, roomType, city, 3));
		HotelManager.getAllBooked().putIfAbsent(hotelName+city+roomType, roomsBooked);
	}
	public boolean bookRoom(String selectedDate) {
		int bookedRooms;
		if(HotelManager.getAllBooked().get(hotelName+city+roomType).get(selectedDate)==null) {
			HotelManager.getAllBooked().get(hotelName+city+roomType).put(selectedDate, 0);
			bookedRooms=0;
		}
		else {
			bookedRooms=HotelManager.getAllBooked().get(hotelName+city+roomType).get(selectedDate);
		}
		if(bookedRooms<totalRooms) {
			HotelManager.getAllBooked().get(hotelName+city+roomType).put(selectedDate, bookedRooms+1);
			HotelManager.saveBookings("hotel-booking.txt", HotelManager.getAllBooked());
			return true;
		}
		else {
			System.out.println("The flight is full. No seats available.");
			return false;
		}
	}
	public void bookRoom(String checkinDate, String checkOutDate) {
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate=LocalDate.parse(checkinDate,dtf);
		LocalDate endDate=LocalDate.parse(checkOutDate,dtf);
		LocalDate c= startDate;
		while(!c.isAfter(endDate)) {
			String s=c.format(dtf);
			bookRoom(s);
			c=c.plusDays(1);
		}
	}
	public String toString() {
		return "Hotel: "+hotelName+"\nCity: "+city+"\nRoom Type: "+roomType+"\nPrice: $"+price+
				"\nDistance to Airport: "+distanceToAirport+"\nCheck-in Date: "+Date;
	}
	//avaialble seats on a specific date. scans the map for the date and gets value.
	public int getSeatsAvailable(String selectedDate) {
		if(roomsBooked.get(selectedDate)==null) {
			return totalRooms;
		}
		else {
			return totalRooms-roomsBooked.get(selectedDate);
		}
	}
	public void setDate(String date) {
		this.Date = date;
	}
	//getters and setters
	public String getDate() {
		return Date;
	}
	public String getHotelName() {
		return hotelName;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getCity() {
		return city;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

	public float getPrice() {
		return price;
	}

	public float getDistanceToAirport() {
		return distanceToAirport;
	}
	
}
