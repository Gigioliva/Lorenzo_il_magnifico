package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;

public abstract class Connection extends Observable implements Runnable {
	
	public abstract void send(Object obj);
	
	public abstract void setActive();
	
	public abstract void close();

}
