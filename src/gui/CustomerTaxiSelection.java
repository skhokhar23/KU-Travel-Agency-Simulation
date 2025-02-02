package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import travelPackage.TravelPackageBuilder;
import travelPackage.TravelPackageManager;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import handler.HotelsHandler;
import handler.TaxiHandler;
import packageComponents.Taxi;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Users.UserManager;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomerTaxiSelection {

	private JFrame frame;
	//private String city="Istanbul";
	private String city = TravelPackageBuilder.getInstance().getReturnFlight().getDepartureCity();
	private int dailyKM, numberOfDays;
	private String taxiType;
	private Taxi taxi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerTaxiSelection window = new CustomerTaxiSelection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerTaxiSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 240));
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JComboBox taxiTypesCombo = new JComboBox(TaxiHandler.getFilteredPart(city, 0, 1));
		taxiTypesCombo.setBackground(new Color(240, 128, 128));
		taxiTypesCombo.setBounds(198, 123, 110, 22);
		frame.getContentPane().add(taxiTypesCombo);
		
		JLabel lblNewLabel = new JLabel("Taxi Type");
		lblNewLabel.setForeground(new Color(240, 128, 128));
		lblNewLabel.setBounds(223, 98, 57, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Taxi Selection Page");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(185, 47, 170, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Number of Days of Use:");
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(110, 178, 146, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox numberOfDaysCombo = new JComboBox();
		for(int i=1;i<TravelPackageBuilder.getInstance().getDaysofStay()+1;i++) {
			numberOfDaysCombo.addItem(i);
		}
		numberOfDaysCombo.setBackground(new Color(240, 128, 128));
		numberOfDaysCombo.setBounds(132, 203, 84, 22);
		frame.getContentPane().add(numberOfDaysCombo);
		
		JLabel lblNewLabel_3 = new JLabel("Daily kilometrage: ");
		lblNewLabel_3.setForeground(new Color(240, 128, 128));
		lblNewLabel_3.setBounds(282, 178, 127, 14);
		frame.getContentPane().add(lblNewLabel_3);
		JLabel lblNewLabel_4 = new JLabel("km");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(361, 207, 48, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton confirm = new JButton("Confirm Selection");
		confirm.setBounds(185, 251, 138, 23);
		
		frame.getContentPane().add(confirm);
		numberOfDaysCombo.setEnabled(false);
		String[] km= {"10","20","30","40","50","60","70","80","90","100"};
		JComboBox kmBox = new JComboBox(km) ;
		kmBox.setBackground(new Color(240, 128, 128));
		kmBox.setBounds(282, 203, 73, 22);
		frame.getContentPane().add(kmBox);
		kmBox.setEnabled(false);
		confirm.setEnabled(false);
		
		
		kmBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dailyKM= (kmBox.getSelectedIndex()+1)*10;
				confirm.setEnabled(true);
			}
		});
		numberOfDaysCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberOfDays= numberOfDaysCombo.getSelectedIndex()+1;
				kmBox.setEnabled(true);
			}
		});
		taxiTypesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxiType= (String) taxiTypesCombo.getSelectedItem();
				numberOfDaysCombo.setEnabled(true);
			}
		});
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Taxi taxi = new Taxi(city, taxiType);
				taxi.setDailyKM(dailyKM);
				taxi.setDays(numberOfDays);
				DateTimeFormatter dtf =DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDate=LocalDate.parse(TravelPackageBuilder.getInstance().getSelectedFlight().getDate(),dtf);
				LocalDate endDate=startDate.plusDays(numberOfDays);
				String s=endDate.format(dtf);
				taxi.setStartDate(TravelPackageBuilder.getInstance().getSelectedFlight().getDate());
				taxi.setEndDate(s);
				//taxi.bookTaxi(TravelPackageBuilder.getInstance().getSelectedFlight().getDate(), s);
				int choice = JOptionPane.showOptionDialog(frame,"These are your taxi selection details:\nCity: "+city+"\nTaxi Type: "+taxiType+"\nNumber of days of use: "+ numberOfDays+
						"\nAverage Daily Kilometrage: "+dailyKM+"km\nTaxi Base Fare: $"+taxi.getBaseFare()+"\nPrice per KM: $"+taxi.getPricePerKM()+
						"\nTotal Estimated Cost: $"+((taxi.getBaseFare()*numberOfDays)+(taxi.getPricePerKM()*numberOfDays*taxi.getPricePerKM())),
                        "Flight Class Selection",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,
                        null, new Object[]{"Yes", "No"},  "Option 1"); 
				if (choice == JOptionPane.YES_OPTION) {
					TravelPackageBuilder.getInstance().setSelectedTaxi(taxi);
					int choice2 = JOptionPane.showOptionDialog(frame,"Package Building Completed! Would you like to proceed with the payment?\n"
							+ " Following are the package details: \nDeparture City: "+
							TravelPackageBuilder.getInstance().getSelectedFlight().getDepartureCity()+
							"\nArrival City: "+TravelPackageBuilder.getInstance().getSelectedFlight().getArrivalCity()+
							"\nDuration of Stay: "+ TravelPackageBuilder.getInstance().getDaysofStay()+
							" days\nHotel of Stay: "+ TravelPackageBuilder.getInstance().getSelectedHotel().getHotelName()+
							"\nRoom Type: "+ TravelPackageBuilder.getInstance().getSelectedHotel().getRoomType()+
							"\nTaxi Type: "+taxiType+
							"\nTotal Estimated Cost: $"+TravelPackageBuilder.getInstance().getTotalCost()+
							"\nHotel Cost: $"+(TravelPackageBuilder.getInstance().getSelectedHotel().getPrice()*TravelPackageBuilder.getInstance().getDaysofStay())+
							"\nTaxi Cost: $"+"\nTotal Estimated Cost: $"+(TravelPackageBuilder.getInstance().getSelectedTaxi().getDailyKM()*TravelPackageBuilder.getInstance().getSelectedTaxi().getDays()*TravelPackageBuilder.getInstance().getSelectedTaxi().getPricePerKM())+
							"\nFlight Price Breakdown: $"+(TravelPackageBuilder.getInstance().getReturnFlight().getPrice()+TravelPackageBuilder.getInstance().getSelectedFlight().getPrice()),
	                        "Travel Package Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,
	                        null, new Object[]{"Yes", "No"},  "Option 1"); 
					if (choice == JOptionPane.YES_OPTION) {
						String pname=JOptionPane.showInputDialog(null,"Enter the Package Name: ","User Input", JOptionPane.PLAIN_MESSAGE);
						if(pname!=null) {
							TravelPackageBuilder.getInstance().getSelectedHotel().bookRoom(TravelPackageBuilder.getInstance().getSelectedFlight().getDate(), TravelPackageBuilder.getInstance().getReturnFlight().getDate());
							TravelPackageBuilder.getInstance().getSelectedFlight().bookSeat(TravelPackageBuilder.getInstance().getSelectedFlight().getDate());
							TravelPackageBuilder.getInstance().getReturnFlight().bookSeat(TravelPackageBuilder.getInstance().getReturnFlight().getDate());
							TravelPackageBuilder.getInstance().getSelectedTaxi().bookTaxi(TravelPackageBuilder.getInstance().getSelectedTaxi().getStartDate(), TravelPackageBuilder.getInstance().getSelectedTaxi().getEndDate());
							UserManager.getActiveUser().addFlight(TravelPackageBuilder.getInstance().getSelectedFlight());
							UserManager.getActiveUser().addFlight(TravelPackageBuilder.getInstance().getReturnFlight());
							UserManager.getActiveUser().addHotel(TravelPackageBuilder.getInstance().getSelectedHotel());
							UserManager.getActiveUser().addTaxi(TravelPackageBuilder.getInstance().getSelectedTaxi());
							UserManager.getActiveUser().addMoneySpent(TravelPackageBuilder.getInstance().getTotalCost());
							UserManager.getActiveUser().incrementFlight();
							UserManager.getActiveUser().incrementHotel();
							UserManager.getActiveUser().incrementReservations();
							UserManager.getActiveUser().incrementTaxi();
							System.out.println(UserManager.getActiveUser().toString());
							UserManager.updateUser(UserManager.getActiveUser());
							UserManager.saveUsersToTextFile("user-stats.txt");
							TravelPackageBuilder.getInstance().reset();
							CustomerMainMenu cmm= new CustomerMainMenu();
							cmm.getFrame().setVisible(true);
							frame.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "The package name was not entered!");
						}
					}
                } else if (choice == JOptionPane.NO_OPTION) {
                	TaxiSelectionPage h = new TaxiSelectionPage();
                	h.getFrame().setVisible(true);
                	frame.dispose();
                }
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
