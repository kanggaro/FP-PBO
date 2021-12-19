package snake_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snake_game.Snake.SAdapter;

public class Menu extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 600;
    static final int IMAGE_SIZE = 20;
    static final int TOTAL_MENU = 5;
    static final int TOTAL_CHARA = 4;
    int choose = 1;
    int chooseHorizontal;
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
	
	public Menu() {
		addKeyListener(new SAdapter());
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
		GetCharacter snakeSelect = new GetCharacter();
		ImageIcon imgDisplay = null;
		chooseHorizontal = snakeSelect.getSelectedCharacter();
		switch(snakeSelect.getSelectedCharacter()) {
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
		GetCharacter snakeSelect = new GetCharacter();
		switch(snakeSelect.getSelectedCharacter()) {
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
	
	public void draw(Graphics g) {
		//draw background
		g.drawImage(backgroundMenu, 0, 0, this);
		
		//draw main menu
		if(main_menu) {
			//title
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 130));
	        FontMetrics metrics00 = getFontMetrics(g.getFont());
	        g.drawString("SNAKE", (SCREEN_WIDTH - metrics00.stringWidth("SNAKE")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*21);
			
	        //draw snake display
	        g.drawImage(charaMenuDisplay, 270, 420, this);
	        
	        g.setFont(new Font("Sans", Font.BOLD, 35));
	        FontMetrics metrics01 = getFontMetrics(g.getFont());
	        
			//button1
			if(choose==1) g.setColor(new Color(211,18,18));
			else g.setColor(new Color(196,206,116));
	        g.drawString("Start Game", (SCREEN_WIDTH - metrics01.stringWidth("Start Game")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*17);
			
	        //button2
	        if(choose==2) g.setColor(new Color(211,18,18));
	        else g.setColor(new Color(196,206,116));
	        g.drawString("Choose Skin", (SCREEN_WIDTH - metrics01.stringWidth("Choose Skin")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*15);
			
	        //button3
	        if(choose==3) g.setColor(new Color(211,18,18));
	        else g.setColor(new Color(196,206,116));
	        g.drawString("Utilities", (SCREEN_WIDTH - metrics01.stringWidth("Utilities")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*13);
			
	        //button4
	        if(choose==4) g.setColor(new Color(211,18,18));
	        else g.setColor(new Color(196,206,116));
	        g.drawString("Credits", (SCREEN_WIDTH - metrics01.stringWidth("Credits")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*11);
			
	        //button5
	        if(choose==5) g.setColor(new Color(211,18,18));
	        else g.setColor(new Color(196,206,116));
	        g.drawString("Exit Game", (SCREEN_WIDTH - metrics01.stringWidth("Exit Game")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*9);
	        
	        //high score
			GetHighScore highScore = new GetHighScore();
			g.setColor(Color.WHITE);
	        g.setFont(new Font("Sans", Font.BOLD, 40));
	        FontMetrics metrics06 = getFontMetrics(g.getFont());
	        g.drawString("High Score : " + highScore.getHighScore(), (SCREEN_WIDTH - metrics06.stringWidth("High Score : " + highScore.getHighScore())) / 2, SCREEN_HEIGHT-IMAGE_SIZE);
	        
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
			g.setColor(new Color(211,18,18));
	        g.setFont(new Font("Sans", Font.BOLD, 40));
	        FontMetrics metrics1 = getFontMetrics(g.getFont());
	        g.drawString("YEAY..KAMU AKAN MASUK ARENA", 
	        		(SCREEN_WIDTH - metrics1.stringWidth("YEAY..KAMU AKAN MASUK ARENA")) / 2, SCREEN_HEIGHT/3);
	        
	        g.setColor(new Color(196,206,116));
	        g.setFont(new Font("Sans", Font.BOLD, 35));
	        FontMetrics metrics11 = getFontMetrics(g.getFont());
	        g.drawString("Tekan SPASI / SPACE untuk memulai game", 
	        		(SCREEN_WIDTH - metrics11.stringWidth("Tekan SPASI / SPACE untuk memulai game")) / 2, SCREEN_HEIGHT/2);
	        g.drawString("Tekan ESC untuk kembali ke Main Menu", 
	        		(SCREEN_WIDTH - metrics11.stringWidth("Tekan ESC untuk kembali ke Main Menu")) / 2, SCREEN_HEIGHT/2+IMAGE_SIZE*3);
	        
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Sans", Font.BOLD, 20));
	        FontMetrics metrics12 = getFontMetrics(g.getFont());
	        g.drawString("Short Guide", 
	        		(SCREEN_WIDTH - metrics12.stringWidth("Short Guide")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*6);
	        g.drawString("> Kamu bisa pilih level di panel kiri atas di arena", 
	        		(SCREEN_WIDTH - metrics12.stringWidth("> Kamu bisa pilih level di panel kiri atas di arena")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*4);
	        g.drawString("> Jika butuh pertolongsn tombol help tersedia di samping tombol level", 
	        		(SCREEN_WIDTH - metrics12.stringWidth("> Jika butuh pertolongsn tombol help tersedia di samping tombol level")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*3);
		}
		//draw choose characters page
		if(characters_menu) {
			g.setColor(new Color(211,18,18));
	        g.setFont(new Font("Sans", Font.BOLD, 45));
	        FontMetrics metrics2 = getFontMetrics(g.getFont());
	        g.drawString("Choose Skin", (SCREEN_WIDTH - metrics2.stringWidth("Choose Skin")) / 2, SCREEN_HEIGHT/4);
	        
	        g.drawImage(charaDisplay, SCREEN_WIDTH/3+20, SCREEN_HEIGHT/3, this);
	        
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Sans", Font.BOLD, 20));
	        FontMetrics metrics21 = getFontMetrics(g.getFont());
	        g.drawString("Press Left or Right to find skin, Press Enter to select, Press ESC to back", 
	        		(SCREEN_WIDTH - metrics21.stringWidth("Press Left or Right to find skin, Press Enter to select, Press ESC to back")) / 2, SCREEN_HEIGHT-IMAGE_SIZE);
		}
		//draw utilities
		if(utilities_menu) {
			g.setColor(Color.black);
	        g.setFont(new Font("Sans", Font.BOLD, 45));
	        FontMetrics metrics3 = getFontMetrics(g.getFont());
	        g.drawString("UTILITIES", (SCREEN_WIDTH - metrics3.stringWidth("UTILITIES")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*24);
		}
		//draw credits page
		if(credits_menu) {
			g.setColor(new Color(211,18,18));
	        g.setFont(new Font("Sans", Font.BOLD, 60));
	        FontMetrics metrics3 = getFontMetrics(g.getFont());
	        g.drawString("THANKS TO DEVELOPERS", (SCREEN_WIDTH - metrics3.stringWidth("THANKS TO DEVELOPERS")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*22);
	        
	        g.setColor(new Color(196,206,116));
	        g.setFont(new Font("Sans", Font.BOLD, 35));
	        FontMetrics metrics31 = getFontMetrics(g.getFont());
	        g.drawString("Muhammad Afif Dwi Ardhiansyah / 5025201212", 
	        		(SCREEN_WIDTH - metrics31.stringWidth("Muhammad Afif Dwi Ardhiansyah / 5025201212")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*18);
	        g.drawString("Muhammad Abror Al-Qushoyyi / 5025201028", 
	        		(SCREEN_WIDTH - metrics31.stringWidth("Muhammad Abror Al-Qushoyyi / 5025201028")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*15);
	        g.drawString("Gaudhiwa Hendrasto / 5025201066", 
	        		(SCREEN_WIDTH - metrics31.stringWidth("Gaudhiwa Hendrasto / 5025201066")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*12);
	        g.drawString("Beryl / 5025201029", 
	        		(SCREEN_WIDTH - metrics31.stringWidth("Beryl / 5025201029")) / 2, SCREEN_HEIGHT-IMAGE_SIZE*9);
		}
		//do exit
		if(exitMenu) {
			g.setColor(new Color(211,18,18));
	        g.setFont(new Font("Sans", Font.BOLD, 60));
	        FontMetrics metrics4 = getFontMetrics(g.getFont());
	        g.drawString("Are you sure want to Exit?", (SCREEN_WIDTH - metrics4.stringWidth("Are you sure want to Exit?")) / 2, SCREEN_HEIGHT/2-IMAGE_SIZE*4);
	        
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Sans", Font.BOLD, 30));
	        FontMetrics metrics41 = getFontMetrics(g.getFont());
	        g.drawString("ENTER for YES", (SCREEN_WIDTH - metrics41.stringWidth("ENTER for YES")) / 2, SCREEN_HEIGHT/2);
	        g.drawString("ESC for NO", (SCREEN_WIDTH - metrics41.stringWidth("ESC for NO")) / 2, SCREEN_HEIGHT/2+IMAGE_SIZE*2);
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
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		eagleMenuMove();
		repaint();
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
				if(chooseHorizontal==TOTAL_CHARA) chooseHorizontal = 1;
				else chooseHorizontal+=1;
				break;
			case KeyEvent.VK_LEFT:
				if(chooseHorizontal==1) chooseHorizontal = TOTAL_CHARA;
				else chooseHorizontal-=1;
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
	
	public class CountInSeconds implements ActionListener {
		private int count;
		
		CountInSeconds(){
			count = 0;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		public int resetCount() {
			return 0;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			count++;		
		}		
	}
}
