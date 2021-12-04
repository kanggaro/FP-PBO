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
        
        
        
        
      //EventQueue.invokeLater(new Runnable() {
      //@Override
      //public void run() {
         JFrame jf = new SnakeFrame();
         jf.setJMenuBar(Snake.mb);
                       
            jf.setVisible(true);
      //}
    //});
        
    }
}