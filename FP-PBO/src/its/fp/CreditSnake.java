package its.fp;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreditSnake extends JFrame  {
	
	private final JLabel title;
	private final JLabel dev1;
	private final JLabel dev2;
	private final JLabel dev3;
	private final JLabel dev4;
	private final JLabel song;
	
	public CreditSnake() {
		super("Credit Snake");
//		setLayout(new GridLayout());
		
		title = new JLabel("Thanks to Developers :");
		title.setFont(new Font("Calibri", Font.BOLD, 47));
		title.setBounds(184, 40, 447, 65);
		add(title, BorderLayout.NORTH);
//		frame.getContentPane().add(lblNewLabel);
		
		dev1 = new JLabel("Afif");
		dev1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dev1.setBounds(382, 134, 50, 49);
		add(dev1, BorderLayout.CENTER);
//		frame.getContentPane().add(title);
		
		dev2 = new JLabel("Beryl");
		dev2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dev2.setBounds(374, 194, 67, 49);
		add(dev2, BorderLayout.CENTER);
//		frame.getContentPane().add(lblNewLabel_2);
		
		dev3 = new JLabel("Qushoy");
		dev3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dev3.setBounds(355, 257, 105, 49);
		add(dev3, BorderLayout.CENTER);
//		frame.getContentPane().add(lblNewLabel_3);
		
		dev4 = new JLabel("Dhiwa");
		dev4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dev4.setBounds(366, 325, 82, 49);
		add(dev4, BorderLayout.CENTER);
//		frame.getContentPane().add(lblNewLabel_4);
		
		song = new JLabel("Song : `Nama Lagu`");
		song.setFont(new Font("Calibri", Font.BOLD, 32));
		song.setBounds(280, 419, 255, 49);
		add(song, BorderLayout.SOUTH);
//		frame.getContentPane().add(lblNewLabel_5);
		
		
	}
}
