package it.polimi.ingsw.ps22.view;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.move.Move;

public class View extends Observable implements Observer {
	private String username;
	
	public View(String username){
		this.username=username;
	}
	
	public void askMove(){
		
	}
	
	public void showModel(){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
