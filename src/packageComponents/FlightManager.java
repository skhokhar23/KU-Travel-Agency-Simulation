package packageComponents;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FlightManager extends Manager{
	private static Map<String, Map<String,Integer>> AllSeatsBooked = new HashMap();
	//constructor
	public FlightManager() {
		AllSeatsBooked = loadBookings("flight-booking.txt");
	}
	//save bookings into specofed fke by default
	public void saveAllBookings() {
		saveBookings("flight-booking.txt", AllSeatsBooked);
	}
	
	public static Map<String, Map<String, Integer>> getAllBooked() {
		return AllSeatsBooked;
	}
	//saves all booking from the nested map into a text file flight-booking.txt
	public static void saveBookings(String filePath, Map<String, Map<String,Integer>> AllSeatsBooked) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			for (String flightID:  AllSeatsBooked.keySet()) {
				Map<String,Integer> individualBookings= AllSeatsBooked.get(flightID);
				for(String date:individualBookings.keySet()) {
					writer.write(flightID+","+date+","+individualBookings.get(date));
					writer.newLine();
				}
			}
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//load bookings of all flights from flight-booking.txt
	//It returns a map with string flight id and value of map which contains all the number of flights on different dates
	public static Map<String, Map<String,Integer>> loadBookings(String filePath){
		Map<String, Map<String,Integer>>  bookings = new HashMap();
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner lineScan= new Scanner(line).useDelimiter(",");
				if(lineScan.hasNext()) {
					String flightID= lineScan.next();
					if(lineScan.hasNext()) {
						String date= lineScan.next();
						if(lineScan.hasNext()) {
							int numberBooked= Integer.parseInt(lineScan.next());
							Map<String,Integer> dateToSeats= new HashMap();
							dateToSeats.put(date, numberBooked);
							bookings.put(flightID, dateToSeats);
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
