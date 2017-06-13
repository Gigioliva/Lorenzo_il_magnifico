package it.polimi.ingsw.ps22.client.main;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class ViewClient extends Observable implements Observer, Runnable {
	
	private String username;
	private Graphic graphic;
	
	public ViewClient(){
		graphic=new GraphicCLI(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			username=(String)arg;
			System.out.println((String)arg);
		}
		if(arg instanceof GenericMessage){
			graphic.getAnswer((GenericMessage)arg);
		}
		if(arg instanceof ChoiceMove){
			graphic.getMove();
		}
		if(arg instanceof Model){
			graphic.printModel((Model)arg);
		}
	}

	@Override
	public void run() {
		
	}
	
	public String getUsername(){
		return username;
	}
	
	public void send(Object obj){
		if(obj!=null){
			setChanged();
			notifyObservers(obj);
		}
	}

}
