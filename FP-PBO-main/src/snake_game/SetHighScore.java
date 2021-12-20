package snake_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SetHighScore extends SetFile{
	
	public SetHighScore(int score) {
		super(score);
		File file = new File("highscore.txt");
		PrintWriter pw;
		try{
			pw = new PrintWriter(file);
		}
		catch(FileNotFoundException e) {
			return;
		}
		
		pw.println(score);
		pw.close();
	}
}
