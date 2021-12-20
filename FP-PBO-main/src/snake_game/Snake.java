package snake_game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN = 1200;
    static final int IMAGE_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / IMAGE_SIZE;
    static final int DELAY = 50;
    static final int HORIZONTAL = 1;
    static final int VERTICAL = 2;
    static final int UPPERBOUND = (SCREEN_WIDTH/IMAGE_SIZE)-2;
    static final int UNIT = SCREEN/IMAGE_SIZE;
    static final int UNIT_WIDTH = (SCREEN_WIDTH/IMAGE_SIZE) - 1;
    static final int UNIT_HEIGHT = (SCREEN_HEIGHT/IMAGE_SIZE) - 2;

    static final int EAGLE_DELAY = 7;
    static final int CABBAGE_DELAY = 15;
    static final int CABBAGE_DURATION = 4;
    static final int POISON_DELAY = 7;
    static final int POISON_DURATION = 3;
    static final int BERRY_APPLE_EATEN = 5;
       
    //Arrays 
    final int snk_x[] = new int[GAME_UNITS];
    final int snk_y[] = new int[GAME_UNITS];
    final int block_x[] = new int[GAME_UNITS];
    final int block_y[] = new int[GAME_UNITS];
    
    //
    int snakeLength = 4;
    int score=0;
    int appleEaten=0;
    int level = 1;
    int nblock = 0;
    int foodX;
    int foodY;
    int goldFoodX;
    int goldFoodY;
    int goldFoodDuration=5;
    int goldFoodCD = 10;
    private int count;
    int highScore;
    char direction = 'R';
    boolean newHighScore=false;

    int eagleX = -1;
	int eagleY = -1;
	int eagleReadyDuration = 2;
	char eagleDirection = 'N';
	boolean eagleFly = false;
	boolean eagleReady = false;

    int cabbageX;
	int cabbageY;
	int cabbageDuration = CABBAGE_DURATION;
    boolean cabbageAppear = false;
	boolean cabbageEaten = false;

    int poisonX;
    int poisonY;
    int poisonDuration = POISON_DURATION;
    boolean poisonAppear = false;
    boolean poisonEaten = false;

    int berryX;
    int berryY;
    int berryTime=1;
    boolean berryAppear = false;
    boolean berryEaten = false;
    boolean berryOk = false;

    boolean running = false;
    boolean pause = false;
    boolean goldAppleAppear = false;
    boolean goldAppleEaten = false;
    boolean pauseAppear = false;

    Timer timer;
    Random random;
    Image food;
    Image goldFood;
    Image body;
    Image headL,headR,headU,headD;
    Image tile;
    Image eagleReadyImg;
    Image eagleFlyImgU, eagleFlyImgD, eagleFlyImgL, eagleFlyImgR;
    Image cabbageImg;
    Image poisonImg;
    Image berryImg;
    Image backgroundArena;
    CountInSeconds countInSeconds;
    Timer timerSeconds;
    GetFile getHighScore = new GetHighScore();
    DrawingString drawingString = new DrawingString();
    
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
//        setBackground(Color.cyan);
        setBackground(new Color(196,206,116));
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT+7));
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
        initPosSnake(1,1);
        newFood();
        // newGoldFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

        countInSeconds = new CountInSeconds();
        timerSeconds = new Timer (1000, countInSeconds);
		timerSeconds.start();
		
		highScore = getHighScore.getInt();
    }
    
    public void reset()
    {  
         direction='R';
         snakeLength=4;
         score=0;
         count = 0;
         for(int i=0;i<=nblock;i++)
         {
          block_x[i]=0;
          block_y[i]=0;
         }
        
         nblock=0;
         initPosSnake(5,5);
         timerSeconds.restart();
         initItem();
         
         if(running == false)
         {
             running=true;
             timer.start();
             timerSeconds.restart();
         }
    }
    
    public void initItem() {
    	count = 0;
    	goldFoodX = -1;
        goldFoodY = -1;
        goldFoodDuration=5;
        goldFoodCD = 10;
        highScore = getHighScore.getInt();
        direction = 'R';
        newHighScore=false;
        eagleX = -1;
    	eagleY = -1;
    	eagleReadyDuration = 2;
    	eagleDirection = 'N';
    	eagleFly = false;
    	eagleReady = false;
        cabbageX = -1;
    	cabbageY = -1;
    	cabbageDuration = CABBAGE_DURATION;
        cabbageAppear = false;
    	cabbageEaten = false;
        poisonX = -1;
        poisonY = -1;
        poisonDuration = POISON_DURATION;
        poisonAppear = false;
        poisonEaten = false;
        berryX = -1;
        berryY = -1;
        berryTime=1;
        berryAppear = false;
        berryEaten = false;
        berryOk = false;
        running = false;
        pause = false;
        goldAppleAppear = false;
        goldAppleEaten = false;
    }
    
    public void level(){
        
        if(level==1)
        {
            
        }
                
        else if(level==2)
        {
            plotBrick(0,0,HORIZONTAL, UNIT_WIDTH+1);
            plotBrick(0,UNIT_HEIGHT,HORIZONTAL, UNIT_WIDTH+1);
        }
         
        else if(level==3)
        {
            plotBrick(3,3,HORIZONTAL,UNIT_WIDTH-6);
            plotBrick(3,UNIT_HEIGHT-4,HORIZONTAL,UNIT_WIDTH-6);
        }
        
        else if(level==4)
        {
              plotBrick(3,2,HORIZONTAL,3);
              plotBrick(3,3,VERTICAL,2);
           
              plotBrick(UNIT_WIDTH-7,UNIT_HEIGHT-2,HORIZONTAL,2);
              plotBrick(UNIT_WIDTH-5,UNIT_HEIGHT-4,VERTICAL,3);
        }

        else if(level==5)
        {
              plotBrick(3,2,HORIZONTAL,3);
              plotBrick(3,3,VERTICAL,2);

              plotBrick(3,UNIT_HEIGHT-2,HORIZONTAL,3);
              plotBrick(3,UNIT_HEIGHT-4,VERTICAL,2);

              plotBrick(UNIT_WIDTH-7,2,HORIZONTAL,3);
              plotBrick(UNIT_WIDTH-5,3,VERTICAL,2);

              plotBrick(UNIT_WIDTH-7,UNIT_HEIGHT-2,HORIZONTAL,3);
              plotBrick(UNIT_WIDTH-5,UNIT_HEIGHT-4,VERTICAL,2);   
        }
        
        else if(level==6)
        {
                plotBrick(0,0,HORIZONTAL,UNIT_WIDTH+1);
                plotBrick(0,UNIT_HEIGHT,HORIZONTAL,UNIT_WIDTH+1);
                plotBrick(0,1,VERTICAL,6);
                plotBrick(UNIT_WIDTH,1,VERTICAL,6);
                plotBrick(0,UNIT_HEIGHT-6,VERTICAL,6);
                plotBrick(UNIT_WIDTH, UNIT_HEIGHT-6,VERTICAL,6);
              
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
        ImageIcon imgtile = new ImageIcon("img/tile.png");
        tile = imgtile.getImage();
        
        GetFile snakeSelect = new GetCharacter();
        ImageIcon imgbody = null;
        ImageIcon imgheadL = null;
        ImageIcon imgheadR = null;
        ImageIcon imgheadU = null;
        ImageIcon imgheadD = null;
        switch(snakeSelect.getInt()) {
        case 1:
        	imgbody = new ImageIcon("img/greenSnake/body.png");
        	imgheadL = new ImageIcon("img/greenSnake/headL.png");
        	imgheadR = new ImageIcon("img/greenSnake/headR.png");
        	imgheadU = new ImageIcon("img/greenSnake/headU.png");
        	imgheadD = new ImageIcon("img/greenSnake/headD.png");
        	break;
        case 2:
        	imgbody = new ImageIcon("img/redSnake/bodyRed.png");
        	imgheadL = new ImageIcon("img/redSnake/headRedL.png");
        	imgheadR = new ImageIcon("img/redSnake/headRedR.png");
        	imgheadU = new ImageIcon("img/redSnake/headRedU.png");
        	imgheadD = new ImageIcon("img/redSnake/headRedD.png");
        	break;
        case 3:
        	imgbody = new ImageIcon("img/yellowSnake/bodyYellow.png");
        	imgheadL = new ImageIcon("img/yellowSnake/headYellowL.png");
        	imgheadR = new ImageIcon("img/yellowSnake/headYellowR.png");
        	imgheadU = new ImageIcon("img/yellowSnake/headYellowU.png");
        	imgheadD = new ImageIcon("img/yellowSnake/headYellowD.png");
        	break;
        case 4:
        	imgbody = new ImageIcon("img/blueSnake/bodyBlue.png");
        	imgheadL = new ImageIcon("img/blueSnake/headBlueL.png");
        	imgheadR = new ImageIcon("img/blueSnake/headBlueR.png");
        	imgheadU = new ImageIcon("img/blueSnake/headBlueU.png");
        	imgheadD = new ImageIcon("img/blueSnake/headBlueD.png");
        	break;
        }
        body = imgbody.getImage();
        headL = imgheadL.getImage();
        headR = imgheadR.getImage();
        headU = imgheadU.getImage();
        headD = imgheadD.getImage();
        ImageIcon imgfood = new ImageIcon("img/apple.png");
        food = imgfood.getImage();
        ImageIcon imgGoldfood = new ImageIcon("img/goldApple.png");
        goldFood = imgGoldfood.getImage();
        ImageIcon imgEagleReadyImg = new ImageIcon("img/eagleReadyImg.png");
        eagleReadyImg = imgEagleReadyImg.getImage();
        ImageIcon imgEagleFlyImgU = new ImageIcon("img/eagleFlyImgU.png");
        eagleFlyImgU = imgEagleFlyImgU.getImage();
        ImageIcon imgEagleFlyImgD = new ImageIcon("img/eagleFlyImgD.png");
        eagleFlyImgD = imgEagleFlyImgD.getImage();
        ImageIcon imgEagleFlyImgR = new ImageIcon("img/eagleFlyImgR.png");
        eagleFlyImgR = imgEagleFlyImgR.getImage();
        ImageIcon imgEagleFlyImgL = new ImageIcon("img/eagleFlyImgL.png");
        eagleFlyImgL = imgEagleFlyImgL.getImage();
        ImageIcon imgCabbageImg = new ImageIcon("img/cabbage.png");
        cabbageImg = imgCabbageImg.getImage();
        ImageIcon imgPoisonImg = new ImageIcon("img/poisonImg.png");
        poisonImg = imgPoisonImg.getImage();
        ImageIcon imgBerryImg = new ImageIcon("img/berryImg.png");
        berryImg = imgBerryImg.getImage();
        
        //bg arena
        ImageIcon imgBG = null;
        imgBG = new ImageIcon("img/arenaBackground2.jpg");
        backgroundArena = imgBG.getImage();
    }
    
    public void doDrawings(Graphics g)
    {

    	//background
//    	g.drawImage(backgroundArena, 0, 0, this);

        if(running)
        {
            count = countInSeconds.getCount();
            
            if(pause){
                drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "PAUSE", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
		        drawingString.draw();
                pauseAppear = true;
            }

            //apple biasa aja 
            g.drawImage(food,foodX*IMAGE_SIZE,foodY*IMAGE_SIZE,this);

            //gold apple
            if((goldAppleEaten==false && count%(goldFoodCD*2)==0 && count!=0)  
                || (goldAppleEaten==true && count==goldFoodDuration)){
				goldAppleEaten = false;
				goldAppleAppear = false;
				goldFoodDuration = 5;
			}
			else if(goldAppleEaten == true) {
				goldAppleAppear = false;
                g.setColor(Color.YELLOW);
				g.fillOval(snk_x[0]*IMAGE_SIZE, snk_y[0]*IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
			}
            else if(goldAppleAppear == true){
                g.drawImage(goldFood,goldFoodX*IMAGE_SIZE,goldFoodY*IMAGE_SIZE,this);
            }
			else if(count%goldFoodCD==0 && count!=0 && goldAppleAppear==false) {
				newGoldFood();
				goldAppleAppear = true;
			}  

            //sayur
            if(count==cabbageDuration && count!=0) {
				cabbageEaten = false;
				cabbageAppear = false;
				newCabbage();
				cabbageDuration=CABBAGE_DURATION;
			}
			else if(cabbageEaten == true) {
				cabbageAppear = false;
			}
			else if(cabbageAppear==true) {
                g.drawImage(cabbageImg, cabbageX, cabbageY, this);
			}
			else if(count%CABBAGE_DELAY==0 && count!=0 && cabbageAppear==false) {
				cabbageAppear = true;
				cabbageDuration+=count;
			} 

            //poison
            if((poisonEaten==false && count%(POISON_DELAY*2)==0 && count!=0)  
                || (poisonEaten==true && count==poisonDuration)){
				poisonEaten = false;
				poisonAppear = false;
				poisonDuration = POISON_DURATION;
			}
			else if(poisonEaten == true) {
				poisonAppear = false;
                g.setColor(Color.GREEN);
				g.fillOval(snk_x[0]*IMAGE_SIZE, snk_y[0]*IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
			}
            else if(poisonAppear == true){
                g.drawImage(poisonImg, poisonX, poisonY, this);
            }
			else if(count%POISON_DELAY==0 && count!=0 && poisonAppear==false) {
				newPoison();
				poisonAppear = true;
			}

            //berry
            if(berryEaten==true){
                berryAppear=false;
                berryEaten=false;
                newBerry();
            }
            else if(berryAppear==true){
                g.drawImage(berryImg, berryX, berryY, this);
            }
            else if(appleEaten%BERRY_APPLE_EATEN==0 && appleEaten!=0 && berryEaten==false && berryOk==false){
                if(newChance(6,10)){
                    berryAppear=true;
                    berryOk=true;
                    newBerry();
                }  
                else{
                    berryTime+=appleEaten;
                    berryOk=true;
                }               
            }
            else if(appleEaten==berryTime){
                berryTime=1;
                berryOk=false;
            }
            
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
            for(int i=1;i<snakeLength;i++)
                g.drawImage(body,snk_x[i]*IMAGE_SIZE,snk_y[i]*IMAGE_SIZE,this);

            //eagle
            if(eagleFly == false && count%EAGLE_DELAY==0 &&count!=0 && eagleReady == false) {
				newEagle();
				eagleReady = true;
				eagleReadyDuration+=count;
			}
			else if(count==eagleReadyDuration && eagleReady == true) {
				eagleFly=true;
				eagleReady = false;
				eagleReadyDuration = 1;
			}
            if(eagleReady==true){
                g.drawImage(eagleReadyImg, eagleX, eagleY,this);
            }
			else if(eagleFly==true) {
                if(eagleDirection=='U')
				    g.drawImage(eagleFlyImgU, eagleX, eagleY,this);
                else if(eagleDirection=='D')
                    g.drawImage(eagleFlyImgD, eagleX, eagleY,this);
                else if(eagleDirection=='R')
                    g.drawImage(eagleFlyImgR, eagleX, eagleY,this);
                else if(eagleDirection=='L')
                    g.drawImage(eagleFlyImgL, eagleX, eagleY,this);
			}
            

            showScore(g);
                    
            Toolkit.getDefaultToolkit().sync();
        }
        
        else
        {
            gameOver(g);
        }
    }

    // public void drawPause(){
    //     Graphics g;
    //     drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "PAUSE", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
	// 	drawingString.draw();
    // } 
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawings(g);
    }
    
    //display game over
    public void gameOver(Graphics g) {
        
    	//Game Over text
    	Color topBottomColor = new Color(139, 23, 23);
    	Font topFont = new Font("Arial", Font.BOLD, 80);
    	drawingString = new DrawingStringMid(topBottomColor, topFont, "GAME OVER", SCREEN_WIDTH, SCREEN_HEIGHT/2-IMAGE_SIZE*8, g);
		drawingString.draw();
        
        //Result text
		drawingString = new DrawingStringMid(Color.BLACK, new Font("Arial", Font.BOLD, 30),
				"Apple Eaten: " + appleEaten + "     Score: " + score + "     Snake Length: " + snakeLength, SCREEN_WIDTH, SCREEN_HEIGHT/2-IMAGE_SIZE, g);
		drawingString.draw();
		
		//High score text
		drawingString = new DrawingStringMid(Color.BLACK, new Font("Arial", Font.BOLD, 30), "High Score: " + highScore, SCREEN_WIDTH, SCREEN_HEIGHT/2+IMAGE_SIZE, g);
		drawingString.draw();
        
        //tips text
		drawingString = new DrawingStringMid(topBottomColor, new Font("Arial", Font.BOLD, 18), "Press ESC to close", SCREEN_WIDTH, SCREEN_HEIGHT/2+IMAGE_SIZE*8, g);
		drawingString.draw();
		drawingString = new DrawingStringMid(topBottomColor, new Font("Arial", Font.BOLD, 18), "Press SPACE to play again", SCREEN_WIDTH, SCREEN_HEIGHT/2+IMAGE_SIZE*9, g);
		drawingString.draw();
		
        //if new high score
       if(newHighScore==true) {
    	   drawingString = new DrawingStringSpecific(Color.RED, new Font("Ink Free", Font.BOLD, 30), "NEW", 420,  SCREEN_HEIGHT/2+IMAGE_SIZE, g);
   			drawingString.draw();
        }
        new SetHighScore(highScore);
    }
    
    public void showScore(Graphics g)
    {
        //Bottom text Score        
    	Color scoreColor = Color.RED;
    	Font scoreFont = new Font("Ink Free", Font.BOLD,15);
    	drawingString = new DrawingStringSpecific(scoreColor, scoreFont, "Level: "+ level, 300, SCREEN_HEIGHT-30, g);
		drawingString.draw();
		drawingString = new DrawingStringMid(scoreColor, scoreFont, "Score: "+ score, SCREEN_WIDTH, SCREEN_HEIGHT-30, g);
		drawingString.draw();
		drawingString = new DrawingStringSpecific(scoreColor, scoreFont, "High Score: "+ highScore, SCREEN_WIDTH-350, SCREEN_HEIGHT-30, g);
		drawingString.draw();
        
    }
    
    public void newFood() {
       random = new Random();
       foodX = random.nextInt(UPPERBOUND)+1;
       foodY = random.nextInt(UNIT_HEIGHT)+1; 
       
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
        goldFoodY = random.nextInt(UNIT_HEIGHT)+1; 
        
        // Check if food is placed on brick
        if(nblock>0)
        {
            for(int i=0;i<nblock;i++)
                if(goldFoodX==block_x[i]&&goldFoodY==block_y[i])
                    newGoldFood();
        }
     }

     public void newCabbage() {
        random = new Random();
		cabbageX = random.nextInt((int)(SCREEN_WIDTH/IMAGE_SIZE))*IMAGE_SIZE;
		cabbageY = random.nextInt((int)(SCREEN_HEIGHT/IMAGE_SIZE-1))*IMAGE_SIZE;

        // Check if food is placed on brick
        if(nblock>0)
        {
            for(int i=0;i<nblock;i++)
                if(cabbageX==block_x[i]&&cabbageY==block_y[i])
                    newCabbage();
        }
	}

    public void newPoison() {
        random = new Random();
		poisonX = random.nextInt((int)(SCREEN_WIDTH/IMAGE_SIZE))*IMAGE_SIZE;
		poisonY = random.nextInt((int)(SCREEN_HEIGHT/IMAGE_SIZE-1))*IMAGE_SIZE;

        // Check if food is placed on brick
        if(nblock>0)
        {
            for(int i=0;i<nblock;i++)
                if(poisonX==block_x[i]&&poisonY==block_y[i])
                    newPoison();
        }
	}

    public void newBerry() {
        random = new Random();
		berryX = random.nextInt((int)(SCREEN_WIDTH/IMAGE_SIZE))*IMAGE_SIZE;
		berryY = random.nextInt((int)(SCREEN_HEIGHT/IMAGE_SIZE-1))*IMAGE_SIZE;

        // Check if food is placed on brick
        if(nblock>0)
        {
            for(int i=0;i<nblock;i++)
                if(berryX==block_x[i]&&berryY==block_y[i])
                    newBerry();
        }
	}

    public void newEagle() {
		//eagle from horizontal wall
		if(newChance(1,2)) {
			eagleX = random.nextInt((int)(SCREEN_WIDTH/IMAGE_SIZE))*IMAGE_SIZE;
			//eagle from top wall
			if(newChance(1,2)) {
				eagleY = 0;
				eagleDirection = 'D';
			}
			//eagle from bottom wall
			else {
				eagleY = UNIT_HEIGHT*IMAGE_SIZE-1;
				eagleDirection = 'U';
			}
		}
		//eagle from vertical wall
		else {
			eagleY = random.nextInt((int)(SCREEN_HEIGHT/IMAGE_SIZE))*IMAGE_SIZE;
			//eagle from left wall
			if(newChance(1,2)) {
				eagleX = 0;
				eagleDirection = 'R';
			}
			//eagle from right wall
			else {
				eagleX = UNIT_WIDTH*IMAGE_SIZE-1;
				eagleDirection = 'L';
			}
		}
	}

    public boolean newChance(int n, int s) {
		int temp = random.nextInt((s-1)+1)+1;
		if(temp <= n) return true;
		else return false;
	}

    public void checkFood() {
        //eat apel biasa aja
        if(snk_x[0]==foodX && snk_y[0]==foodY)
        {
            if(goldAppleEaten==true){
                score+=20;
                appleEaten+=1;
            }
            else{
                score+=10;
                appleEaten+=1;
            } 
            newFood();
            snakeLength++;
        }
        //eat apel gold
        if(snk_x[0]==goldFoodX && snk_y[0]==goldFoodY && goldAppleAppear==true)
        {
            goldAppleEaten = true;
            goldFoodDuration+=count;
            goldFoodX=-1;
            goldFoodY=-1;
        }
        //eat sayur
        if(snk_x[0]==cabbageX/IMAGE_SIZE && snk_y[0]==cabbageY/IMAGE_SIZE && cabbageAppear==true)
        {
            cabbageEaten = true;
            snakeLength-=snakeLength/3;
            cabbageX=-1;
            cabbageY=-1;
        }
        //eat poison
        if(snk_x[0]==poisonX/IMAGE_SIZE && snk_y[0]==poisonY/IMAGE_SIZE && poisonAppear==true)
        {
            poisonDuration+=count;
            poisonEaten = true;
            poisonX=-1;
            poisonY=-1;
        }
        //eat berry
        if(snk_x[0]==berryX/IMAGE_SIZE && snk_y[0]==berryY/IMAGE_SIZE && berryAppear==true)
        {
            berryEaten = true;
            score+=30;
            berryX=-1;
            berryY=-1;
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
        if(!running) {
        	timer.stop();
        	timerSeconds.stop();
        	count = 0;
        }
    }

    public void isEagleHitSnake()
    {
        if(eagleFly){
            for(int i=snakeLength;i>0;i--)
            {   
              if(snk_x[i]==eagleX/IMAGE_SIZE && snk_y[i]==eagleY/IMAGE_SIZE)
              {
                 running = false;
             }
            }
        }
        
        if(!running) {
        	timer.stop();
        	timerSeconds.stop();
        	count = 0;
        }
                
    }
  
    public void isSnakeHitBrick()
    {       
        for(int i=0;i<nblock;i++)
        {
            if(block_x[i]==snk_x[0]&&block_y[i]==snk_y[0])
                running = false;
        }
            if(!running) {
            	timer.stop();
            	timerSeconds.stop();
            	count = 0;
            }
                
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
            running = false;
            // snk_x[0]=UNIT_WIDTH;
        if(snk_x[0]>UNIT_WIDTH)
            running = false;
            // snk_x[0]=0;
        if(snk_y[0]<0)
            running = false;
            // snk_y[0]=UNIT_HEIGHT;
        if(snk_y[0]>UNIT_HEIGHT)
            running = false;
            // snk_y[0]=0;
    }
    
    public void checkHighScore() {
    	if(highScore<score) {
    		highScore = score;
    		newHighScore = true;
    	}
    }
    
    public void closeWindow() {
//    	MenuFrame.main(null);
    	MenuFrame.visibleOn();
    	SnakeFrame.visibleOff();
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
                    JOptionPane.showMessageDialog(this,"Title : Snake Game \n Reference of Author : Manish Kumar \n Blog : www.justdocodings.blopspot.com","About",
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
            isEagleHitSnake();
            eagleMove();
            checkHighScore();
            if(pauseAppear){
                timer.stop();
                timerSeconds.stop();
            }
        }
        repaint();
    }

    public void move() {
        for (int i = snakeLength; i>0; i--) {
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

    public void eagleMove() {
		if(eagleFly) {			
			switch(eagleDirection) {
			case 'U':
				eagleY = eagleY - IMAGE_SIZE;
				if(eagleY<0) eagleFly = false;
				break;
			case 'D':
				eagleY = eagleY + IMAGE_SIZE;
				if(eagleY>SCREEN_HEIGHT) eagleFly = false;
				break;
			case 'R':
				eagleX = eagleX + IMAGE_SIZE;
				if(eagleX>SCREEN_WIDTH) eagleFly = false;
				break;
			case 'L':
				eagleX = eagleX - IMAGE_SIZE;
				if(eagleX<0) eagleFly = false;
				break;
			}	
		}
	}
    
    public boolean isGameOver() {
    	if(running) return false;
    	else return true;
    }

    public class SAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(!poisonEaten){
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
                        pause = true;
                        // timer.stop();
                        // timerSeconds.stop();
                    }
                    else if(pause==true)
                    {
                        timer.start();
                        pauseAppear=false;
                        pause=false;
                        timerSeconds.stop(); 
                    }
                    if(!running) reset();

                break;
                
                case KeyEvent.VK_ESCAPE:
                	if(!running) closeWindow();
                }
            }
            //eat poison
            else{
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(direction!='L')
                           direction='R';
                       break;
                    case KeyEvent.VK_RIGHT:
                        if(direction!='R')
                            direction='L';
                        break;
                    case KeyEvent.VK_UP:
                        if(direction!='U')
                            direction='D';
                        break;
                    case KeyEvent.VK_DOWN:
                        if(direction!='D')
                            direction='U';
                        break;
                    case KeyEvent.VK_SPACE:
                        if(pause==false)
                       {
                           timer.stop();
                           pause = true;
                           timerSeconds.stop();
                        }
                        else if(pause==true)
                        {
                            timer.start();
                            pause=false;
                            timerSeconds.stop();

                        }
                    break;
                }
            }
            
        }
    }

//    public class CountInSeconds implements ActionListener {
//		private int count;
//		
//		CountInSeconds(){
//			count = 0;
//		}
//
//		public int getCount() {
//			return count;
//		}
//
//		public void setCount(int count) {
//			this.count = count;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if(running) {
//				count++;
//			}		
//		}		
//	}
}
