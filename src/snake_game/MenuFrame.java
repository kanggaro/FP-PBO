package snake_game;

import java.awt.Point;

import javax.swing.*;


public class MenuFrame extends JFrame {

	static JFrame jf;
	
	public MenuFrame() {
		add(new Menu());
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void closeMenuFrame() {
		System.exit(0);
	}
	
	public static void visibleOff() {
		jf.setVisible(false);
	}
	
	public static void visibleOn() {
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		jf = new MenuFrame();
	    jf.setVisible(true);
	    
	}
}
