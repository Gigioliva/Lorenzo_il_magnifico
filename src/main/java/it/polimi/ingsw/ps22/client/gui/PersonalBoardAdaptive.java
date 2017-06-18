package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

//	TUTTE LE POSIZIONI RITORNATE DA QUESTA CLASSE SONO DA RIFERIRSI AL FATTORE DI RISCALATURA
//	DELLA PERSONAL BOARD E SONO IN POSIZIONE RELATIVA DALL'INIZIO DELLA PERSONAL BOARD 
//  COMPRESA DI SPAZIO BONUS PERSONALI LE CUI MISURE REALI SONO UNIFORMATE A 220*1836

public class PersonalBoardAdaptive {

	private static final Rectangle cardBaseSlot = new Rectangle(251, 747, 56, 802);
	private static final Rectangle coinSlot = new Rectangle(330, 510, 2070, 2190);
	private static final Rectangle woodSlot = new Rectangle(900, 1080, 2070, 2190);
	private static final Rectangle stoneSlot = new Rectangle(1470, 1650, 2070, 2190);;
	private static final Rectangle servantSlot = new Rectangle(2190, 2370, 2070, 2190);
	private static final Rectangle blackDiceButtonSlot = new Rectangle(0, 400, 4000, 4600);
	private static final Rectangle basicPlayerButtonSlot = new Rectangle(2100, 2600, 4000, 4280);
	private static final Rectangle chatSlot = new Rectangle(3630, 3900, 150, 2308);
	private static final Rectangle servantRequestSlot = new Rectangle(3630, 3900, 2400, 2700);
	private static final Rectangle excommSlot1 = new Rectangle(3630, 3900, 2400, 2700);
	private static final Rectangle excommSlot2 = new Rectangle(3630, 3900, 2400, 2700);
	private static final Rectangle excommSlot3 = new Rectangle(3630, 3900, 2400, 2700);
	private static final Rectangle leaderSlot = new Rectangle(3630, 3800, 2730, 2980);

	private static final int cardOffsetX = 548;
	private static final int cardTerritoryOffsetY = 1000;
	private static final int cardVentureOffsetY = 2350;
	private static final int cardCharacterOffsetY = 3130;
	private static final int diceButtonOffsetX = 500;
	private static final int playerOffsetX = 600;
	private static final int playerOffsetY = 315;
	private static final int leaderOffsetX = 180;
	private static final int leaderOffsetY = 320;

	
	public static Rectangle getChatSlot(double resizeFactor) {
		return chatSlot.resize(resizeFactor);
	}

	public static Rectangle getServantRequestSlot(double resizeFactor) {
		return servantRequestSlot.resize(resizeFactor);
	}

	public static Rectangle getExcommSlot(double resizeFactor, int era) {
		if (era == 1)
			return excommSlot1.resize(resizeFactor);
		if (era == 2)
			return excommSlot2.resize(resizeFactor);
		if (era == 3)
			return excommSlot3.resize(resizeFactor);
		return null;
	}

	public static ArrayList<Rectangle> getLeaderSlot(double resizeFactor) {
		ArrayList<Rectangle> temp = new ArrayList<Rectangle>();
		temp.add(leaderSlot.resize(resizeFactor));
		temp.add(new Rectangle(leaderSlot.getInitx()+leaderOffsetX, 
				leaderSlot.getFinalx()+leaderOffsetX,
				leaderSlot.getInity(), leaderSlot.getFinaly()).resize(resizeFactor));
		temp.add(new Rectangle(leaderSlot.getInitx(), leaderSlot.getFinalx(),
				leaderSlot.getInity()+leaderOffsetY, 
				leaderSlot.getFinaly()+leaderOffsetY).resize(resizeFactor));
		temp.add(new Rectangle(leaderSlot.getInitx()+leaderOffsetX, 
				leaderSlot.getFinalx()+leaderOffsetX,
				leaderSlot.getInity()+leaderOffsetY, 
				leaderSlot.getFinaly()+leaderOffsetY).resize(resizeFactor));
		return temp;
	}

	public static Rectangle getBuildingCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)), cardBaseSlot.getInity(),
				cardBaseSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public static Rectangle getTerritoryCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getInity() + cardTerritoryOffsetY), (cardBaseSlot.getFinaly() + cardTerritoryOffsetY));
		return temp.resize(resizeFactor);
	}

	public static Rectangle getVentureCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)), (cardBaseSlot.getInity() + cardVentureOffsetY),
				(cardBaseSlot.getFinaly() + cardVentureOffsetY));
		return temp.resize(resizeFactor);
	}

	public static Rectangle getCharacterCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getInity() + cardCharacterOffsetY), (cardBaseSlot.getFinaly() + cardCharacterOffsetY));
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

	public static Rectangle getBlackButtonSLot(double resizeFactor) {
		return blackDiceButtonSlot.resize(resizeFactor);
	}

	public static Rectangle getOrangeButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + diceButtonOffsetX),
				(blackDiceButtonSlot.getFinalx() + diceButtonOffsetX), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public static Rectangle getWhiteButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + (2 * diceButtonOffsetX)),
				(blackDiceButtonSlot.getFinalx() + (2 * diceButtonOffsetX)), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public static Rectangle getNeutralButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + (3 * diceButtonOffsetX)),
				(blackDiceButtonSlot.getFinalx() + (3 * diceButtonOffsetX)), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	// DISTRIBUZIONE GIOCATORI: ESTENDIBILITA?
	/*
	 * GIOCATORE 1 GIOCATORE 3 GIOCATORE 5 GIOCATORE 2 GIOCATORE 4 ECC...
	 */

	public static Rectangle getPlayerButtonSLot(double resizeFactor, int player) {
		int xMolt = (int) (player / 2);
		int yMolt = 1 - (player % 2);
		Rectangle temp = new Rectangle((basicPlayerButtonSlot.getInitx() + ((xMolt) * playerOffsetX)),
				(basicPlayerButtonSlot.getFinalx() + ((xMolt) * playerOffsetX)),
				(basicPlayerButtonSlot.getInity() + ((yMolt) * playerOffsetY)),
				(basicPlayerButtonSlot.getFinaly() + ((yMolt) * playerOffsetY)));
		return temp.resize(resizeFactor);
	}

}