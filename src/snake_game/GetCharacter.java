package snake_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetCharacter extends GetFile {
	private int select;
	
	public GetCharacter(){
		File file = new File("select_character.txt");
		Scanner input;
		try {
			input = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			return;
		}
		this.select = input.nextInt();
		input.close();
	}

	@Override
	public int getInt() {
		return this.select;
	}
	
	
}
