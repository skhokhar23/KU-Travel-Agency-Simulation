package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Users.User;
import Users.UserManager;
import handler.FileHandler;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPage {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passField_1;
	private JPasswordField passField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage window = new RegisterPage();
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
	public RegisterPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(22, 23, 33));
		frame.getContentPane().setForeground(new Color(20, 19, 30));
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome");
		lblNewLabel_4.setForeground(new Color(192, 192, 192));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBackground(new Color(64, 128, 128));
		lblNewLabel_4.setBounds(65, 177, 118, 37);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("to KU Travel!");
		lblNewLabel_5.setForeground(new Color(192, 192, 192));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(56, 208, 107, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 207, 382);
		Image img1 = new ImageIcon(this.getClass().getResource("/purplegradient.jpeg")).getImage();
		Image dimg1= img1.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon1= new ImageIcon(dimg1);
		
		lblNewLabel.setIcon(icon1);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(348, 129, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passField_1 = new JPasswordField();
		passField_1.setBounds(348, 187, 96, 20);
		frame.getContentPane().add(passField_1);
		passField_1.setColumns(10);
		
		passField_2 = new JPasswordField();
		passField_2.setBounds(348, 245, 96, 20);
		frame.getContentPane().add(passField_2);
		passField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(183, 185, 206));
		lblNewLabel_1.setBounds(263, 132, 62, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(new Color(183, 185, 206));
		lblNewLabel_2.setBounds(263, 190, 62, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm Password:");
		lblNewLabel_3.setForeground(new Color(183, 185, 206));
		lblNewLabel_3.setBounds(228, 248, 125, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton registerButton = new JButton("REGISTER");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username= textField.getText();
				String password= String.valueOf(passField_1.getPassword());
				String confirmPass= String.valueOf(passField_2.getPassword());
				
				if(username.isEmpty()|| password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
				}
				else {
				for(String name : UserManager.getAllUsers().keySet()) {
					if(username.equals(name)) {
						JOptionPane.showMessageDialog(null, "User already exists! Please choose another one.");
					}
				}
				if(username.equals("admin")) {
					JOptionPane.showMessageDialog(null, "New admins cannot be registered!");
				}
				else {
				if(username.length()<11) {
					if(password.equals(confirmPass)) {
						User user= new User(username, password);
						UserManager.addUser(user);
						JOptionPane.showMessageDialog(null, "You have been registered","Success",JOptionPane.INFORMATION_MESSAGE);
						
						FileHandler.saveUsers("users.txt",UserManager.getAllUsers());
						LoginPage lPage= new LoginPage();
						lPage.getFrame().setVisible(true);
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "The entered passwords do not match. Try again!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "The username entered is longer than 10 characters.");
				}}
				}
			}
		});
		registerButton.setBackground(new Color(82, 99, 84));
		registerButton.setBounds(290, 301, 107, 23);
		frame.getContentPane().add(registerButton);
		
		JLabel lblNewLabel_6 = new JLabel("Register in one step!");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setForeground(new Color(192, 192, 192));
		lblNewLabel_6.setBounds(290, 58, 154, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Create a username and a password.");
		lblNewLabel_7.setBackground(new Color(183, 185, 206));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setForeground(new Color(134, 112, 186));
		lblNewLabel_7.setBounds(263, 83, 195, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("(username should not exceed 10 characters)");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_8.setForeground(new Color(82, 99, 84));
		lblNewLabel_8.setBounds(263, 157, 227, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		
		
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	

}
