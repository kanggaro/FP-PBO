package snake_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawingStringSpecific extends DrawingString {

	Color color;
	Font font;
	String text;
	private int x;
	private int y;
	Graphics g;
	public DrawingStringSpecific(Color color, Font font, String text, int x, int y, Graphics g) {
		this.color = color;
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
		this.g = g;
	}
	
	public void draw() {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
}
