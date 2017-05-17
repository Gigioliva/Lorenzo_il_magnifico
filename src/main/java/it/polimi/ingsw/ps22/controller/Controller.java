package it.polimi.ingsw.ps22.controller;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.model.Model;

public class Controller implements Observer {
	private Model model;
	
	public Controller(Model model){
		this.model=model;		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	 

}
