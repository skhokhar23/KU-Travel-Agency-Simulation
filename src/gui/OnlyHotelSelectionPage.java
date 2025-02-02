package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Users.UserManager;
import handler.HotelsHandler;
import handler.PackageManager;
import packageComponents.Hotel;
import java.awt.Font;
import java.awt.Image;

public class OnlyHotelSelectionPage {

	private JFrame frame;
	private String selectedHotel, selectedRoomType,selectedDate,endDate;
	private int daysOfStay;
	private int selectedDay,selectedMonth,selectedYear;
	private Hotel hotel;
	//private String arrivalCity = TravelPackageBuilder.getInstance().getSelectedFlight().getArrivalCity();
	private String arrivalCity;
	private JLabel lblNewLabel;
	private PackageManager p = new PackageManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlyHotelSelectionPage window = new OnlyHotelSelectionPage();
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
	public OnlyHotelSelectionPage() {
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
		HotelNameLabel.setBounds(82, 110, 40, 14);
		frame.getContentPane().add(HotelNameLabel);
		
		
		//JComboBox HotelNameCombo = new JComboBox<>(HotelsHandler.getFilteredPart(arrivalCity, 1, 0));
		JComboBox HotelNameCombo = new JComboBox<>(HotelsHandler.getPart(0));
		HotelNameCombo.setBackground(new Color(240, 128, 128));
		HotelNameCombo.setBounds(63, 135, 83, 22);
		frame.getContentPane().add(HotelNameCombo);
		
		JLabel RoomTypeLabel = new JLabel("Room Type");
		RoomTypeLabel.setForeground(new Color(240, 128, 128));
		RoomTypeLabel.setBounds(206, 110, 74, 14);
		frame.getContentPane().add(RoomTypeLabel);
		
		JComboBox roomTypeCombo = new JComboBox();
		roomTypeCombo.setEnabled(false);
		roomTypeCombo.setBackground(new Color(240, 128, 128));
		roomTypeCombo.setBounds(197, 135, 83, 22);
		frame.getContentPane().add(roomTypeCombo);
		
		//Initializing the day year and month combo boxes. 
        String[] months = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        JComboBox monthComboBox = new JComboBox(months);
        monthComboBox.setVisible(false);
        monthComboBox.setBackground(new Color(240, 128, 128));
		monthComboBox.setBounds(33, 256, 89, 22);
		frame.getContentPane().add(monthComboBox);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setVisible(false);
		yearComboBox.setBackground(new Color(240, 128, 128));
		yearComboBox.setBounds(229, 256, 69, 22);
		frame.getContentPane().add(yearComboBox);
		Calendar calendar = Calendar.getInstance();
        yearComboBox.addItem(2025);
        
        String[] days= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox dayComboBox = new JComboBox(days);
        dayComboBox.setVisible(false);
        dayComboBox.setBackground(new Color(240, 128, 128));
		dayComboBox.setBounds(148, 256, 57, 22);
		frame.getContentPane().add(dayComboBox);
		
		JButton confirmSelection = new JButton("Confirm room Type");
		confirmSelection.setEnabled(false);
		confirmSelection.setBackground(new Color(240, 128, 128));
		confirmSelection.setBounds(105, 188, 148, 23);
		frame.getContentPane().add(confirmSelection);
		
		JButton confirmDate = new JButton("Confirm Date");
		confirmDate.setVisible(false);
		confirmDate.setBackground(new Color(240, 128, 128));
		confirmDate.setBounds(105, 289, 148, 23);
		frame.getContentPane().add(confirmDate);
		
		JButton startOver = new JButton("Start Over");
		startOver.setBounds(105, 348, 112, 23);
		frame.getContentPane().add(startOver);
		
		JLabel dateLabel = new JLabel("Please select date of check-in:");
		dateLabel.setVisible(false);
		dateLabel.setForeground(new Color(240, 128, 128));
		dateLabel.setBounds(105, 222, 203, 14);
		frame.getContentPane().add(dateLabel);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(335, 11, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel picture = new JLabel("");
		picture.setBounds(322, 48, 225, 334);
		frame.getContentPane().add(picture);
		
		JLabel lblNewLabel_1 = new JLabel("Hotel Selection Page");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(180, 8, 269, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		HotelNameCombo.setEnabled(false);
		
		JComboBox cityBox = new JComboBox<>(PackageManager.getDepartureCity());
		
		cityBox.setBackground(new Color(240, 128, 128));
		cityBox.setBounds(124, 81, 81, 22);
		frame.getContentPane().add(cityBox);
		
		JLabel lblNewLabel_2 = new JLabel("City");
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(148, 56, 48, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/IMG_6037.jpeg")).getImage();
		Image dimg1= img1.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon1= new ImageIcon(dimg1);
		
		picture.setIcon(icon1);
		
		String[] daysof= {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox durationOfStay = new JComboBox(daysof);
		durationOfStay.setVisible(false);
		durationOfStay.setBackground(new Color(240, 128, 128));
		durationOfStay.setBounds(132, 256, 87, 22);
		frame.getContentPane().add(durationOfStay);
		
		JLabel durationof = new JLabel("Please select duration of Stay:");
		durationof.setVisible(false);
		durationof.setForeground(new Color(240, 128, 128));
		durationof.setBounds(115, 231, 183, 14);
		frame.getContentPane().add(durationof);
		
		JButton complete = new JButton("Complete Booking");
		complete.setVisible(false);
		complete.setForeground(new Color(240, 128, 128));
		complete.setBounds(90, 289, 150, 23);
		frame.getContentPane().add(complete);
		//Action Listener for Hotel Names Combo Box
		HotelNameCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(TravelPackageBuilder.getInstance().getDaysofStay());
				
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
				confirmSelection.setEnabled(true);
			}
		});
		//Action Listener for year combo box.
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedYear = (int)yearComboBox.getSelectedItem();
			}
		});
		//Action Listener for year combo box.
				dayComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedDay = dayComboBox.getSelectedIndex()+1;
					}
				});
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
		//Action listener for confirmSelection
		confirmSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmSelection.setEnabled(false);
				selectedRoomType=(String) roomTypeCombo.getSelectedItem();
				hotel = new Hotel(selectedHotel,selectedRoomType,arrivalCity);
				roomTypeCombo.setEnabled(false);confirmDate.setVisible(true);dateLabel.setVisible(true);
				yearComboBox.setVisible(true);dayComboBox.setVisible(true);monthComboBox.setVisible(true);
			}
		});
		//Action Listener for Confirm Date
		confirmDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedMonth==0) {selectedMonth=1;} //addressing no selection
        		if (selectedDay==0) {selectedDay=1;} //addressing no selection
        		selectedYear=2025;
        		System.out.println(selectedDay);
        		selectedDate= String.format("%04d-%02d-%02d",selectedYear,selectedMonth,selectedDay);
        		System.out.println(selectedDate);
                hotel.setDate(selectedDate);
                System.out.println(hotel.getDate());
                durationof.setVisible(true);
                durationOfStay.setVisible(true);
                dateLabel.setVisible(false);
                confirmDate.setVisible(false);
                complete.setVisible(true);
                monthComboBox.setVisible(false);dayComboBox.setVisible(false);yearComboBox.setVisible(false);
			}
		});
		cityBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrivalCity= (String) cityBox.getSelectedItem();
				HotelNameCombo.setEnabled(true);
			}
		});
		durationOfStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daysOfStay= durationOfStay.getSelectedIndex()+1;
				DateTimeFormatter dtf =DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDate=LocalDate.parse(selectedDate,dtf);
				LocalDate c= startDate;
				c=c.plusDays(daysOfStay);
				endDate=c.format(dtf);
				
			}
		});
		complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(endDate);
				hotel.bookRoom(selectedDate, endDate);
				UserManager.getActiveUser().incrementHotel();
				UserManager.getActiveUser().incrementReservations();
				UserManager.getActiveUser().addHotelMoney(hotel.getPrice()*daysOfStay);
				UserManager.getActiveUser().addMoneySpent(hotel.getPrice()*daysOfStay);
				UserManager.updateUser(UserManager.getActiveUser());
				UserManager.saveUsersToTextFile("user-stats.txt");
				CustomerMainMenu c= new CustomerMainMenu();
				c.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	
	

}

	public JFrame getFrame() {
		return frame;
	}}
