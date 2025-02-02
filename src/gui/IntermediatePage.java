package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Users.UserManager;

public class IntermediatePage {

	private JFrame frame;

	/**
	 * Launch the page.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntermediatePage window = new IntermediatePage();
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
	public IntermediatePage() {
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
		
		JButton btnNewButton = new JButton("Hotels");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnlyHotelSelectionPage i = new OnlyHotelSelectionPage ();
				i.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 250, 240));
		btnNewButton.setBounds(173, 262, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Flights");
		btnNewButton_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1.setBounds(173, 298, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Taxis");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnlyTaxiSelectionPage ot=new OnlyTaxiSelectionPage();
				ot.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setBackground(new Color(255, 250, 240));
		btnNewButton_2.setBounds(173, 332, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("↩ Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerMainMenu c= new CustomerMainMenu();
				c.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_3.setBackground(new Color(255, 250, 240));
		btnNewButton_3.setBounds(10, 332, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

	public JFrame getFrame() {
		return frame;
	}


}
