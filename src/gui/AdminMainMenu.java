package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainMenu window = new AdminMainMenu();
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
	public AdminMainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(210, 180, 140));
		frame.setBounds(100, 100, 522, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcome = new JLabel("Welcome Back, Admin!");
		welcome.setBackground(new Color(128, 0, 0));
		welcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		welcome.setBounds(159, 46, 192, 14);
		frame.getContentPane().add(welcome);
		
		JLabel lblNewLabel_1 = new JLabel("TRAVEL PACKAGES");
		lblNewLabel_1.setBounds(293, 118, 119, 14);
		frame.getContentPane().add(lblNewLabel_1);
		JButton btnNewButton_1 = new JButton("MANAGE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminTravelPackage tpPage= new AdminTravelPackage();
				tpPage.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setForeground(new Color(211, 211, 211));
		btnNewButton_1.setBackground(new Color(165, 42, 42));
		btnNewButton_1.setBounds(300, 208, 105, 14);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel box2 = new JLabel("");
		box2.setBounds(288, 108, 129, 121);
		Image img2 = new ImageIcon(this.getClass().getResource("/icon2.jpeg")).getImage();
		Image dimg2= img2.getScaledInstance(box2.getWidth(), box2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon2= new ImageIcon(dimg2);
		
		box2.setIcon(icon2);
		frame.getContentPane().add(box2);
		
		JButton btnNewButton = new JButton("MANAGE");
		btnNewButton.setForeground(new Color(211, 211, 211));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminManagePackages p= new AdminManagePackages();
				p.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBackground(new Color(165, 42, 42));
		btnNewButton.setBounds(93, 208, 105, 14);
		frame.getContentPane().add(btnNewButton);
		JLabel lblNewLabel = new JLabel("PACKAGES");
		lblNewLabel.setBounds(105, 118, 100, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel box1 = new JLabel("");
		box1.setForeground(new Color(211, 211, 211));
		box1.setBounds(81, 108, 124, 121);
		Image img1 = new ImageIcon(this.getClass().getResource("/icon1.jpeg")).getImage();
		Image dimg1= img1.getScaledInstance(box1.getWidth(), box1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon1= new ImageIcon(dimg1);
		
		box1.setIcon(icon1);
		frame.getContentPane().add(box1);
		
		JButton btnNewButton_2 = new JButton("USER STATS");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserStatsPage ad= new AdminUserStatsPage();
				ad.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setForeground(new Color(248, 248, 255));
		btnNewButton_2.setBackground(new Color(165, 42, 42));
		btnNewButton_2.setBounds(159, 277, 156, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
		
		
		
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}
}
