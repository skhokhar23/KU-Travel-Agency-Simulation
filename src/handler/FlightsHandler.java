package handler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightsHandler {
	
	//returns all flight info in a nested list of lists by reading the Flights csv file.
	public static List<List<String>> AllFlightsInfo() {
		List<List<String>> listOfLists = new ArrayList();
		try {
			Scanner scanner = new Scanner(new FileReader("FinalKU_Travel_Agency_Dataset_Flights.csv"));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				List<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
				while (myList.size() < 15) {
                    myList.add(""); 
                }
				listOfLists.add(myList);
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		return listOfLists;
	}
	
	//A method to get all the different options in a file(without repeat) from all the data.
	//For example, only getting a String[] array of all the airlines.
	public static String[] getPart(int k){
		List<String> options =new ArrayList();
		String[] options1;
		List<List<String>> listOfLists=AllFlightsInfo();
		for(int i=0;i<listOfLists.size();i++) {
			if(!options.contains(listOfLists.get(i).get(k))) {
				options.add(listOfLists.get(i).get(k));
			}
			
		}
		options1= options.toArray(new String[0]);
		return options1;
	}
	
	//Method overloading for previous getPart(int k)
	//This method searches for part in a specified data list of lists and not the list of lits from the original data.
	public static String[] getPart(int k, List<List<String>> data){
		List<String> options =new ArrayList();
		String[] options1;
		for(int i=0;i<data.size();i++) {
			if(!options.contains(data.get(i).get(k))) {
				options.add(data.get(i).get(k));
			}
			
		}
		options1= options.toArray(new String[0]);
		return options1;
	}
	
	//A method for filtering data. It results only those flights which have a certain 'String name' at int j index.
	public static List<List<String>> filterData(String name, int j) {
		List<List<String>> listOfLists = AllFlightsInfo();
		List<List<String>> filteredList = new ArrayList();
		for(int i=0;i<listOfLists.size();i++) {
			if(listOfLists.get(i).get(j).equals(name)) {
				filteredList.add(listOfLists.get(i));
			}
		}
		return filteredList;
	}
	
	//this method for filtering data returns filtered data from a list passed as a parameter
	public static List<List<String>> filterData(String name, int j,List<List<String>> data){
		List<List<String>> filteredList = new ArrayList();
		for(int i=0;i<data.size();i++) {
			if(data.get(i).get(j).equals(name)) {
				filteredList.add(data.get(i));
			}
		}
		return filteredList;
	}
	//this method takes the paramters passed in flight object constructor to search that flight.
	//It returns the String at the index passed as a paramter
	public static String getIndexInfo(String departureCity, String arrivalCity, boolean isDirect, String airline,int index) {
		List<List<String>> listOfLists = AllFlightsInfo();
		String indexInfo="";
		for(int i=0;i<listOfLists.size();i++) {
			if(listOfLists.get(i).get(1).equals(airline) && listOfLists.get(i).get(2).equals(departureCity)){
				if(isDirect) {
					if(listOfLists.get(i).get(3).equals(arrivalCity)) {
						indexInfo=listOfLists.get(i).get(index);
				}
				}
				else if(listOfLists.get(i).get(10).equals(arrivalCity)) {
					indexInfo=listOfLists.get(i).get(index);
				}
			}
		}
		return indexInfo;
	}
	//this method is the same as the previous, except that it does not require airline as a parameter
	public static String getIndexInfo(String departureCity, String arrivalCity, boolean isDirect,int index) {
		List<List<String>> listOfLists = AllFlightsInfo();
		String indexInfo="";
		for(int i=0;i<listOfLists.size();i++) {
			if(listOfLists.get(i).get(2).equals(departureCity)){
				if(isDirect) {
					if(listOfLists.get(i).get(3).equals(arrivalCity)) {
						indexInfo=listOfLists.get(i).get(index);
				}
				}
				else if(listOfLists.get(i).get(10).equals(arrivalCity)) {
					indexInfo=listOfLists.get(i).get(index);
				}
			}
		}
		return indexInfo;
	}
	
	
	
}
