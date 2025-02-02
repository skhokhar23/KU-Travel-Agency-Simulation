package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Users.UserManager;
import travelPackage.TravelPackageManager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMainMenu window = new CustomerMainMenu();
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
	public CustomerMainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(33, 31, 27));
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel graphicLabel = new JLabel("");
		graphicLabel.setBounds(0, 0, 510, 251);
		Image img1 = new ImageIcon(this.getClass().getResource("/graphic1.jpeg")).getImage();
		Image dimg1= img1.getScaledInstance(graphicLabel.getWidth(), graphicLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon1= new ImageIcon(dimg1);
		
		graphicLabel.setIcon(icon1);
		frame.getContentPane().add(graphicLabel);
		
		JLabel lblNewLabel = new JLabel("Welcome to KUTravel, "+UserManager.getActiveUser().getUsername()+"!");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(172, 262, 201, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton customPackage = new JButton("CUSTOM PACKAGES");
		customPackage.setBackground(new Color(255, 250, 240));
		customPackage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		customPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerPackageFlightSelection c= new CustomerPackageFlightSelection();
				c.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		customPackage.setBounds(107, 299, 144, 23);
		frame.getContentPane().add(customPackage);
		
		JButton btnNewButton_1 = new JButton("EXISITING PACKAGES");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerExistingTravelPackage cetp=new CustomerExistingTravelPackage();
				cetp.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(291, 299, 137, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("BOOKING HISTORY");
		btnNewButton.setBackground(new Color(255, 250, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(107, 335, 144, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("INDIVIDUAL ENTITIES");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntermediatePage ip=new IntermediatePage();
				ip.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setBackground(new Color(255, 250, 240));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setBounds(291, 335, 137, 23);
		frame.getContentPane().add(btnNewButton_2);
	}

	public JFrame getFrame() {
		return frame;
	}

	
}
