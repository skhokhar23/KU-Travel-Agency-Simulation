package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import handler.FlightsHandler;
import handler.PackageManager;
import packageComponents.Flight;
import travelPackage.TravelPackageBuilder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class AdminTravelPackage {

	
	private JFrame frame;
	private List<List<String>> data= new ArrayList();
	private String selectedCity,arrivalCity, airline,selectedDate;
	private List<String> selectedItems= new ArrayList();
	private int selectedDay,selectedMonth,selectedYear;
	private Flight flight, returnFlight;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTravelPackage window = new AdminTravelPackage();
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
	public AdminTravelPackage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PackageManager p= new PackageManager();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 240));
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        
		
		JLabel lblNewLabel = new JLabel("Departure City");
		lblNewLabel.setForeground(new Color(240, 128, 128));
		lblNewLabel.setBounds(91, 62, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel flight_info = new JLabel("");
		flight_info.setForeground(new Color(240, 128, 128));flight_info.setBounds(165, 36, 252, 20);
		frame.getContentPane().add(flight_info);
		JLabel flight_info1 = new JLabel("");
		flight_info1.setForeground(new Color(0, 0, 0));flight_info1.setBounds(165, 76, 252, 20);
		frame.getContentPane().add(flight_info1);
		JLabel flight_info2 = new JLabel("");flight_info2.setBounds(165, 96, 252, 20);
		frame.getContentPane().add(flight_info2);
		JLabel flight_info3 = new JLabel("");flight_info3.setBounds(165, 116, 252, 20);
		frame.getContentPane().add(flight_info3);
		JLabel flight_info4 = new JLabel("");flight_info4.setBounds(165, 136, 252, 20);
		frame.getContentPane().add(flight_info4);
		JLabel flight_info5 = new JLabel("");flight_info5.setBounds(165, 156, 252, 20);
		frame.getContentPane().add(flight_info5);
		JLabel flight_info6 = new JLabel("");flight_info6.setBounds(165, 176, 252, 20);
		frame.getContentPane().add(flight_info6);
		JLabel flight_info7 = new JLabel("");flight_info7.setBounds(165, 196, 252, 20);
		frame.getContentPane().add(flight_info7);
		JLabel flight_info8 = new JLabel("");flight_info8.setBounds(165, 216, 252, 20);
		frame.getContentPane().add(flight_info8);
		JLabel flight_info9 = new JLabel("");flight_info9.setBounds(165, 236, 252, 20);
		frame.getContentPane().add(flight_info9);
		JLabel flight_info10 = new JLabel("");flight_info10.setBounds(165, 256, 252, 20);
		frame.getContentPane().add(flight_info10);
		JLabel flight_info11 = new JLabel("");flight_info11.setBounds(165, 276, 252, 20);
		frame.getContentPane().add(flight_info11);
		
		JButton end = new JButton("Confirm Selection");
		end.setBackground(new Color(240, 128, 128));
		end.setForeground(new Color(245, 245, 245));
		end.setBounds(165, 296, 144, 20);
		frame.getContentPane().add(end);
		end.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Arrival City");
		lblNewLabel_1.setForeground(new Color(240, 128, 128));
		lblNewLabel_1.setBounds(318, 62, 106, 14);
		frame.getContentPane().add(lblNewLabel_1);
		JCheckBox chckbxNewCheckBox = new JCheckBox("Only direct flights");
		JCheckBox checkbox2 = new JCheckBox("Stopover Flights");
		JComboBox comboBox1 = new JComboBox<>(PackageManager.getDepartureCity());
        comboBox1.setForeground(new Color(255, 250, 240));
        comboBox1.setBackground(new Color(205, 92, 92));
        comboBox1.setBounds(74, 84, 106, 22);
        JComboBox comboBox2=new JComboBox<>();
		comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedCity = (String) comboBox1.getSelectedItem();
                selectedItems.add(selectedCity);
                List<List<String>> data = PackageManager.filterData(selectedCity, 2);
                for(int i=0;i<PackageManager.getAllArrivalCities(data).length;i++) {
                	String[] s =PackageManager.getAllArrivalCities(data);
                	comboBox2.addItem(s[i]);
                }
                
                comboBox1.setEnabled(false);
                comboBox2.setEnabled(true);
                chckbxNewCheckBox.setVisible(false);
                checkbox2.setVisible(false);
            }
        });
		
		
		JComboBox comboBox3 = new JComboBox<>(); 
        comboBox3.setVisible(false);
        
        JLabel lblNewLabel_2 = new JLabel("Airlines");
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(207, 121, 48, 14);
		lblNewLabel_2.setVisible(false);
		frame.getContentPane().add(lblNewLabel_2);
        
		JButton confirmButton2 = new JButton("Confirm Date");
		confirmButton2.setBackground(new Color(240, 128, 128));
		confirmButton2.setBounds(175, 295, 117, 23);
		frame.getContentPane().add(confirmButton2);
        
		//checking box 
		//when checked, it modifies airlines drop down (only airlines offering direct flghts)
		
		chckbxNewCheckBox.setVisible(false);
		chckbxNewCheckBox.setForeground(new Color(245, 245, 245));
		chckbxNewCheckBox.setBackground(new Color(47, 79, 79));
		chckbxNewCheckBox.setBounds(167, 120, 130, 16);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		
		checkbox2.setVisible(false);
		checkbox2.setBounds(167, 142, 130, 16);
		checkbox2.setForeground(new Color(245, 245, 245));
		checkbox2.setBackground(new Color(47, 79, 79));
		frame.getContentPane().add(checkbox2);
		
		
        //comboBox2 = new JComboBox<>(PackageManager.getAirlines(data)); 
        comboBox2.setBackground(new Color(205, 92, 92));
        comboBox2.setBounds(294, 84, 106, 22);
        comboBox2.setEnabled(false); 
        
        //comboBox2 Action Listener
		comboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrivalCity=(String) comboBox2.getSelectedItem();
                if(selectedItems.size()>1) {
                	selectedItems.remove(selectedItems.get(1));
                }
                selectedItems.add(arrivalCity);
                System.out.println(selectedItems.toString());
                comboBox2.setEnabled(false); 
                chckbxNewCheckBox.setVisible(true);
                checkbox2.setVisible(true);
            }
        });

        //Initializing the JButton with the info of the flights found. Setting it as inivisble
		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(187, 182, 89, 23);
		frame.getContentPane().add(continueButton);
		continueButton.setVisible(false);
		
		//comboBox3 settings 
        comboBox3.setBackground(new Color(205, 92, 92));
        comboBox3.setBounds(177, 146, 106, 22);
        //Action Listener for comboBox3 (Airline selection)
        comboBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	airline=(String) comboBox3.getSelectedItem();//assign airline the value of selected item
            	if(selectedItems.size()>3) {//check the selectedItems list for appropriate length before adding
                	selectedItems.remove(selectedItems.get(3));
                }
            	selectedItems.add(airline);//add airline to the selectedItems list
            	System.out.println(selectedItems);
            	//Now a flight object will be created with the information provided from selectedItems.
            	//I created a flight object at this stage because of the assumption that there is only one direct and stopover flight available for each route.
            	flight= new Flight(selectedItems.get(0),selectedItems.get(1),selectedItems.get(2),selectedItems.get(3));
            	//make continue button visible
            	continueButton.setVisible(true);
            	
            }
        });
        
        frame.getContentPane().add(comboBox1);
        frame.getContentPane().add(comboBox2);
        frame.getContentPane().add(comboBox3);
        
        
		//Initializing the day year and month combo boxes. 
        String[] months = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        JComboBox monthComboBox = new JComboBox(months);
        monthComboBox.setVisible(false);
        monthComboBox.setBackground(new Color(240, 128, 128));
		monthComboBox.setBounds(106, 256, 89, 22);
		frame.getContentPane().add(monthComboBox);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedYear = (int)yearComboBox.getSelectedItem();
			}
		});
		yearComboBox.setVisible(false);
		yearComboBox.setBackground(new Color(240, 128, 128));
		yearComboBox.setBounds(296, 256, 69, 22);
		frame.getContentPane().add(yearComboBox);
		Calendar calendar = Calendar.getInstance();
        yearComboBox.addItem(2025);
        confirmButton2.setVisible(false);
        
        
        String[] days= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox dayComboBox = new JComboBox(days);
        dayComboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedDay = dayComboBox.getSelectedIndex()+1;
        	}
        });
        dayComboBox.setVisible(false);
        dayComboBox.setBackground(new Color(240, 128, 128));
		dayComboBox.setBounds(215, 256, 57, 22);
		frame.getContentPane().add(dayComboBox);
		//Editing the number of days displayed according to the month selected.
		monthComboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedMonth = monthComboBox.getSelectedIndex()+1;
                calendar.set(selectedYear, selectedMonth, 1);
                int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                // Repopulate the day ComboBox
                dayComboBox.removeAllItems();
                for (int day = 1; day <= daysInMonth; day++) {
                    dayComboBox.addItem(day);
                }
        	}
        });
		
		//Button for confirming date. Sets the flight date as specified.
		JButton confirmDateButton = new JButton("Confirm Date");
		confirmDateButton.setVisible(false);
        confirmDateButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (selectedMonth==0) {selectedMonth=1;} //addressing no selection
        		if (selectedDay==0) {selectedDay=1;} //addressing no selection
        		selectedDate= String.format("%04d-%02d-%02d",selectedYear,selectedMonth,selectedDay);
                flight.setDate(selectedDate);
                System.out.println(selectedDate);
                comboBox1.setVisible(false);comboBox2.setVisible(false);comboBox3.setVisible(false);confirmDateButton.setVisible(false);lblNewLabel_2.setVisible(false);
                monthComboBox.setVisible(false);dayComboBox.setVisible(false);yearComboBox.setVisible(false);lblNewLabel.setVisible(false);lblNewLabel_1.setVisible(false);
                
                flight_info.setText("Your chosen flight details:");
                flight_info1.setText("Departure City: "+flight.getDepartureCity());
                flight_info2.setText("Arrival City: "+flight.getArrivalCity());
                flight_info3.setText("Airlines: "+flight.getAirline());
                flight_info4.setText("Ticket Class: "+flight.getTicketClass());
                flight_info5.setText("Ticket Price: $"+flight.getPrice());
                flight_info6.setText("Date of Flight: "+selectedDate);
                if(flight.isDirect()) {
                	flight_info7.setText("DepartureTime: "+flight.getDepartureTime());
                	flight_info8.setText("Arrival Time: "+flight.getArrivalTime());
                }
                else {
                	flight_info7.setText("Stop Over City: "+flight.getStopoverCity());
                	flight_info8.setText("Leg 1 Departure Time: "+flight.getLeg1depTime());
                	flight_info9.setText("Leg 1 Arrival Time: "+flight.getLeg1arrTime());
                	flight_info10.setText("Leg 2 Departure Time: "+flight.getLeg2depTime());
                	flight_info11.setText("Leg 2 Arrival Time: "+flight.getLeg2arrTime());
                }
                end.setVisible(true);
        	}
        });
		confirmDateButton.setBounds(180, 287, 110, 19);
		frame.getContentPane().add(confirmDateButton);
		
		
        //ContinueButton action listener
        continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adding an option dialog to ask the user if they would continue with the ticket class.
				int choice = JOptionPane.showOptionDialog(frame,"The chosen flight has only '"+flight.getTicketClass()+"' Class tickets left. \nWould you like to proceed?",
                        "Flight Class Selection",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,
                        null, new Object[]{"Yes", "No"},  "Option 1"); 

                // Handle the user's choice. 
                if (choice == JOptionPane.YES_OPTION) {
                    comboBox3.setEnabled(false);
                    continueButton.setVisible(false);
                    //make date selection visible
                    monthComboBox.setVisible(true);dayComboBox.setVisible(true);yearComboBox.setVisible(true);confirmDateButton.setVisible(true);//make the combo boxes visible
                } else if (choice == JOptionPane.NO_OPTION) {
                	JOptionPane.showMessageDialog(frame, "Please select 'yes' to proceed. \nOtherwise, you can modify your search or start over.");
                } 
			}
		});
        
        //checkbox Action Listeners
        chckbxNewCheckBox.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			selectedItems.add("true"); //true string signifies that only direct flights (selected items at position 2)
    			//the checkbox are made invisible while the third combobox with airline options is made visible
    			chckbxNewCheckBox.setVisible(false);checkbox2.setVisible(false);comboBox3.setVisible(true);lblNewLabel_2.setVisible(true);
    			String dCity=selectedItems.get(0); //departure city selected
    			String aCity=selectedItems.get(1); //arrival city selected
    			String[] allAirlines= PackageManager.getAirlines(dCity, aCity,true); //all direct (boolean ==true) airline options in a string array
    			if(allAirlines.length==0) {
    				JOptionPane.showMessageDialog(null, "No direct flights available on this route. Start over!");//catch if no such flights available
    			}else {
    				for(int i=0;i<allAirlines.length;i++) {
        				comboBox3.addItem(allAirlines[i]);//airline options added to the comboBox
        			}
    			}
    		    }
    		});
        
        checkbox2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			selectedItems.add("false"); //false string signifies that only direct flights (selected items at position 2)
    			//the checkbox are made invisible while the third combobox with airline options is made visible
    			chckbxNewCheckBox.setVisible(false);checkbox2.setVisible(false);comboBox3.setVisible(true);lblNewLabel_2.setVisible(true);
    			String dCity=selectedItems.get(0);//departure city selected
    			String aCity=selectedItems.get(1);//arrival city selected
    			String[] allAirlines= PackageManager.getAirlines(dCity, aCity,false); //all indirect (boolean == false) airline options in a string array
    			if(allAirlines.length==0) {
    				JOptionPane.showMessageDialog(null, "No stopover flights available on this route. Start over!");//catch if no such flights available
    			}else {
    				for(int i=0;i<allAirlines.length;i++) {
        				comboBox3.addItem(allAirlines[i]);//airline options added to the comboBox
        			}
    			}
    		    }
    		});
        //checkbox action listeners end here

        
        
		JButton btnNewButton = new JButton("🔙");
		btnNewButton.setBackground(new Color(255, 250, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMainMenu a = new AdminMainMenu();
				a.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(10, 11, 60, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("▶Start Again");
		btnNewButton_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminTravelPackage a = new AdminTravelPackage();
				a.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(187, 359, 117, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TravelPackageBuilder.getInstance().setSelectedFlight(flight);
				JOptionPane.showMessageDialog(null, "Retun flight should be selecetd now");
                	flight_info.setVisible(false);flight_info1.setVisible(false);flight_info2.setVisible(false);flight_info3.setVisible(false);
                	flight_info4.setVisible(false);flight_info5.setVisible(false);flight_info6.setVisible(false);flight_info7.setVisible(false);
                	flight_info8.setVisible(false);flight_info9.setVisible(false);flight_info10.setVisible(false);flight_info11.setVisible(false);
                	monthComboBox.setVisible(true);dayComboBox.setVisible(true);yearComboBox.setVisible(true);confirmButton2.setVisible(true);end.setVisible(false);
                	boolean d= false;
                	if(selectedItems.get(2).equals("true")) {
                		d= true;
                	}
                	String returnAirline = FlightsHandler.getIndexInfo(selectedItems.get(1),selectedItems.get(0), d, 1);
                	returnFlight= new Flight(selectedItems.get(1),selectedItems.get(0),selectedItems.get(2),returnAirline);
                
			}
		});
		confirmButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedMonth==0) {selectedMonth=1;} //addressing no selection
        		if (selectedDay==0) {selectedDay=1;} //addressing no selection
        		selectedDate= String.format("%04d-%02d-%02d",selectedYear,selectedMonth,selectedDay);
        		LocalDate flightDate= LocalDate.parse(flight.getDate());
        		try {
        			LocalDate returnFlightDate= LocalDate.parse(selectedDate);
        			if(!returnFlightDate.isBefore(flightDate)&& !returnFlightDate.isEqual(flightDate)&&!returnFlightDate.isAfter(flightDate.plusDays(10))) {
        				returnFlight.setDate(selectedDate);
                        System.out.println(selectedDate);
                        TravelPackageBuilder.getInstance().setReturnFlight(returnFlight);
                        TravelPackageBuilder.getInstance().setDaysofStay(flight, returnFlight);
                        if(returnFlight.isDirect()) {
                        	JOptionPane.showMessageDialog(null, "Success! The return ticket was selected!\nAirline: "+returnFlight.getAirline()+
                            		"\nDate of Flight: "+returnFlight.getDate()+"\nPrice: $"+returnFlight.getPrice()+
                            		"\nTicket Class: "+returnFlight.getTicketClass()+"\nDeparture Time: "+returnFlight.getDepartureTime()+
                            		"\nArrival Time: "+returnFlight.getArrivalTime());
                        }
                        else{
                        	JOptionPane.showMessageDialog(null, "Success! The return ticket was selected!\nAirline: "+returnFlight.getAirline()+
                            		"\nDate of Flight: "+returnFlight.getDate()+"\nPrice: $"+returnFlight.getPrice()+
                            		"\nTicket Class: "+returnFlight.getTicketClass()+"\nLeg 1 Departure Time: "+returnFlight.getLeg1depTime()+
                            		"\nLeg 1 Arrival Time: "+returnFlight.getLeg1arrTime()+"\nLeg 2 Departure Time: "+returnFlight.getLeg2depTime()+
                            		"\nLeg 2 Arrival Time: "+returnFlight.getLeg2arrTime()+"\nStopover City: "+returnFlight.getStopoverCity());
                        
                        }
                        HotelSelectionPage  hotelSelectionPage= new  HotelSelectionPage();
        				hotelSelectionPage.getFrame().setVisible(true);
        				frame.dispose();
        			}
        			else {
        				JOptionPane.showMessageDialog(null, "The date of the return ticket is not valid. It should meet the following criteria:\n1.It should not be before the original ticket.\n2. It should not be on the same day as the original ticket.\n3. It should not be more than 10 days after the original ticket.");
        			}
        		}catch(DateTimeParseException e1) {
        			System.out.println("Invalid date format. yyyy-MM-dd format must be used.");
        		}
                
			}
		});
	}
	public JFrame getFrame() {
		return frame;
	}
}
