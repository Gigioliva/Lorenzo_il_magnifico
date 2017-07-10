package it.polimi.ingsw.ps22.server.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.ChatMessage;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;
import it.polimi.ingsw.ps22.server.parser.TimerSaxParser;
import it.polimi.ingsw.ps22.server.view.View;

public class Controller implements Observer {
	private static int TIMER;
	private Model model;
	private Timer timer;

	public Controller(Model model) {
		this.model = model;
		TIMER=TimerSaxParser.ControllerTimer();
		this.timer = new Timer();
		timer.schedule(new Task(), TIMER);
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			if (model.getIsActive()) {
				if (o instanceof View && arg instanceof Move) {
					timer.cancel();
					timer = new Timer();
					timer.schedule(new Task(), TIMER);
					if (((View) o).getUsername().equals(model.getPlayerGame())) {
						((Move) arg).applyMove(model);
					}
				}
				if (o instanceof View && arg instanceof ChatMessage) {
					model.notifyMessage((ChatMessage) arg);
				}
				if (o instanceof View && arg instanceof GenericAnswer) {
					((GenericAnswer) arg).applyAnswer(model);
				}
				if (o instanceof View && arg==null) {
					model.getPlayers().get(((View)o).getUsername()).setConnected(true);;
					model.sendModel();
					for(MessageAsk el: model.getWaitAnswer()){
						if(el.getUser().equals(((View)o).getUsername())){
							model.notifyAsk(el);
						}
					}
				}
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(Controller.class.getName());
			logger.info(e.getMessage());
		}
	}

	private class Task extends TimerTask {
		@Override
		public void run() {
			if(model.getCurrentPlayer()!=null){
				while(!model.getWaitAnswer().isEmpty()){
					model.getWaitAnswer().get(0).applyDefault(model);
				}
				model.getCurrentPlayer().setConnected(false);
				GenericMessage mex=new GenericMessage();
				mex.setString("Il giocatore "+ model.getPlayerGame() + " si è disconnesso");
				model.notifyModel();
				model.notifyMessage(mex);
				do{
					model.nextPlayer();
				}while(!model.getCurrentPlayer().getConnected() && model.getIsActive());
				model.notifyModel();
			}else{
				ArrayList<MessageAsk> temp=new ArrayList<>();
				temp.addAll(model.getWaitAnswer());
				for(MessageAsk el: temp){
					el.applyDefault(model);
				}
			}
			timer.cancel();
			timer = new Timer();
			timer.schedule(new Task(), TIMER);
		}
	}

}
