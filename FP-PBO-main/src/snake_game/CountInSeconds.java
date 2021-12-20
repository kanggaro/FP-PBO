package snake_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
