package snake_game;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;



public class MenuFrame extends JFrame {
	
    public static void addComponentsToPane(Container pane) {
        	
    	pane.add(Box.createVerticalGlue());
        pane.add(Box.createVerticalGlue());
        
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(new Color(22,182,123));

		//Icon Snake = new ImageIcon(getClass().getResource( "Snake887.png"));
		JLabel Title = new JLabel("SNAKE");
		Title.setFont(new Font("Arial", Font.BOLD, 120));
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(Title);
        pane.add(Box.createVerticalGlue());
        
        JButton PlayGame = new JButton("Play Game"); 
        PlayGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        PlayGame.setPreferredSize(new Dimension(150, 40));
        PlayGame.setBackground(new Color(193, 209, 136));
        PlayGame.setFont(new Font("Arial", Font.BOLD, 36));
        PlayGame.addActionListener((event) -> 
        {
			SplashSnakeFrame.main(null);
		});
        pane.add(PlayGame);
        pane.add(Box.createVerticalGlue());
        
        JButton Characters = new JButton("Characters"); 
        Characters.setAlignmentX(Component.CENTER_ALIGNMENT);
        Characters.setPreferredSize(new Dimension(150, 40));
        Characters.setBackground(new Color(193, 209, 136));
        Characters.setFont(new Font("Arial", Font.BOLD, 30));
        pane.add(Characters);
        pane.add(Box.createVerticalGlue());
        
        JButton Utilities = new JButton("Utilities"); 
        Utilities.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utilities.setPreferredSize(new Dimension(150, 40));
        Utilities.setBackground(new Color(193, 209, 136));
        Utilities.setFont(new Font("Arial", Font.BOLD, 30));
        pane.add(Utilities);
        pane.add(Box.createVerticalGlue());
        
        JButton Credits = new JButton("Credits"); 
        Credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        Credits.setPreferredSize(new Dimension(150, 40));
        Credits.setBackground(new Color(193, 209, 136));
        Credits.setFont(new Font("Arial", Font.BOLD, 30));
        Credits.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
			{
				Credit cek = new Credit();
				cek.creditFrame.setVisible(true);
			}
		});
        pane.add(Credits);
        pane.add(Box.createVerticalGlue());
        
        JButton Exit = new JButton("Exit"); 
        Exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        Exit.setPreferredSize(new Dimension(150, 40));
        Exit.setBackground(new Color(193, 209, 136));
        Exit.setFont(new Font("Arial", Font.BOLD, 30));
        
        Exit.addActionListener((event) -> {
        	JFrame confirm = new JFrame();
        	if(JOptionPane.showConfirmDialog(confirm, "Yakin nih udahan..", "Confirm Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
        	{
        		System.exit(0);
        	}
        });
        pane.add(Exit);
        
        pane.add(Box.createVerticalGlue());
        pane.add(Box.createVerticalGlue());

        JLabel Highscore = new JLabel("Highscore: -");
        Highscore.setFont(new Font("Arial", Font.BOLD, 24));
        Highscore.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(Highscore);
        pane.add(Box.createVerticalGlue());
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e)
        	{
        		if(JOptionPane.showConfirmDialog(null, "Yakin nih udahan..", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_NO_OPTION)
            	{
            		System.exit(0);
            	}
        	}
        });

        addComponentsToPane(frame.getContentPane());
        frame.setSize(1200, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	
}