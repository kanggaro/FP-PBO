import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;

public class HighScore {
    
    PrintWriter writer;
    Scanner scanner;
    
    public void HighScore(){
        try{
            scanner = new Scanner(Paths.get("highscore.txt"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setHighScore(int score){
        writer.write(score);
    }

    public int getHighScore(){
        return scanner.nextInt();
    }

    public void closeFile(){
        scanner.close();
    }
}
