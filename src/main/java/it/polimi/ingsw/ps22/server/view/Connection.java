package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public abstract class Connection extends Observable implements Runnable {
	
	public abstract void send(GenericMessage message);
	
	public abstract void send(Model model);
	
	public abstract void setActive();

}
