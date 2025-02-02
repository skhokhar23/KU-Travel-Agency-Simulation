package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import handler.FlightsHandler;
import handler.HotelsHandler;
import handler.PackageManager;
import packageComponents.Flight;
import packageComponents.Hotel;
import travelPackage.TravelPackageBuilder;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CustomerHotelSelection{

	private JFrame frame;
	private String selectedHotel, selectedRoomType;
	
	private Hotel hotel;
	private String arrivalCity = TravelPackageBuilder.getInstance().getSelectedFlight().getArrivalCity();
	//private String arrivalCity = "Istanbul";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerHotelSelection window = new CustomerHotelSelection();
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
	public CustomerHotelSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HotelsHandler h= new HotelsHandler();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 240));
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel HotelNameLabel = new JLabel("Hotel");
		HotelNameLabel.setForeground(new Color(240, 128, 128));
		HotelNameLabel.setBounds(136, 117, 40, 14);
		frame.getContentPane().add(HotelNameLabel);
		
		
		JComboBox HotelNameCombo = new JComboBox<>(HotelsHandler.getFilteredPart(arrivalCity, 1, 0));
		//JComboBox HotelNameCombo = new JComboBox<>(HotelsHandler.getPart(0));
		HotelNameCombo.setBackground(new Color(240, 128, 128));
		HotelNameCombo.setBounds(112, 142, 83, 22);
		frame.getContentPane().add(HotelNameCombo);
		
		JLabel RoomTypeLabel = new JLabel("Room Type");
		RoomTypeLabel.setForeground(new Color(240, 128, 128));
		RoomTypeLabel.setBounds(297, 117, 74, 14);
		frame.getContentPane().add(RoomTypeLabel);
		
		JComboBox roomTypeCombo = new JComboBox();
		roomTypeCombo.setEnabled(false);
		roomTypeCombo.setBackground(new Color(240, 128, 128));
		roomTypeCombo.setBounds(288, 142, 83, 22);
		frame.getContentPane().add(roomTypeCombo);
		
		
		JButton confirmSelection = new JButton("Confirm room Type");
		confirmSelection.setBackground(new Color(240, 128, 128));
		confirmSelection.setEnabled(false);
		confirmSelection.setBounds(170, 211, 148, 23);
		frame.getContentPane().add(confirmSelection);
		
		JButton startOver = new JButton("Start Over");
		startOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelSelectionPage h = new HotelSelectionPage();
            	h.getFrame().setVisible(true);
            	frame.dispose();
			}
		});
		startOver.setBounds(190, 349, 128, 22);
		frame.getContentPane().add(startOver);
		
		
		JLabel lblNewLabel = new JLabel("Hotel Selection Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel.setBackground(new Color(240, 128, 128));
		lblNewLabel.setBounds(170, 69, 182, 22);
		frame.getContentPane().add(lblNewLabel);
		
		//Action Listener for Hotel Names Combo Box
		HotelNameCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(TravelPackageBuilder.getInstance().getDaysofStay());
				confirmSelection.setEnabled(true);
				roomTypeCombo.setEnabled(true);
				HotelNameCombo.setEnabled(false);
				selectedHotel = (String) HotelNameCombo.getSelectedItem();
                for(int i=0;i<HotelsHandler.getFilteredPart(selectedHotel, 0, 2).length;i++) {
                	roomTypeCombo.addItem(HotelsHandler.getFilteredPart(selectedHotel, 0, 2)[i]);
                }
			}
		});
		//Action Listener for room type combo box.
		roomTypeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//Action listener for confirmSelection
		confirmSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmSelection.setEnabled(false);
				selectedRoomType=(String) roomTypeCombo.getSelectedItem();
				hotel = new Hotel(selectedHotel,selectedRoomType,arrivalCity);
				int choice = JOptionPane.showOptionDialog(frame,"These are your hotel selection details:\nCity of Stay: "+arrivalCity+"\nHotel: "+hotel.getHotelName()+"\nDate of check-in: "+ TravelPackageBuilder.getInstance().getSelectedFlight().getDate()+
						"\nDate of check-out: "+TravelPackageBuilder.getInstance().getReturnFlight().getDate()+"\nDuration of stay: "+TravelPackageBuilder.getInstance().getDaysofStay()+
						" days\nPrice: $"+hotel.getPrice()+" per night\nTotal Cost: $" +(hotel.getPrice()*TravelPackageBuilder.getInstance().getDaysofStay())+"\nDistance from Airport: "+hotel.getDistanceToAirport()+"km",
                        "Flight Class Selection",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,
                        null, new Object[]{"Yes", "No"},  "Option 1"); 
				if (choice == JOptionPane.YES_OPTION) {
					TravelPackageBuilder.getInstance().setSelectedHotel(hotel);
                	hotel.bookRoom(TravelPackageBuilder.getInstance().getSelectedFlight().getDate(), TravelPackageBuilder.getInstance().getReturnFlight().getDate());
                	CustomerTaxiSelection t= new CustomerTaxiSelection();
                	t.getFrame().setVisible(true);
                	frame.dispose();
                } else if (choice == JOptionPane.NO_OPTION) {
                	HotelSelectionPage h = new HotelSelectionPage();
                	h.getFrame().setVisible(true);
                	frame.dispose();
                }
				roomTypeCombo.setEnabled(false);
				
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

}