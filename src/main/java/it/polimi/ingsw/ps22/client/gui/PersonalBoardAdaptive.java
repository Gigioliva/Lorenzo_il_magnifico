package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.player.PersonalBoard;

//	TUTTE LE POSIZIONI RITORNATE DA QUESTA CLASSE SONO DA RIFERIRSI AL FATTORE DI RISCALATURA
//	DELLA PERSONAL BOARD E SONO IN POSIZIONE RELATIVA DALL'INIZIO DELLA STESSA



public class PersonalBoardAdaptive {
	
	private static final Rectangle cardBaseSlot = new Rectangle(0,0,0,0);
	private static final Rectangle coinSlot = new Rectangle(0,0,0,0);
	private static final Rectangle woodSlot = new Rectangle(0,0,0,0);
	private static final Rectangle StoneSlot = new Rectangle(0,0,0,0);
	private static final Rectangle servantSlot = new Rectangle(0,0,0,0);
	private static final int cardOffsetX = 548;
	private static final int cardTerritoryOffsetY = 800;
	
	
	public static Rectangle getBuildingCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle(
				(cardBaseSlot.getInitx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getFinalx()+((slot-1)*cardOffsetX)),
				cardBaseSlot.getInity(),cardBaseSlot.getFinaly());
		return temp.resize(resizeFactor);
	}
	
	public static Rectangle getTerritoryCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle(
				(cardBaseSlot.getInitx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getFinalx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getInity()+cardTerritoryOffsetY),
				(cardBaseSlot.getFinaly()+cardTerritoryOffsetY)
				);
		return temp.resize(resizeFactor);
	}
	
	
	
}