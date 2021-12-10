package snake_game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Panel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Credit {

	JFrame creditFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credit window = new Credit();
					window.creditFrame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */
	public Credit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		creditFrame = new JFrame("Credit Snake");
		creditFrame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 30));
		creditFrame.setResizable(false);
		creditFrame.getContentPane().setBackground(new Color(135, 206, 250));
		creditFrame.setBounds(100, 100, 1200, 600);
		creditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		creditFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("FP-PBO F");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 32));
		lblNewLabel_5.setBounds(531, 468, 121, 49);
		creditFrame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Thanks to Developers :");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 60));
		lblNewLabel.setBounds(303, 64, 578, 66);
		creditFrame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(236, 161, 711, 258);
		creditFrame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{286, 55, 182, 0};
		gbl_panel.rowHeights = new int[]{37, 37, 37, 37, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{3.5, 3.5, 3.5, 3.5, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_4 = new JLabel("  Muhammad Afif Dwi Ardiansyah");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_7 = new JLabel("[5025201066]");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 0;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_3 = new JLabel("  Muhammad Abror Al Qushoyyi ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_8 = new JLabel("[5025201028]");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 1;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_1 = new JLabel("  Gaudhiwa Hendrasto");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_9 = new JLabel("[5025201212]");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 2;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_2 = new JLabel("  Beryl ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblNewLabel_10 = new JLabel("[5025201029]");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 3;
		panel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
	}
}
