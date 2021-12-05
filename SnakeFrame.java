//import java.awt.EventQueue;
import javax.swing.*;

public class SnakeFrame extends JFrame  {
    
    public SnakeFrame() {
        
        add(new Snake());
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
      JFrame jf = new SnakeFrame();
      jf.setJMenuBar(Snake.mb);
      jf.setVisible(true);      
      // HighScore highscore = new HighScore();
      // System.out.println(highscore.getHighScore());
      // highscore.closeFile();
    }
}