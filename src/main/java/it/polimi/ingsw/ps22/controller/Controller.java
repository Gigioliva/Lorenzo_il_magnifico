package it.polimi.ingsw.ps22.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.answer.GenericAnswer;
import it.polimi.ingsw.ps22.message.ChatMessage;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.move.Move;
import it.polimi.ingsw.ps22.view.View;

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
			//si potrebbe aggiungere una notifica al giocatore che il turno non è il suo
		}
		if(o instanceof View && arg instanceof ChatMessage){
			model.notifyMessage((ChatMessage)arg);
		}
		if(o instanceof View && arg instanceof GenericAnswer){
			((GenericAnswer)arg).applyAnswer(model);
		}
	}
	 

}
