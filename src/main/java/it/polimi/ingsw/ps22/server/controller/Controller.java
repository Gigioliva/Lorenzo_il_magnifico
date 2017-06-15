package it.polimi.ingsw.ps22.server.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.ChatMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;
import it.polimi.ingsw.ps22.server.view.View;

public class Controller implements Observer {
	private Model model;
	
	public Controller(Model model){
		this.model=model;		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof View && arg instanceof Move){
			if(((View)o).getUsername().equals(model.getPlayerGame())){
				((Move)arg).applyMove(model);
			}
		}
		if(o instanceof View && arg instanceof ChatMessage){
			model.notifyMessage((ChatMessage)arg);
		}
		if(o instanceof View && arg instanceof GenericAnswer){
			((GenericAnswer)arg).applyAnswer(model);
		}
	}
	 

}
