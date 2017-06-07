package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;

public class ViewClient extends Observable implements Observer, Runnable {
	
	private static String username;
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private VisitorB visitor;

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Receive && arg instanceof String){
			username=(String)arg;
			System.out.println("Your username is: " + username);
		}
		if(o instanceof Receive && arg instanceof GenericMessage){
			GenericAnswer mex=((GenericMessage)arg).accept(visitor);
			if(mex!=null){
				setChanged();
				notifyObservers(mex);
			}
		}
		if(o instanceof Receive && arg instanceof ChoiceMove){
			Move temp=RequestMove.chiediMossa(stdin);
			if(temp!=null){
				setChanged();
				notifyObservers(temp);
			}
			
		}
		if(o instanceof Receive && arg instanceof Model){
			
		}
		
	}

	@Override
	public void run() {
		
	}
	
	public static String getUsername(){
		return username;
	}

}
