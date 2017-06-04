package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.server.model.Model;

public class View extends Observable implements Observer {
	protected String username;
	
	public View(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void showModel(Model model){
		
	}
	
	protected void processChoice() {
		//notifica i messaggi del cient al controller
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
