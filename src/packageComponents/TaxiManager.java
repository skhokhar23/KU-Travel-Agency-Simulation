package packageComponents;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaxiManager extends Manager {
private static Map<String, Map<String,Integer>> AllTaxiBooked = new HashMap();
	
	public TaxiManager() {
		AllTaxiBooked = loadBookings("taxi-booking.txt");
	}
	
	public void saveAllBookings() {
		saveBookings("taxi-booking.txt", AllTaxiBooked);
	}
	
	public static Map<String, Map<String, Integer>> getAllBooked() {
		return AllTaxiBooked;
	}
	
	
	//saves all booking from the nested map into a text file taxi-booking.txt
	public static void saveBookings(String filePath, Map<String, Map<String,Integer>> AllTaxiBooked) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			for (String taxiID:  AllTaxiBooked.keySet()) {
				Map<String,Integer> individualBookings= AllTaxiBooked.get(taxiID);
				for(String date:individualBookings.keySet()) {
					writer.write(taxiID+","+date+","+individualBookings.get(date));
					writer.newLine();
				}
			}
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//load bookings of all taxis from taxi-booking.txt
		//It returns a map with string taxitype+city (as a unique identifer used) and value of map which contains all the number of taxis on different dates
	public static Map<String, Map<String,Integer>> loadBookings(String filePath){
		Map<String, Map<String,Integer>>  bookings = new HashMap();
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner lineScan= new Scanner(line).useDelimiter(",");
				if(lineScan.hasNext()) {
					String taxiID= lineScan.next();
					if(lineScan.hasNext()) {
						String date= lineScan.next();
						if(lineScan.hasNext()) {
							int numberBooked= Integer.parseInt(lineScan.next());
							Map<String,Integer> dateToSeats= new HashMap();
							dateToSeats.put(date, numberBooked);
							bookings.put(taxiID, dateToSeats);
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
