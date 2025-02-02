package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import travelPackage.TravelPackageManager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AdminManagePackages {

	private JFrame frame;
private String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManagePackages window = new AdminManagePackages();
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
	public AdminManagePackages() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 240));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 128, 128));
		panel.setBounds(282, 0, 156, 265);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Welcome Back, Admin!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 119, 136, 14);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("↩ Back");
		btnNewButton_3.setVisible(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminManagePackages  a= new AdminManagePackages ();
				a.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(25, 231, 94, 23);
		panel.add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(240, 128, 128));
		
		JLabel lblNewLabel_1 = new JLabel("Please select the travel package from the comboBox.");
		lblNewLabel_1.setForeground(new Color(240, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(11, 11, 261, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		for(int i=0; i<TravelPackageManager.getAllPackages().size();i++) {
			comboBox.addItem(TravelPackageManager.getAllPackages().get(i).getPackageName());
		}
		comboBox.setBackground(new Color(240, 128, 128));
		comboBox.setBounds(91, 92, 106, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Travel Packages");
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(91, 67, 106, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("View the package details.");
		btnNewButton.setEnabled(false);
		btnNewButton.setBackground(new Color(240, 128, 128));
		btnNewButton.setBounds(50, 162, 186, 20);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea t = new JTextArea();
		t.setVisible(false);
		t.setBounds(11, 23, 261, 182);
		frame.getContentPane().add(t);
		
		JButton btnNewButton_1 = new JButton("Delete Package");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBackground(new Color(240, 128, 128));
		btnNewButton_1.setBounds(11, 220, 115, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		JButton btnNewButton_2 = new JButton("Modify Package");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setBackground(new Color(240, 128, 128));
		btnNewButton_2.setBounds(149, 220, 123, 23);
		frame.getContentPane().add(btnNewButton_2);
		
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
		btnNewButton_2.setVisible(false);
	
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				name = (String)comboBox.getSelectedItem();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(false);btnNewButton.setVisible(false);lblNewLabel_2.setVisible(false);btnNewButton_4.setVisible(false);
				lblNewLabel_1.setVisible(false);btnNewButton_3 .setVisible(true);lblNewLabel_3.setVisible(true);
				t.setVisible(true);btnNewButton_2.setVisible(true);btnNewButton_1.setVisible(true);
				t.setText(TravelPackageManager.getAllPackages().get(0).toString());
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<TravelPackageManager.getAllPackages().size();i++) {
					if(TravelPackageManager.getAllPackages().get(i).getPackageName().equals(name)) {
						TravelPackageManager.getAllPackages().remove(i);
					}
				}
				TravelPackageManager.saveBookings("travel-packages.txt", TravelPackageManager.getAllPackages());
				AdminManagePackages  a= new AdminManagePackages ();
				a.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

}
