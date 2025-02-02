package packageComponents;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class HotelManager extends Manager{
private static Map<String, Map<String,Integer>> AllRoomsBooked = new HashMap();
	
	public HotelManager() {
		AllRoomsBooked = loadBookings("hotel-booking.txt");
	}
	
	public void saveAllBookings() {
		saveBookings("hotel-booking.txt", AllRoomsBooked);
	}
	
	public static Map<String, Map<String, Integer>> getAllBooked() {
		return AllRoomsBooked;
	}
	
	
	//saves all booking from the nested map into a text file hotel-booking.txt
	public static void saveBookings(String filePath, Map<String, Map<String,Integer>> AllRoomsBooked) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			for (String hotelID:  AllRoomsBooked.keySet()) {
				Map<String,Integer> individualBookings= AllRoomsBooked.get(hotelID);
				for(String date:individualBookings.keySet()) {
					writer.write(hotelID+","+date+","+individualBookings.get(date));
					writer.newLine();
				}
			}
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//load bookings of all hotels from hotel-booking.txt
		//It returns a map with string hotelname+city+roomType(as a unique identifier)
	//and value of map which contains all the number of hotels booked on different dates
		
	public static Map<String, Map<String,Integer>> loadBookings(String filePath){
		Map<String, Map<String,Integer>>  bookings = new HashMap();
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner lineScan= new Scanner(line).useDelimiter(",");
				if(lineScan.hasNext()) {
					String hotelID= lineScan.next();
					if(lineScan.hasNext()) {
						String date= lineScan.next();
						if(lineScan.hasNext()) {
							int numberBooked= Integer.parseInt(lineScan.next());
							Map<String,Integer> dateToSeats= new HashMap();
							dateToSeats.put(date, numberBooked);
							bookings.put(hotelID, dateToSeats);
						}
					}
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			return bookings;
	}
}
