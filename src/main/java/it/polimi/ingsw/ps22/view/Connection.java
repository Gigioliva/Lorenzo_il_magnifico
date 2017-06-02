package it.polimi.ingsw.ps22.view;

import java.util.Observable;

import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.model.Model;

public abstract class Connection extends Observable implements Runnable {
	
	public abstract void send(GenericMessage message);
	
	public abstract void send(Model model);

}
