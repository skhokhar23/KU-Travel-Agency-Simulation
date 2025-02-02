package travelPackage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import packageComponents.Flight;
import packageComponents.Hotel;
import packageComponents.Taxi;

public class TravelPackageBuilder {
	private static TravelPackageBuilder instance;
    private Flight selectedFlight;
    private Flight returnFlight;
    private Hotel selectedHotel;
    private Taxi selectedTaxi;
    private long daysofStay;
    private float totalCost;

   

	//private Taxi selectedTaxi;
    private TravelPackageBuilder() {}
//a temporray instance to use to build the package during the program runtime
    public static TravelPackageBuilder getInstance() {
        if (instance == null) {
            instance = new TravelPackageBuilder();
        }
        return instance;
    }
//set days of stay by calculatig the number between two selected datds. 
    //it uses the LocalDate and chronoUnit.DAYS to do so
    public void setDaysofStay(Flight selectedFlight, Flight returnFlight) {
    	LocalDate selectedFlightDate= LocalDate.parse(selectedFlight.getDate());
    	LocalDate returnFlightDate= LocalDate.parse(returnFlight.getDate());
    	daysofStay= ChronoUnit.DAYS.between(selectedFlightDate, returnFlightDate);
    	System.out.println(daysofStay+" days in between.");
    }
    
    public long getDaysofStay() {
		return daysofStay;
	}

	public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }
    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public Taxi getSelectedTaxi() {

        return selectedTaxi;

    }
    //reset method for when the instance has to be reset to be used again to build next travel package
    public void reset() {
    	selectedFlight=null;
    	selectedHotel=null;
    	selectedTaxi=null;
    	returnFlight=null;
    	
    }


    public void setSelectedTaxi(Taxi selectedTaxi) {

        this.selectedTaxi = selectedTaxi;

    }
    //total cost by combining all costs
    public float getTotalCost() {
		return (selectedTaxi.getBaseFare()*selectedTaxi.getDays())+(selectedTaxi.getPricePerKM()*selectedTaxi.getDailyKM()*selectedTaxi.getDays())+ 
				selectedFlight.getPrice()+
				(selectedHotel.getPrice()*daysofStay)+ 
				returnFlight.getPrice();
	}

	public void setTotalCost() {
		totalCost=(selectedTaxi.getBaseFare()*selectedTaxi.getDays())+(selectedTaxi.getPricePerKM()*selectedTaxi.getDailyKM()*selectedTaxi.getDays())+ 
				selectedFlight.getPrice()+
				(selectedHotel.getPrice()*daysofStay)+ 
				returnFlight.getPrice();
	}


//to string method for travel package builder
    @Override

    public String toString() {

        return "TravelPackageBuilder{" +

                "selectedFlight=" + selectedFlight.toString() +
                "\n returnFlight=" + returnFlight.toString() +
                "\n selectedHotel=" + selectedHotel.toString() +
                "\n selectedTaxi=" +  selectedTaxi.toString()+

                '}';

    }

}
