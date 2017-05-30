package it.polimi.ingsw.ps22.view;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.message.*;
import it.polimi.ingsw.ps22.model.Model;

public class View extends Observable implements Observer {
	private String username;
	
	public View(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void showModel(Model model){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Model && arg==null){
			showModel((Model)o);
		}
		if(o instanceof Model && arg instanceof ChatMessage){
			//mando al client
		}
		if(o instanceof Model && arg instanceof ErrorMove){
			if(((Model)o).getCurrentPlayer().equals(username)){
				//mando al client
			}
		}
		if(o instanceof Model && arg instanceof MessageAsk){
			if(((Model)o).getCurrentPlayer().equals(username)){
				//mando al client / forse salvo in memoria il messaggio mandato
			}
		}
		if(o instanceof Model && arg instanceof AskExcomm){
			if(((AskExcomm)arg).getPlayer().getUsername().equals(username)){
				//mando al client / forse salvo in memoria il messaggio mandato
			}
		}
		
	}

}
