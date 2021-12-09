package snake_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetHighScore{
	private int score;
	
	public GetHighScore(){
		File file = new File("highscore.txt");
		Scanner input;
		try {
			input = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			return;
		}
		this.score = input.nextInt();
		getHighScore();
		input.close();
	}
	
	public int getHighScore() {
		return this.score;
	}

}
