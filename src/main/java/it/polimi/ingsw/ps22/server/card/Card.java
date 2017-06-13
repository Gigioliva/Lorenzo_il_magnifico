package it.polimi.ingsw.ps22.server.card;

import java.io.Serializable;

public abstract class Card implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public abstract Card clone();
}
