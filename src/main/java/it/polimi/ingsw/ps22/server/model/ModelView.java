package it.polimi.ingsw.ps22.server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.message.GenericMessage;

public class ModelView extends Observable implements Observer {

	private HashMap<String, Model> models;
	private String currentPlayer;

	public ModelView() {
		models = new HashMap<String, Model>();
	}

	public Model getModel(String username){
		if(models.containsKey(username)){
			return models.get(username);
		}else{
			return null;
		}
	}
	
	public String getCurrentPlayer(){
		return currentPlayer;
	}
	
	public void notifyModels() {
		setChanged();
		notifyObservers();
	}

	public void notifyMessage(GenericMessage obj) {
		setChanged();
		notifyObservers(obj);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Model && arg == null) {
			ArrayList<String> names = new ArrayList<String>(((Model) o).getPlayers().keySet());
			for (String el : names) {
				models.put(el, ((Model) o).clone(el));
			}
			currentPlayer=((Model)o).getPlayerGame();
			notifyModels();
		}
		if (o instanceof Model && arg instanceof GenericMessage) {
			notifyMessage((GenericMessage)arg);
		}
		
	}
}
