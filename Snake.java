
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 550;
    static final int SCREEN = 500;
    static final int IMAGE_SIZE = 25;
    static final int GAME_UNITS = (SCREEN * SCREEN) / (IMAGE_SIZE * IMAGE_SIZE);
    static final int DELAY = 175;
    static final int HORIZONTAL = 1;
    static final int VERTICAL = 2;
    static final int UPPERBOUND = (SCREEN/IMAGE_SIZE)-2;
    static final int UNIT = SCREEN/IMAGE_SIZE;
       
    //Arrays 
    final int snk_x[] = new int[GAME_UNITS];
    final int snk_y[] = new int[GAME_UNITS];
    final int block_x[] = new int[GAME_UNITS];
    final int block_y[] = new int[GAME_UNITS];
    
    //
    int snakeLength = 4;
    int foodEaten=0;
    int level = 1;
    int nblock = 0;
    int foodX;
    int foodY;
    int goldFoodX;
    int goldFoodY;
    char direction = 'L';
    boolean running = false;
    boolean pause = false;

    
    Timer timer;
    Random random;
    Image food;
    Image goldFood;
    Image body;
    Image headL,headR,headU,headD;
    Image tile;
    
    
    ///
    // menubar
    static JMenuBar mb;
  
    // JMenu
    static JMenu lvl,help;
  
    // CheckBoxMenu items
    static JRadioButtonMenuItem l1, l2, l3, l4, l5, l6;
    
    static JMenuItem abt,hlp;
  

    Snake() {
        
        addKeyListener(new SAdapter());
        setBackground(Color.cyan);
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        loadImage();
        startGame();
        
        addMenu();
    }
    
    public void addMenu()
    {
        // create a menubar
        mb = new JMenuBar();
  
        // create a menu
        lvl = new JMenu("Level");
        help = new JMenu("Help");
  
         l1 = new JRadioButtonMenuItem("Level 1",true); 
         l2 = new JRadioButtonMenuItem("Level 2",false);
         l3 = new JRadioButtonMenuItem("Level 3",false);
         l4 = new JRadioButtonMenuItem("Level 4",false);
         l5 = new JRadioButtonMenuItem("Level 5",false);
         l6 = new JRadioButtonMenuItem("Level 6",false);
         
         hlp = new JMenuItem("View Help");
         abt = new JMenuItem("About");
         
         
         l1.setActionCommand("levelOne");
         l2.setActionCommand("levelTwo");
         l3.setActionCommand("levelThree");
         l4.setActionCommand("levelFour");
         l5.setActionCommand("levelFive");
         l6.setActionCommand("levelSix");
        
         hlp.setActionCommand("viewHelp");
         abt.setActionCommand("about");
         
         // Group the Radio Button Menu
         
         ButtonGroup group = new ButtonGroup();
         group.add(l1);
         group.add(l2);
         group.add(l3);
         group.add(l4);
         group.add(l5);
         group.add(l6);
         
         l1.addActionListener(this);
         l2.addActionListener(this);
         l3.addActionListener(this);
         l4.addActionListener(this);
         l5.addActionListener(this);
         l6.addActionListener(this);
         
         hlp.addActionListener(this);
         abt.addActionListener(this);
         
         
        // add menu items to menu
        lvl.add(l1);
        lvl.add(l2);
        lvl.add(l3);
        lvl.add(l4);
        lvl.add(l5);
        lvl.add(l6);
  
        help.add(hlp);
        help.add(abt);
        // add menu to menu bar
        mb.add(lvl);
        mb.add(help);
    }


    public void startGame() {
        level();
        initPosSnake(5,10);
        newFood();
        newGoldFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void reset()
    {  
         direction='L';
         snakeLength=4;
         foodEaten=0;
         for(int i=0;i<=nblock;i++)
         {
          block_x[i]=0;
          block_y[i]=0;
         }
        
         nblock=0;
         initPosSnake(5,10);
         if(running == false)
         {
             running=true;
             timer.start();
         }

    }
    
    public void level(){
        
        if(level==1)
        {
            
        }
                
        else if(level==2)
        {
            plotBrick(0,0,HORIZONTAL,UNIT);
            plotBrick(0,UNIT-1,HORIZONTAL,UNIT);
        }
         
        else if(level==3)
        {
            plotBrick(3,3,HORIZONTAL,UNIT-7);
            plotBrick(3,UNIT-4,HORIZONTAL,UNIT-7);
            
        }
        
        else if(level==4)
        {
              plotBrick(3,2,HORIZONTAL,3);
              plotBrick(3,3,VERTICAL,2);

              
              plotBrick(UNIT-7,UNIT-2,HORIZONTAL,3);
              plotBrick(UNIT-5,UNIT-4,VERTICAL,2);

        }
        
        
        else if(level==5)
        {
              plotBrick(3,2,HORIZONTAL,3);
              plotBrick(3,3,VERTICAL,2);

              plotBrick(3,UNIT-2,HORIZONTAL,3);
              plotBrick(3,UNIT-4,VERTICAL,2);

              plotBrick(UNIT-7,2,HORIZONTAL,3);
              plotBrick(UNIT-5,3,VERTICAL,2);

              plotBrick(UNIT-7,UNIT-2,HORIZONTAL,3);
              plotBrick(UNIT-5,UNIT-4,VERTICAL,2);
              
        }
        
        else if(level==6)
        {
                plotBrick(0,0,HORIZONTAL,UNIT);
                plotBrick(0,UNIT-1,HORIZONTAL,UNIT);
                plotBrick(0,1,VERTICAL,6);
                plotBrick(UNIT-1,1,VERTICAL,6);
                plotBrick(0,13,VERTICAL,6);
                plotBrick(UNIT-1,13,VERTICAL,6);
              
        }
        
        
        
    }
        
    public void initPosSnake(int x, int y)
    {
        for (int i = snakeLength-1; i>=0; i--) {
            snk_x[i] = x - i;
            snk_y[i] = y;
            
        }
    }
    
    public void loadImage()
    {
        
        ImageIcon imgtile = new ImageIcon("tile.png");
        tile = imgtile.getImage();
        ImageIcon imgbody = new ImageIcon("body.png");
        body = imgbody.getImage();
        ImageIcon imgheadL = new ImageIcon("headL.png");
        headL = imgheadL.getImage();
        ImageIcon imgheadR = new ImageIcon("headR.png");
        headR = imgheadR.getImage();
        ImageIcon imgheadU = new ImageIcon("headU.png");
        headU = imgheadU.getImage();
        ImageIcon imgheadD = new ImageIcon("headD.png");
        headD = imgheadD.getImage();
        ImageIcon imgfood = new ImageIcon("apple.png");
        food = imgfood.getImage();
        ImageIcon imgGoldfood = new ImageIcon("goldApple.png");
        goldFood = imgGoldfood.getImage();
        
    }
    
    public void doDrawings(Graphics g)
    {
        if(running)
        {
        
                
            //food 
            g.drawImage(food,foodX*IMAGE_SIZE,foodY*IMAGE_SIZE,this);

            //gold food 
            g.drawImage(goldFood,goldFoodX*IMAGE_SIZE,goldFoodY*IMAGE_SIZE,this);
            
            //brick
            
            for(int k=0;k<nblock;k++)
                g.drawImage(tile,block_x[k]*IMAGE_SIZE,block_y[k]*IMAGE_SIZE,this);
            
            //head
            switch(direction)
            {
                case 'L':
                    g.drawImage(headL,snk_x[0]*IMAGE_SIZE,snk_y[0]*IMAGE_SIZE,this);
                    break;
                case 'R':
                    g.drawImage(headR,snk_x[0]*IMAGE_SIZE,snk_y[0]*IMAGE_SIZE,this);
                    break;
                case 'U':
                    g.drawImage(headU,snk_x[0]*IMAGE_SIZE,snk_y[0]*IMAGE_SIZE,this);
                    break;
                case 'D':
                    g.drawImage(headD,snk_x[0]*IMAGE_SIZE,snk_y[0]*IMAGE_SIZE,this);
                    break;
                
            }
            
            //body
            for(int i=snakeLength-1;i>0;i--)
                g.drawImage(body,snk_x[i]*IMAGE_SIZE,snk_y[i]*IMAGE_SIZE,this);
                
            
                    showScore(g);
                    
                    Toolkit.getDefaultToolkit().sync();
        }
        
        else
        {
            gameOver(g);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawings(g);

    }


    public void gameOver(Graphics g) {
        
        //Score
        g.setColor(Color.blue);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + foodEaten)) / 2, g.getFont().getSize()+50);
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 45));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    
        
    }
    
    public void showScore(Graphics g)
    {
        //Score
        
        g.drawRect(0,SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT); 
        g.setColor(Color.green);
        g.fillRect(0,SCREEN,SCREEN_WIDTH,SCREEN_HEIGHT);
        
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD,15));
        g.drawString("Score: "+ foodEaten,100,SCREEN_HEIGHT-30);
        g.drawString("Level: "+ level,SCREEN_WIDTH-200,SCREEN_HEIGHT-30);
        
    }
    
    public void newFood() {
        
       random = new Random();
       foodX = random.nextInt(UPPERBOUND)+1;
       foodY = random.nextInt(UPPERBOUND)+1; 
       
       // Check if food is placed on brick
       
       if(nblock>0)
       {
           for(int i=0;i<nblock;i++)
               if(foodX==block_x[i]&&foodY==block_y[i]){
                newFood();
               }    
       }
       
    }

    public void newGoldFood() {
        
        random = new Random();
        goldFoodX = random.nextInt(UPPERBOUND)+1;
        goldFoodY = random.nextInt(UPPERBOUND)+1; 
        
        // Check if food is placed on brick
        
        if(nblock>0)
        {
            for(int i=0;i<nblock;i++)
                if(goldFoodX==block_x[i]&&goldFoodY==block_y[i])
                    newGoldFood();
        }
        
     }

    public void checkFood() {
        if(snk_x[0]==foodX && snk_y[0]==foodY)
        {
            
            foodEaten++;
            newFood();
            snakeLength++;
            move();
        }
        if(snk_x[0]==goldFoodX && snk_y[0]==goldFoodY)
        {
            foodEaten++;
            newGoldFood();
            snakeLength++;
            move();
        }
    }

    public void isSnkHitItself()
    {
        for(int i=snakeLength-1;i>2;i--)
        {   
            if(snk_x[0]==snk_x[i]&&snk_y[0]==snk_y[i])
            {
                running = false;
            }
        }
        if(!running)
            timer.stop();
    }
  
    public void isSnakeHitBrick()
    {
       
                
        for(int i=0;i<nblock;i++)
        {
            if(block_x[i]==snk_x[0]&&block_y[i]==snk_y[0])
                running = false;
        }
            if(!running)
                timer.stop();
        
    }
    
    public void plotBrick(int start_x, int start_y,int dir, int howmany)
    {
        if(dir==HORIZONTAL)
        {
            
            for(int i=0;i<howmany;i++)
            {
                    block_x[nblock]=start_x+i;
                    block_y[nblock]=start_y;
                    nblock++;
                    
            }
        
        }
        
        else if(dir==VERTICAL)
        {
            for(int j=0;j<howmany;j++)
            {
                block_x[nblock]=start_x;
                block_y[nblock]=start_y+j;
                nblock++;
            }
        
        }
    }

    
    public void checkCollision()
    {
        if(snk_x[0]<0)
            snk_x[0]=UNIT;
        if(snk_x[0]>UNIT)
            snk_x[0]=0;
        if(snk_y[0]<0)
            snk_y[0]=UNIT;
        if(snk_y[0]>UNIT)
            snk_y[0]=0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        String s = e.getActionCommand();
        if(s!=null)
        {
            
        switch(s)
        {
            case "levelOne" :
                level=1;
                reset();
                level();
                break;
            case "levelTwo" :
                level=2;
                reset();
                level();
                break;
            case "levelThree" :
                level=3;
                reset();
                level();
                break;
            case "levelFour" :
                level=4;
                reset();
                level();
                break;
            case "levelFive" :
                level=5;
                reset();
                level();
                break;
            case "levelSix" :
                level=6;
                reset();
                level();
                break;
            case "about" :
                    JOptionPane.showMessageDialog(this,"Title : Snake Game \n Author : Manish Kumar \n Blog : www.justdocodings.blopspot.com","About",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "viewHelp" :
                    JOptionPane.showMessageDialog(this,"Use arrow keys for movement \n and space key to pause game.","Help",JOptionPane.INFORMATION_MESSAGE);
                break;
            
            
        }
    }

        if (running) {
            move();
            checkFood();
            checkCollision();
            isSnkHitItself();
            isSnakeHitBrick();
        }
        repaint();
    }
    
    

    public void move() {
        for (int i = snakeLength-1; i>0; i--) {
            snk_x[i] = snk_x[i - 1];
            snk_y[i] = snk_y[i - 1];
            
        }

        switch (direction) {
            case 'U':
                snk_y[0]--;
                break;
            case 'D':
                snk_y[0]++;
                break;
            case 'L':
                snk_x[0]--;
                break;
            case 'R':
                snk_x[0]++;
                break;
        }
        
        

    }
    

    public class SAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction!='R')
                        direction='L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!='L')
                        direction='R';
                    break;
                case KeyEvent.VK_UP:
                    if(direction!='D')
                        direction='U';
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction!='U')
                        direction='D';
                    break;
                case KeyEvent.VK_SPACE:
                    if(pause==false)
                    {
                        timer.stop();
                        pause = true;
                    }
                    else if(pause==true)
                    {
                        timer.start();
                        pause=false;
                        
                    }
                break;


            }
        }
    }
}