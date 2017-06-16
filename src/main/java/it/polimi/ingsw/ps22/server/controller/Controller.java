package it.polimi.ingsw.ps22.server.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.ChatMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;
import it.polimi.ingsw.ps22.server.view.View;

public class Controller implements Observer {
	private static final int TIMER=120000;
	private Model model;
	private Timer timer;
	
	public Controller(Model model){
		this.model=model;
		this.timer=new Timer();
		timer.schedule(new Task(), TIMER);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof View && arg instanceof Move){
			timer.cancel();
			timer=new Timer();
			timer.schedule(new Task(), TIMER);
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
	
	private class Task extends TimerTask{
		@Override
		public void run() {
			model.getWaitAnswer().removeAll(model.getWaitAnswer());
			model.nextPlayer();
			model.notifyModel();
			timer.cancel();
			timer=new Timer();
			timer.schedule(new Task(), TIMER);
		}
	}
	 

}
