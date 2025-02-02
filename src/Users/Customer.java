package Users;

public class Customer extends User{
	private int reservationsMade,flightsBooked, hotelsBooked,taxisBooked;
	private float totalMoneySpent, flightMoney,hotelMoney,taxiMoney;
//customer extends User
	public Customer(String username, String password) {
		super(username, password);
		this.flightsBooked=0;
		this.hotelsBooked=0;
		this.reservationsMade=0;
		this.taxisBooked=0;
		this.totalMoneySpent=0;
		this.taxiMoney=0;
		this.hotelMoney=0;
		this.flightMoney=0;
	}
	public void addMoneySpent(float money) {
		this.totalMoneySpent+=money;
	}
	public void addFlightMoney(float money) {
		this.flightMoney+=money;
	}
	public void addTaxiMoney(float money) {
		this.taxiMoney+=money;
	}
	public void addHotelMoney(float money) {
		this.hotelMoney+=money;
	}
	@Override
	public String toString() {
		return "Username: "+super.getUsername()+"\nPassword: "+super.getPassword();
				
	}
	
	
	
	//getters and setters
	public int getReservationsMade() {
		return reservationsMade;
	}
	public int getFlightsBooked() {
		return flightsBooked;
	}
	public int getHotelsBooked() {
		return hotelsBooked;
	}
	public int getTaxisBooked() {
		return taxisBooked;
	}
	public float getTotalMoneySpent() {
		return totalMoneySpent;
	}
	public float getFlightMoney() {
		return flightMoney;
	}
	public float getHotelMoney() {
		return hotelMoney;
	}
	public float getTaxiMoney() {
		return taxiMoney;
	}

	
}
