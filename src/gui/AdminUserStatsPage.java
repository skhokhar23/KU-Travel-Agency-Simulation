package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Users.User;
import Users.UserManager;
import travelPackage.TravelPackageManager;

public class AdminUserStatsPage {

	private JFrame frame;
	private String name;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUserStatsPage window = new AdminUserStatsPage();
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
	public AdminUserStatsPage() {
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
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 128, 128));
		panel.setBounds(307, 0, 203, 382);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Welcome Back, Admin!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(44, 154, 136, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("↩ Back");
		btnNewButton_3.setVisible(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserStatsPage  a= new AdminUserStatsPage ();
				a.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(57, 232, 94, 23);
		panel.add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(240, 128, 128));
		
		JLabel lblNewLabel_1 = new JLabel("Please select the username from the comboBox.");
		lblNewLabel_1.setForeground(new Color(240, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(11, 11, 261, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		for(int i=0; i<UserManager.getAllUserStats().size();i++) {
			comboBox.addItem(UserManager.getAllUserStats().get(i).getUsername());
		}
		comboBox.setBackground(new Color(240, 128, 128));
		comboBox.setBounds(91, 92, 106, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Select username");
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(96, 67, 115, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("View User History");
		btnNewButton.setEnabled(false);
		btnNewButton.setBackground(new Color(240, 128, 128));
		btnNewButton.setBounds(50, 162, 186, 20);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea t = new JTextArea();
		t.setVisible(false);
		t.setBounds(11, 23, 261, 220);
		frame.getContentPane().add(t);
		
		JLabel lblNewLabel_3 = new JLabel("Package Details");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setForeground(new Color(240, 128, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(76, 11, 121, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_4 = new JButton("↩ Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMainMenu m = new AdminMainMenu();
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_4.setBackground(new Color(255, 250, 240));
		btnNewButton_4.setBounds(91, 220, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("View User Rankings");
	
		btnNewButton_1.setBackground(new Color(240, 128, 128));
		btnNewButton_1.setBounds(76, 307, 160, 23);
		frame.getContentPane().add(btnNewButton_1);
	
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				name = (String)comboBox.getSelectedItem();
				for(int i=0; i<UserManager.getAllUserStats().size();i++) {
					if(UserManager.getAllUserStats().get(i).getUsername().equals(name)) {
						user= UserManager.getAllUserStats().get(i);
					}
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(false);btnNewButton.setVisible(false);lblNewLabel_2.setVisible(false);btnNewButton_4.setVisible(false);
				lblNewLabel_1.setVisible(false);btnNewButton_3 .setVisible(true);lblNewLabel_3.setVisible(true);
				t.setVisible(true);
				t.setText(user.toString());
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(false);btnNewButton.setVisible(false);lblNewLabel_2.setVisible(false);btnNewButton_4.setVisible(false);
				lblNewLabel_1.setVisible(false);btnNewButton_3 .setVisible(true);lblNewLabel_3.setVisible(true);
				t.setVisible(true);
				t.setText(UserManager.printRankedUsers());
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

}
