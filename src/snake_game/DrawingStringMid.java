package snake_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class DrawingStringMid extends DrawingStringSpecific {
	
	public DrawingStringMid(Color color, Font font, String text, int width, int y, Graphics g) {
		super(color, font, text, width , y, g);
		g.setFont(font);
		FontMetrics metrics =  getFontMetrics(g.getFont());
		width = (width - metrics.stringWidth(text)) / 2;
		new DrawingStringSpecific(color, font, text, width , y, g).draw();
	}

}
