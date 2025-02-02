package travelPackage;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import handler.FlightsHandler;
import packageComponents.Flight;
import packageComponents.Hotel;
import packageComponents.Taxi;

public class TravelPackageManager {
	private static List<TravelPackage> allPackages = new ArrayList();
	
	public TravelPackageManager(){
		allPackages= loadPackages("travel-packages.txt");
	}
	//method used to finalize travel package and transfer all the data from TravelPackageBuilder to the TravelPackage Object
	public static void finalizeTravelPackage(String name) {
		TravelPackage travelPackage = new TravelPackage(name,
				TravelPackageBuilder.getInstance().getSelectedFlight(),
				TravelPackageBuilder.getInstance().getReturnFlight(),
				TravelPackageBuilder.getInstance().getSelectedHotel(),
				TravelPackageBuilder.getInstance().getSelectedTaxi(),
				TravelPackageBuilder.getInstance().getTotalCost(),
				TravelPackageBuilder.getInstance().getDaysofStay(),
				TravelPackageBuilder.getInstance().getSelectedTaxi().getDays(),
				TravelPackageBuilder.getInstance().getSelectedFlight().getDate(),
				TravelPackageBuilder.getInstance().getReturnFlight().getDate(),
				TravelPackageBuilder.getInstance().getSelectedTaxi().getDailyKM());
		
		allPackages.add(travelPackage);
		saveBookings("travel-packages.txt",allPackages);
		
	}
	//saves booking into a travel.package.txt file from the list of all the travel packages
	public static void saveBookings(String filePath, List<TravelPackage> allPackages) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			for (TravelPackage pkg :allPackages ) {
				writer.write(pkg.getPackageName()+","+pkg.getFlight().getFlightID()+","+
						pkg.getReturnFlight().getFlightID()+","+pkg.getHotel().getHotelName()+","+pkg.getHotel().getRoomType()+","+
						pkg.getTaxi().getTaxiType()+","+pkg.getTotalCost()+","+pkg.getDaysOfStay()+","+
						pkg.getTaxiDays()+","+pkg.getDepartureDate()+","+
						pkg.getReturnDate()+","+pkg.getDailyKM());
				writer.newLine();
			}
			writer.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//load travel packages from teh text file travel-package.txt
	public static List<TravelPackage> loadPackages(String filePath){
		List<TravelPackage> pkgs = new ArrayList();
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner lineScan= new Scanner(line).useDelimiter(",");
				if(lineScan.hasNext()) {
					String packageName= lineScan.next();
					if(lineScan.hasNext()) {
						String ID = lineScan.next();
						Flight flight= getFlightbyID(ID) ;
						if(lineScan.hasNext()) {
							String ID1 = lineScan.next();
							Flight returnFlight= getFlightbyID(ID) ;
							if(lineScan.hasNext()) {
								String hotelName = lineScan.next();
								if(lineScan.hasNext()) {
									String roomType=lineScan.next();
									Hotel hotel = new Hotel(hotelName,roomType, flight.getArrivalCity());
									if(lineScan.hasNext()) {
										String taxiType=lineScan.next();
										Taxi taxi = new Taxi(flight.getArrivalCity(),taxiType);
										if(lineScan.hasNext()) {
											String t=lineScan.next();
											float totalCost= Float.parseFloat(t);
											if(lineScan.hasNext()) {
												String l=lineScan.next();
												long daysOfStay= Long.parseLong(l);
												if(lineScan.hasNext()) {
													String i=lineScan.next();
													int taxiDays=Integer.parseInt(i);
													if(lineScan.hasNext()) {
														String departureDate=lineScan.next();
														if(lineScan.hasNext()) {
															String returnDate=lineScan.next();
															if(lineScan.hasNext()) {
																String in=lineScan.next();
																int dailyKM=Integer.parseInt(in);
																
																TravelPackage travelP = new TravelPackage(packageName, flight, returnFlight,hotel,taxi,totalCost, daysOfStay,taxiDays,departureDate,returnDate,dailyKM);
																
																pkgs.add(travelP);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
			return pkgs;
	}
	//gets a Flight Object by filtering all the flight data by the ID.
	//It takes advanateg of the fact that each flight has its unique ID.
	public static Flight getFlightbyID(String ID) {
		List<List<String>> f= FlightsHandler.filterData(ID, 0);
		Flight flight;
		List<String> flightInfo=f.get(0);
		if(flightInfo.get(3).equals("")) {
			flight= new Flight(flightInfo.get(2),flightInfo.get(10),"false",flightInfo.get(1));
		}
		else {
			flight= new Flight(flightInfo.get(2),flightInfo.get(3),"true",flightInfo.get(1));
		}
		return flight;
	}
	public static List<TravelPackage> getAllPackages() {
		return allPackages;
	}
	
}
