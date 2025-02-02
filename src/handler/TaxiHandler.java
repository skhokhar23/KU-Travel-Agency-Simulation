package handler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaxiHandler {
	//returns all hotel info in a nested list of lists by reading the Hotels csv file.
			public static List<List<String>> AllTaxisInfo() {
				List<List<String>> listOfLists = new ArrayList();
				try {
					Scanner scanner = new Scanner(new FileReader("FinalKU_Travel_Agency_Dataset_Taxis.csv"));
					while(scanner.hasNextLine()) {
						String line = scanner.nextLine();
						List<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
						listOfLists.add(myList);
					}
					}catch(IOException e) {
						e.printStackTrace();
					}
				listOfLists.remove(0);
				return listOfLists;
			}

			//A method to get all the different options in a file(without repeat) from all the data.
			//For example, only getting a String[] array of all the hotels.
			public static String[] getPart(int k){
				List<String> options =new ArrayList();
				String[] options1;
				List<List<String>> listOfLists=AllTaxisInfo();
				for(int i=0;i<listOfLists.size();i++) {
					if(!options.contains(listOfLists.get(i).get(k))) {
						options.add(listOfLists.get(i).get(k));
					}
					
				}
				options1= options.toArray(new String[0]);
				return options1;
			}
			//returns the String array of all options from an already filtered list.
			//For instance, only get taxis that are present in a specific city.
			//int j is the index of the String name, int k is the index of the part that yoy want to get
			public static String[] getFilteredPart(String name, int j,int k){
				List<List<String>> listOfLists = filterData(name,j);
				List<String> options =new ArrayList();
				String[] options1;
				for(int i=0;i<listOfLists.size();i++) {
					if(!options.contains(listOfLists.get(i).get(k))) {
						options.add(listOfLists.get(i).get(k));
					}
				}
				options1= options.toArray(new String[0]);
				return options1;
			}
			
			//A method for filtering data. It results only those taxis which have a certain 'String name' at int j index.
			public static List<List<String>> filterData(String name, int j) {
				List<List<String>> listOfLists = AllTaxisInfo();
				List<List<String>> filteredList = new ArrayList();
				for(int i=0;i<listOfLists.size();i++) {
					if(listOfLists.get(i).get(j).equals(name)) {
						filteredList.add(listOfLists.get(i));
					}
				}
				return filteredList;
			}
			//A method to get the index info for a specific taxi.
			//For instance, the base fare of a yellow taxi in Istanbul.
			public static String getIndexInfo(String city, String taxiType, int index) {
				List<List<String>> listOfLists = AllTaxisInfo();
				String indexInfo="";
				for(int i=0;i<listOfLists.size();i++) {
					if(listOfLists.get(i).get(0).equals(city)&& listOfLists.get(i).get(1).equals(taxiType) ){
						indexInfo=listOfLists.get(i).get(index);
					}
				}
				return indexInfo;
			}

}
