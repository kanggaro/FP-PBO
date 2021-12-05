package its.fp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;

public class GO extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GO frame = new GO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GO() {
		
		super("Game Over");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(157, 182, 71));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GAME OVER");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 72));
		lblNewLabel.setBounds(306, 116, 372, 82);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Score : 100");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(387, 226, 210, 49);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("High Score : 10000");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(294, 306, 348, 49);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(325, 443, 334, 82);
		panel.setBackground(new Color(157, 182, 71));
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Press Enter to Try again");
		lblNewLabel_2.setForeground(UIManager.getColor("Button.darkShadow"));
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblNewLabel_4 = new JLabel("Press Exit to Menu");
		lblNewLabel_4.setForeground(UIManager.getColor("Button.darkShadow"));
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
			
		
		Icon music = new ImageIcon(getClass().getResource("img/sound.png"));
		JLabel lblNewLabel_5 = new JLabel(music);
		lblNewLabel_5.setBounds(928, 608, 46, 42);
		contentPane.add(lblNewLabel_5);
	}
}
