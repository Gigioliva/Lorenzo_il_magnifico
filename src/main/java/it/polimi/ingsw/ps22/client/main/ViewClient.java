package it.polimi.ingsw.ps22.client.main;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;

public class ViewClient extends Observable implements Observer, Runnable {
	
	private String username;
	private Graphic graphic;
	
	public ViewClient(){
		graphic=new GraphicCLI();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			username=(String)arg;
			System.out.println((String)arg);
		}
		if(arg instanceof GenericMessage){
			GenericAnswer mex=graphic.getAnswer((GenericMessage)arg);
			if(mex!=null){
				setChanged();
				notifyObservers(mex);
			}
		}
		if(arg instanceof ChoiceMove){
			Move temp=graphic.getMove(username);
			if(temp!=null){
				setChanged();
				notifyObservers(temp);
			}
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

}
