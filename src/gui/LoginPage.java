package gui;

import java.awt.EventQueue;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.SoftBevelBorder;

import Users.Customer;
import Users.User;
import Users.UserManager;

import javax.swing.border.BevelBorder;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPage {

	private JFrame frame;
	private JTextField userField;
	private JPasswordField passField;
	/**
	 * Login Page launch
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(20, 19, 30));
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Please enter your credentials:");
		lblNewLabel_1.setForeground(new Color(175, 182, 197));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(54, 79, 172, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		userField = new JTextField();
		userField.setBounds(105, 123, 121, 27);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(105, 176, 121, 27);
		frame.getContentPane().add(passField);
		passField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setForeground(new Color(175, 182, 197));
		lblNewLabel_2.setBounds(30, 129, 65, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setForeground(new Color(175, 182, 197));
		lblNewLabel_3.setBounds(30, 182, 65, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton LoginButton = new JButton("LOGIN");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID= userField.getText();
				String password=String.valueOf(passField.getPassword());
				boolean thereExists=false;
				
					for(String u: UserManager.getAllUsers().keySet()) {
						if(ID.equals(u)) {
							thereExists=true;
							if(password.equals(UserManager.getAllUsers().get(u).getPassword())) {
								if(ID.equals("admin")) {
									AdminMainMenu adminPage= new AdminMainMenu();
									adminPage.getFrame().setVisible(true);
									frame.dispose();
								}
								else {
									User activeUser= UserManager.login(ID, password);
									UserManager.setActiveUser(activeUser);
									CustomerMainMenu customerPage= new CustomerMainMenu();
									customerPage.getFrame().setVisible(true);
									frame.dispose();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Incorrect password. Try again!");
							}
						}
					}
					if(thereExists==false) {
						JOptionPane.showMessageDialog(null, "The user does not exist. Try registering first!");
					}
				
				
			}
		});
		
		LoginButton.setBackground(new Color(82, 99, 84));
		LoginButton.setBounds(84, 238, 89, 23);
		frame.getContentPane().add(LoginButton);
		
		JButton RegisterButton = new JButton("REGISTER");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPage registerPage= new RegisterPage();
				registerPage.getFrame().setVisible(true);
				frame.dispose();
				
			}
		});
		RegisterButton.setBackground(new Color(82, 99, 84));
		RegisterButton.setBounds(81, 307, 102, 23);
		frame.getContentPane().add(RegisterButton);
		
		JLabel lblNewLabel_4 = new JLabel("Not a user yet?");
		lblNewLabel_4.setForeground(new Color(175, 182, 197));
		lblNewLabel_4.setBounds(94, 272, 102, 24);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel purpleGradientLabel = new JLabel("Welcome");
		purpleGradientLabel.setBounds(282, 0, 228, 382);
		Image img1 = new ImageIcon(this.getClass().getResource("/purplegradient.jpeg")).getImage();
		Image dimg1= img1.getScaledInstance(purpleGradientLabel.getWidth(), purpleGradientLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon1= new ImageIcon(dimg1);
		
		purpleGradientLabel.setIcon(icon1);
		frame.getContentPane().add(purpleGradientLabel);
		
		JLabel lblNewLabel = new JLabel("Welcome Back!");
		lblNewLabel.setBounds(368, 182, 48, 14);
		purpleGradientLabel.add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
