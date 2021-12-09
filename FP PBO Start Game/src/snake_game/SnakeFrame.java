package snake_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    
    public static void mainSnake() throws FileNotFoundException {
      JFrame jf = new SnakeFrame();
      jf.setJMenuBar(Snake.mb);
      jf.setVisible(true);
    }
}
