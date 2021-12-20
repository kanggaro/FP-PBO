package snake_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snake_game.Menu.MouseAdapter;


public class Menu extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 600;
    static final int IMAGE_SIZE = 20;
    static final int TOTAL_MENU = 5;
    static final int TOTAL_CHARA = 4;
	static final int TOTAL_UTIL = 6;
    int choose = 1;
    int chooseHorizontal;
	int chooseHorizontalUtil = 1;
    int eagleMenuX;
    int eagleMenuY;
    private int count;
    boolean enter_pressed = false;
    boolean main_menu = true;
    boolean start_menu = false;
    boolean characters_menu = false;
    boolean utilities_menu = false;
    boolean credits_menu = false;
    boolean exitMenu = false;
    boolean eagleAppear = false;
    Timer timer;
    Timer timerNewEagle;
    CountInSeconds countInSeconds;
    Image charaDisplay;
    Image charaMenuDisplay;
    Image backgroundMenu;
    Image eagleMenu;
	Image arrowLeft;
	Image arrowRight;
	Image escape;
    DrawingString drawingString = new DrawingString();
	
	public Menu() {
		addKeyListener(new SAdapter());
		addMouseListener(new MouseAdapter());
        setBackground(new Color(22,182,123));
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        loadImage();
        timer = new Timer(50, this);
        timer.start();
        countInSeconds = new CountInSeconds();
        timerNewEagle = new Timer(1000,countInSeconds);
        timerNewEagle.start();
	}
	
	public void loadImage() {
		//background
		ImageIcon imgBG = null;
		imgBG = new ImageIcon("img/menu_BG.jpg");
		backgroundMenu = imgBG.getImage();
		
		//character on menu
		ImageIcon imgSnkMenu = null;
		
		//eagle on menu
		ImageIcon imgEagleMenu = null;
		imgEagleMenu = new ImageIcon("img/eagleMenu.gif");
		eagleMenu = imgEagleMenu.getImage();
		
		//character select
		GetFile snakeSelect = new GetCharacter();
		ImageIcon imgDisplay = null;

		//arrow 
		ImageIcon arrow = null;
		arrow = new ImageIcon("img/arrowLeft.png");
		arrowLeft = arrow.getImage();
		
		//escape
		ImageIcon escImg = null;
		escImg = new ImageIcon("img/close.png");
		escape = escImg.getImage();

		arrow = new ImageIcon("img/arrowRight.png");
		arrowRight = arrow.getImage();

		chooseHorizontal = snakeSelect.getInt();
		switch(snakeSelect.getInt()) {
		case 1:
			imgDisplay = new ImageIcon("img/Snake887.png");
			imgSnkMenu = new ImageIcon("img/snakeGreenSmall.png");
			break;
		case 2:
			imgDisplay = new ImageIcon("img/snakeRed.png");
			imgSnkMenu = new ImageIcon("img/snakeRedSmall.png");
			break;
		case 3:
			imgDisplay = new ImageIcon("img/snakeYellow.png");
			imgSnkMenu = new ImageIcon("img/snakeYellowSmall.png");
			break;
		case 4:
			imgDisplay = new ImageIcon("img/snakeBlue.png");
			imgSnkMenu = new ImageIcon("img/snakeBlueSmall.png");
			break;
		}
		charaDisplay = imgDisplay.getImage();
		charaMenuDisplay = imgSnkMenu.getImage();
	}
	
	public void loadImageRun() {
		//character menu
		ImageIcon imgDisplay = null;
		switch(chooseHorizontal) {
		case 1:
			imgDisplay = new ImageIcon("img/Snake887.png");
			break;
		case 2:
			imgDisplay = new ImageIcon("img/snakeRed.png");
			break;
		case 3:
			imgDisplay = new ImageIcon("img/snakeYellow.png");
			break;
		case 4:
			imgDisplay = new ImageIcon("img/snakeBlue.png");
			break;
		}
		charaDisplay = imgDisplay.getImage();
		
		//main menu
		ImageIcon imgSnkMenu = null;
		GetFile snakeSelect = new GetCharacter();
		switch(snakeSelect.getInt()) {
		case 1:
			imgSnkMenu = new ImageIcon("img/snakeGreenSmall.png");
			break;
		case 2:
			imgSnkMenu = new ImageIcon("img/snakeRedSmall.png");
			break;
		case 3:
			imgSnkMenu = new ImageIcon("img/snakeYellowSmall.png");
			break;
		case 4:
			imgSnkMenu = new ImageIcon("img/snakeBlueSmall.png");
			break;
		}
		charaMenuDisplay = imgSnkMenu.getImage();
	}

	public void loadImageUtilitesRun() {
		//utilities menu
		ImageIcon imgDisplay = null;
		switch(chooseHorizontalUtil) {
		case 1:
			imgDisplay = new ImageIcon("img/utilitiesRedApple.png");
			break;
		case 2:
			imgDisplay = new ImageIcon("img/utilitiesGoldApple.png");
			break;
		case 3:
			imgDisplay = new ImageIcon("img/utilitiesBerry.png");
			break;
		case 4:
			imgDisplay = new ImageIcon("img/utilitiesVegetable.png");
			break;
		case 5:
			imgDisplay = new ImageIcon("img/utilitiesPoison.png");
			break;
		case 6:
			imgDisplay = new ImageIcon("img/utilitiesEagle.png");
			break;
		}
		charaDisplay = imgDisplay.getImage();
		
	}
	
	public void draw(Graphics g) {
		//draw background
		g.drawImage(backgroundMenu, 0, 0, this);
		
		//draw main menu
		if(main_menu) {
			//title
			drawingString = new DrawingStringMid(Color.BLACK, new Font("Arial", Font.BOLD, 130), "SNAKE", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*21, g);
			drawingString.draw();
			
	        //draw snake display
	        g.drawImage(charaMenuDisplay, 270, 420, this);
	        
	        g.setFont(new Font("Sans", Font.BOLD, 35));
	        
	        //for button
	        Font bottonFont = new Font("Sans", Font.BOLD, 35);
	        
			//button1
	        Color button1Color;
			if(choose==1) button1Color = new Color(211,18,18);
			else button1Color = new Color(196,206,116);
			drawingString = new DrawingStringMid(button1Color, bottonFont, "Start Game", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*17, g);
			drawingString.draw();
			
	        //button2
			Color button2Color;
			if(choose==2) button2Color = new Color(211,18,18);
			else button2Color = new Color(196,206,116);
			drawingString = new DrawingStringMid(button2Color, bottonFont, "Choose Skin", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*15, g);
			drawingString.draw();
			
	        //button3
			Color button3Color;
			if(choose==3) button3Color = new Color(211,18,18);
			else button3Color = new Color(196,206,116);
			drawingString = new DrawingStringMid(button3Color, bottonFont, "Utilities", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*13, g);
			drawingString.draw();
			
	        //button4
			Color button4Color;
			if(choose==4) button4Color = new Color(211,18,18);
			else button4Color = new Color(196,206,116);
			drawingString = new DrawingStringMid(button4Color, bottonFont, "Credits", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*11, g);
			drawingString.draw();
						
	        //button5
			Color button5Color;
			if(choose==5) button5Color = new Color(211,18,18);
			else button5Color = new Color(196,206,116);
			drawingString = new DrawingStringMid(button5Color, bottonFont, "Exit Game", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*9, g);
			drawingString.draw();
			
	        //high score
			GetFile highScore = new GetHighScore();
//			GetHighScore highScore = new GetHighScore();
			drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 40), "High Score : " + highScore.getInt(), SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE, g);
			drawingString.draw();
			
	        //draw eagle on menu
			count = countInSeconds.getCount();
	        if(count%14==0 && count!=0 && eagleAppear==true) {
	        	countInSeconds.setCount(0);
	        	eagleAppear = false;
	        }
	        else if(eagleAppear==true)
	        	g.drawImage(eagleMenu, eagleMenuX, eagleMenuY, this);
	        else if(count%7==0 && count!=0 && eagleAppear==false) {
	        	newEagleMenu();
	        	eagleAppear=true;
	        }
		}
		//draw start game
		if(start_menu) {
			
			//upper text
			drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 40), "YEAY..YOU WILL ENTER THE ARENA", SCREEN_WIDTH, SCREEN_HEIGHT/3, g);
			drawingString.draw();
			
			//middle text
			Color midColor = new Color(196,206,116);
			Font midFont = new Font("Sans", Font.BOLD, 35);
			drawingString = new DrawingStringMid(midColor, midFont, "Press SPACE to start the game", SCREEN_WIDTH, SCREEN_HEIGHT/2, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(midColor, midFont, "Press ESC to return to Main Menu", SCREEN_WIDTH, SCREEN_HEIGHT/2+IMAGE_SIZE*3, g);
			drawingString.draw();
			
			//bottom text
			Color bottomColor = Color.WHITE;
			Font bottomFont = new Font("Sans", Font.BOLD, 20);
			drawingString = new DrawingStringMid(bottomColor, bottomFont, "Short Guide", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*6, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(bottomColor, bottomFont, "> You can select the level in the top left panel in the arena", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*4, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(bottomColor, bottomFont, "> If you need help, the help button is available next to the level button", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*3, g);
			drawingString.draw();
			
		}
		//draw choose characters page
		if(characters_menu) {
			//upper text
			drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Choose Skin", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
			drawingString.draw();
			
			//display image
	        g.drawImage(charaDisplay, SCREEN_WIDTH/3+20, SCREEN_HEIGHT/3, this);

			//arrow
			g.drawImage(arrowLeft, 100, SCREEN_HEIGHT/2, this);
			g.drawImage(arrowRight, SCREEN_WIDTH-200, SCREEN_HEIGHT/2, this);
	        
	        //bottom text
	        drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 20), "Press Left or Right to find skin, Press Enter to select, Press ESC to back", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE, g);
			drawingString.draw();
	        
		}
		//draw utilities
		if(utilities_menu) {			
			//display image
	        g.drawImage(charaDisplay, (SCREEN_WIDTH/2)-125, SCREEN_HEIGHT/3, this);

			//arrow
			g.drawImage(arrowLeft, 100, SCREEN_HEIGHT/2, this);
			g.drawImage(arrowRight, SCREEN_WIDTH-200, SCREEN_HEIGHT/2, this);
	        
			//bottom text
			switch(chooseHorizontalUtil) {
				case 1:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If the snake eats the red apple, the score will increase by +10 points", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Red Apple", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				case 2:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If the snake eats the Golden Apple, the score will be doubled within a certain time.", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Golden Apple", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				case 3:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If the snake eats Raspberry, the score will increase by +30 points", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Raspberry", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				case 4:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If a snake eats vegetables, its body will be on a diet (shortened)", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Vegetables", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				case 5:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If a snake eats poison, it will move in the opposite direction", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Poison", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				case 6:
					drawingString = new DrawingStringMid(Color.WHITE, new Font("Sans", Font.BOLD, 25), "If the snake is hit by an eagle, the snake will be eaten by the eagle and the game is over", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE-40, g);
					drawingString.draw();
					drawingString = new DrawingStringMid(new Color(211,18,18), new Font("Sans", Font.BOLD, 45), "Eagle", SCREEN_WIDTH, SCREEN_HEIGHT/4, g);
					drawingString.draw();
					break;
				}
		}
		//draw credits page
		if(credits_menu) {
			//upper text
			Color topColor = new Color(211,18,18);
			Font topFont = new Font("Sans", Font.BOLD, 60);
			drawingString = new DrawingStringMid(topColor, topFont, "THANKS TO DEVELOPERS", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*22, g);
			drawingString.draw();
			
			//name text
			Color nameColor = new Color(196,206,116);
			Font nameFont = new Font("Sans", Font.BOLD, 35);
			drawingString = new DrawingStringMid(nameColor, nameFont, "Muhammad Afif Dwi Ardhiansyah / 5025201212", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*18, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(nameColor, nameFont, "Muhammad Abror Al-Qushoyyi / 5025201028", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*15, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(nameColor, nameFont, "Gaudhiwa Hendrasto / 5025201066", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*12, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(nameColor, nameFont, "Beryl / 5025201029", SCREEN_WIDTH, SCREEN_HEIGHT-IMAGE_SIZE*9, g);
			drawingString.draw();
			
		}
		//exit page
		if(exitMenu) {
			//upper text
			Color topColor = new Color(211,18,18);
			Font topFont = new Font("Sans", Font.BOLD, 60);
			drawingString = new DrawingStringMid(topColor, topFont, "Are you sure want to Exit?", SCREEN_WIDTH, SCREEN_HEIGHT/2-IMAGE_SIZE*4, g);
			drawingString.draw();
			
			//middle text
			Color midColor= Color.WHITE;
			Font midFont = new Font("Sans", Font.BOLD, 30);
			drawingString = new DrawingStringMid(midColor, midFont, "ENTER for YES", SCREEN_WIDTH, SCREEN_HEIGHT/2, g);
			drawingString.draw();
			drawingString = new DrawingStringMid(midColor, midFont, "ESC for NO", SCREEN_WIDTH, SCREEN_HEIGHT/2+IMAGE_SIZE*2, g);
			drawingString.draw();
			
		}
		
		//for escape button
		if(!main_menu) {
			g.drawImage(escape, 30, 30, this);
		}
	}
	
	public void newEagleMenu() {
		eagleMenuX = -350;
		eagleMenuY = 10;
	}
	
	public void eagleMenuMove() {
		if(eagleAppear) {			
			eagleMenuX += IMAGE_SIZE;	
		}
	}

	public void checkKeyMenu() {
		switch(choose) {
		case 1:
			start_menu=true;
			main_menu = false;
			break;
		case 2:
			characters_menu = true;
			main_menu = false;
			break;
		case 3:
			utilities_menu = true;
			main_menu = false;
			break;
		case 4:
			credits_menu = true;
			main_menu = false;
			break;
		case 5:
			exitMenu = true;
			main_menu = false;
			break;
		}
	}
	
	public void checkKeyChara() {
		new SetCharacter(chooseHorizontal);
		characters_menu = false;
		main_menu = true;
	}
	
	public void startGame() {
		SnakeFrame.mainSnake();
		MenuFrame.visibleOff();
	}
	
	public void backToMain() {
		main_menu = true;
	    start_menu = false;
	    characters_menu = false;
	    utilities_menu = false;
	    credits_menu = false;
	    exitMenu = false;
	}
	
	public void exit() {
		MenuFrame.closeMenuFrame();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        loadImageRun();
		if(utilities_menu)
		loadImageUtilitesRun();
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		eagleMenuMove();
		repaint();
//		hover();
	}
	
	public void hover() {
		Point reference = getLocationOnScreen();
		int xHover = MouseInfo.getPointerInfo().getLocation().x-reference.x;
        int yHover = MouseInfo.getPointerInfo().getLocation().y-reference.y; 
        try { //Update screen every 33 miliseconds = 25 FPS
            Thread.sleep(33);
            } catch(InterruptedException bug) {
            Thread.currentThread().interrupt();
            }
        
		if(main_menu) {
			if(xHover > 512 && xHover < 691 && yHover > 200 && yHover < 250) {
				choose = 1;
			}
			else if (xHover > 450 && xHover < 700 && yHover > 250 && yHover < 300) {
				choose = 2;
			}
			else if (xHover > 450 && xHover < 700 && yHover > 300 && yHover < 350) {
				choose = 3;
			}
			else if (xHover > 450 && xHover < 700 && yHover > 350 && yHover < 400) {
				choose = 4;
			}
			else if (xHover > 450 && xHover < 700 && yHover > 400 && yHover < 480) {
				choose = 5;
			}
		}
	}
	
	public class MouseAdapter implements MouseListener{

		public void MouseAdapter() {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
				
			int x = e.getX();
			int y = e.getY();
			//button on menu
			if(main_menu) {
				if(x > 512 && x < 691 && y > 200 && y < 260) {
					choose = 1;
					checkKeyMenu();
				}
				else if (x > 450 && x < 700 && y > 260 && y < 310) {
					choose = 2;
					checkKeyMenu();
				}
				else if (x > 450 && x < 700 && y > 310 && y < 350) {
					choose = 3;
					checkKeyMenu();
				}
				else if (x > 450 && x < 700 && y > 350 && y < 390) {
					choose = 4;
					checkKeyMenu();
				}
				else if (x > 450 && x < 700 && y > 390 && y < 480) {
					choose = 5;
					checkKeyMenu();
				}
			}
			//button on exit page
			if(exitMenu) {
				if(x > 490 && x < 710 && y > 270 && y < 310) {
					exit();
				}
				else if(x > 490 && x < 710 && y > 310 && y < 350) {
					backToMain();
				}
			}
			//escape button
			if(!main_menu) {
				if(x>20 && x<114 && y>20 && y <114)
					backToMain();
			}
			//arrow button
			if(characters_menu || utilities_menu) {
				//left
				if(x>100 && x<170 && y>SCREEN_HEIGHT/2 && y<SCREEN_HEIGHT/2+70) {
					if(characters_menu) {
						if(chooseHorizontal==1) chooseHorizontal = TOTAL_CHARA;
						else chooseHorizontal--;
					}
					else if(utilities_menu) {
						if(chooseHorizontalUtil==1) chooseHorizontalUtil = TOTAL_UTIL;
						else chooseHorizontalUtil--;
					}
				}
				else if(x>SCREEN_WIDTH-200 && x<SCREEN_WIDTH-200+70 && y>SCREEN_HEIGHT/2 && y<SCREEN_HEIGHT/2+70) {
					if(characters_menu) {
						if(chooseHorizontal==TOTAL_CHARA) chooseHorizontal = 1;
						else chooseHorizontal++;
					}
					else if(utilities_menu) {
						if(chooseHorizontalUtil==TOTAL_UTIL) chooseHorizontalUtil = 1;
						else chooseHorizontalUtil++;
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		
	}
	
	public class SAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(choose==1) choose = TOTAL_MENU;
				else choose-=1;
				break;
			case KeyEvent.VK_DOWN:
				if(choose==TOTAL_MENU) choose = 1;
				else choose+=1;
				break;
			case KeyEvent.VK_RIGHT:
				if(characters_menu){
					if(chooseHorizontal==TOTAL_CHARA) chooseHorizontal = 1;
					else chooseHorizontal+=1;
				}
				else if(utilities_menu){
					if(chooseHorizontalUtil==TOTAL_UTIL) chooseHorizontalUtil = 1;
					else chooseHorizontalUtil+=1;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(characters_menu){
					if(chooseHorizontal==1) chooseHorizontal = TOTAL_CHARA;
					else chooseHorizontal-=1;
				}
				else if(utilities_menu){
					if(chooseHorizontalUtil==1) chooseHorizontalUtil = TOTAL_UTIL;
					else chooseHorizontalUtil-=1;
				}
				break;
			case KeyEvent.VK_ENTER:
				if(main_menu) checkKeyMenu();
				else if(characters_menu) checkKeyChara();
				else if(exitMenu) exit();
				break;
			case KeyEvent.VK_ESCAPE:
				backToMain();
				break;
			case KeyEvent.VK_SPACE:
				if(start_menu) {
					startGame();
				}
			}
		}	
	}
	
//	public class CountInSeconds implements ActionListener {
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
//		public int resetCount() {
//			return 0;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			count++;		
//		}		
//	}
}
