package snake_game;

import javax.swing.*;

public class SnakeFrame extends JFrame  {
    
	static JFrame jf;
	
    public SnakeFrame() {
        
        add(new Snake());
//    	add(new Menu());
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void closeSnakeFrame() {
//		System.exit(0);
    	MenuFrame.main(null);
	}
    
    public static void visibleOff() {
		jf.setVisible(false);
	}
    
    public static void mainSnake(){
      jf = new SnakeFrame();
      jf.setJMenuBar(Snake.mb);
      jf.setVisible(true);
    }
}
