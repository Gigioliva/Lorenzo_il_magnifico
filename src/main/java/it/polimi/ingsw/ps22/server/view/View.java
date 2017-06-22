package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;

public class View extends Observable implements Observer {
	protected String username;
	
	public View(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return username;
	}
	
	protected void processChoice() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
