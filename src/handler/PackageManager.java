package handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import packageComponents.Flight;

public class PackageManager {
	static String[] airlines;
	static String[] departureCity;
	static String[] allArrivalCities;
	static List<List<String>> packageComponents= new ArrayList();
	
	//Constructor for PackageManager
	public PackageManager() {
		this.airlines=FlightsHandler.getPart(1);
		this.departureCity=FlightsHandler.getPart(2);
		this.allArrivalCities=FlightsHandler.getPart(3);
	}
	
	public static List<List<String>> filterData(String name, int j){
		return FlightsHandler.filterData(name, j);
	}
	//get String array for all airlines from a SPECIFIED list of lists. (filtered)
	public static String[] getAirlines(List<List<String>> data) {
		return FlightsHandler.getPart(1,data);
	}
	//get all arrival cities from a specified data(filtered).
	public static String[] getAllArrivalCities(List<List<String>> data) {
		String[] cities= FlightsHandler.getPart(3,data);
		List<String> list = new ArrayList<String>(Arrays.asList(cities));
		for(int i=0;i<list.size();i++) {
			if(list.get(i)=="") {
				list.remove(i);
			}
		}
		cities = list.toArray(new String[0]);
		return cities;
	}
	
	//The following method takes the chosen arrival and departure city as parameters and boolean (if it is a direct flight or not).
	//The method returns all airlines which offer flights specifying this criteria in the form of a String[] array.
	public static String[] getAirlines(String dCity, String aCity, boolean direct) {
		//using filterData methods to get lists of only those flights which meet the criteria.
		List<List<String>> dCityAirlines = filterData(dCity,2); //all flights from the specified departure city
		List<List<String>> directaAirlines=FlightsHandler.filterData(aCity,3,dCityAirlines);//direct flight to the arrival city
		List<List<String>> stopoveraAirlines=FlightsHandler.filterData(aCity,10,dCityAirlines); //stopover flight to arrival city
		List<String> allAirlines=new ArrayList();//list which will contain the airlines that meet the criteria
		
		if(direct) {//if direct==true, the if statement executes. It adds airlines from the directaAirlines list.
			for(int i=0;i<directaAirlines.size();i++) {
				if(!allAirlines.contains(directaAirlines.get(i).get(1))) {
					allAirlines.add(directaAirlines.get(i).get(1));
				}
			}
		}else {//if direct==false, the else statement executes. It adds airlines from the stopoveraAirlines list.
			for(int i=0;i<stopoveraAirlines.size();i++) {
				if(!allAirlines.contains(stopoveraAirlines.get(i).get(1))) {
					allAirlines.add(stopoveraAirlines.get(i).get(1));
				}
			}
		}
		return allAirlines.toArray(new String[0]);//allAirlines is converted to String[] array to match the return type.
		
	}
	
	
	//getters
	public static String[] getAirlines() {
		return airlines;
	}
	//get departure city
	public static String[] getDepartureCity() {
		List<String> list = new ArrayList<String>(Arrays.asList(departureCity));
		list.remove(list.get(0));
		departureCity = list.toArray(new String[0]);
		return departureCity;
	}

	public static String[] getAllArrivalCities() {
		return allArrivalCities;
	}
	
	
	
	
}
