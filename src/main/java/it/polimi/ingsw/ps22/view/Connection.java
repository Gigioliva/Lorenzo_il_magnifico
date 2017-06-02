package it.polimi.ingsw.ps22.view;

import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.model.Model;

public interface Connection extends Runnable {
	
	public void send(GenericMessage message);
	
	public void send(Model model);

}
