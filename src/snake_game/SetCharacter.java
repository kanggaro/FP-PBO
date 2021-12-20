package snake_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SetCharacter{
	
	public SetCharacter(int select) {
		File file = new File("select_character.txt");
		PrintWriter pw;
		try{
			pw = new PrintWriter(file);
		}
		catch(FileNotFoundException e) {
			return;
		}
		
		pw.println(select);
		pw.close();
	}
}
