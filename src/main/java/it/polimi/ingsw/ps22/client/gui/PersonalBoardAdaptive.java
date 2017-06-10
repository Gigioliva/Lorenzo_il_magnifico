package it.polimi.ingsw.ps22.client.gui;

//	TUTTE LE POSIZIONI RITORNATE DA QUESTA CLASSE SONO DA RIFERIRSI AL FATTORE DI RISCALATURA
//	DELLA PERSONAL BOARD E SONO IN POSIZIONE RELATIVA DALL'INIZIO DELLA PERSONAL BOARD 
//  COMPRESA DI SPAZIO BONUS PERSONALI LE CUI MISURE REALI SONO UNIFORMATE A 220*1815



public class PersonalBoardAdaptive {
	
	private static final Rectangle cardBaseSlot = new Rectangle(251,747,56,802);
	private static final Rectangle coinSlot = new Rectangle(360,480,2070,2190);
	private static final Rectangle woodSlot = new Rectangle(930,1050,2070,2190);
	private static final Rectangle stoneSlot = new Rectangle(1500,1620,2070,2190);;
	private static final Rectangle servantSlot = new Rectangle(2020,2140,2070,2190);
	private static final int cardOffsetX = 548;
	private static final int cardTerritoryOffsetY = 800;
	private static final int cardVentureOffsetY = 2400;
	private static final int cardCharacterOffsetY = 3200;
	
	
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
	
	public static Rectangle getVentureCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle(
				(cardBaseSlot.getInitx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getFinalx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getInity()+cardVentureOffsetY),
				(cardBaseSlot.getFinaly()+cardVentureOffsetY)
				);
		return temp.resize(resizeFactor);
	}
	
	public static Rectangle getCharacterCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle(
				(cardBaseSlot.getInitx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getFinalx()+((slot-1)*cardOffsetX)),
				(cardBaseSlot.getInity()+cardCharacterOffsetY),
				(cardBaseSlot.getFinaly()+cardCharacterOffsetY)
				);
		return temp.resize(resizeFactor);
	}
	
	public static Rectangle getCoinSlot(double resizeFactor) {
		return coinSlot.resize(resizeFactor);
	}
	
	public static Rectangle getStoneSlot(double resizeFactor) {
		return stoneSlot.resize(resizeFactor);
	}
	
	public static Rectangle getWoodSlot(double resizeFactor) {
		return woodSlot.resize(resizeFactor);
	}
	
	public static Rectangle getServantSlot(double resizeFactor) {
		return servantSlot.resize(resizeFactor);
	}
	
}