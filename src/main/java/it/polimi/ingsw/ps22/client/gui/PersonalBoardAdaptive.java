package it.polimi.ingsw.ps22.client.gui;

//	TUTTE LE POSIZIONI RITORNATE DA QUESTA CLASSE SONO DA RIFERIRSI AL FATTORE DI RISCALATURA
//	DELLA PERSONAL BOARD E SONO IN POSIZIONE RELATIVA DALL'INIZIO DELLA PERSONAL BOARD 
//  COMPRESA DI SPAZIO BONUS PERSONALI LE CUI MISURE REALI SONO UNIFORMATE A 220*1836

public class PersonalBoardAdaptive {

	private final Rectangle cardBaseSlot;
	private final Rectangle coinSlot;
	private final Rectangle woodSlot;
	private final Rectangle stoneSlot;
	private final Rectangle servantSlot;
	private final Rectangle blackDiceButtonSlot;
	private final Rectangle basicPlayerButtonSlot;
	private final Rectangle chatSlot;
	private final Rectangle servantRequestSlot;
	private final Rectangle leaderSlot;
	private final int cardOffsetX;
	private final int cardTerritoryOffsetY;
	private final int cardVentureOffsetY;
	private final int cardCharacterOffsetY;
	private final int diceButtonOffsetX;
	private final int playerOffsetX;
	private final int playerOffsetY;

	private static PersonalBoardAdaptive instance = null;

	private PersonalBoardAdaptive() {
		cardBaseSlot = new Rectangle(251, 747, 56, 802);
		coinSlot = new Rectangle(420, 700, 1800, 1920);
		woodSlot = new Rectangle(1020, 1200, 1800, 1920);
		stoneSlot = new Rectangle(1540, 1820, 1800, 1920);
		servantSlot = new Rectangle(2020, 2200, 1900, 2020);
		blackDiceButtonSlot = new Rectangle(0, 400, 4200, 4600);
		basicPlayerButtonSlot = new Rectangle(2100, 2600, 4000, 4280);
		chatSlot = new Rectangle(3630, 4300, 150, 2300);
		servantRequestSlot = new Rectangle(3700, 4900, 1900, 2200);
		leaderSlot = new Rectangle(3700, 4900, 2250, 4000);
		cardOffsetX = 548;
		cardTerritoryOffsetY = 1000;
		cardVentureOffsetY = 2350;
		cardCharacterOffsetY = 3130;
		diceButtonOffsetX = 500;
		playerOffsetX = 600;
		playerOffsetY = 315;
	}

	public static PersonalBoardAdaptive instance() {
		if (instance == null)
			instance = new PersonalBoardAdaptive();
		return instance;
	}

	public Rectangle getChatSlot(double resizeFactor) {
		return chatSlot.resize(resizeFactor);
	}

	public Rectangle getServantRequestSlot(double resizeFactor) {
		return servantRequestSlot.resize(resizeFactor);
	}

	public Rectangle getLeaderSlot(double resizeFactor) {
		return leaderSlot.resize(resizeFactor);

	}

	public Rectangle getBuildingCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)), cardBaseSlot.getInity(),
				cardBaseSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public Rectangle getTerritoryCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getInity() + cardTerritoryOffsetY), (cardBaseSlot.getFinaly() + cardTerritoryOffsetY));
		return temp.resize(resizeFactor);
	}

	public Rectangle getVentureCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)), (cardBaseSlot.getInity() + cardVentureOffsetY),
				(cardBaseSlot.getFinaly() + cardVentureOffsetY));
		return temp.resize(resizeFactor);
	}

	public Rectangle getCharacterCardSlot(double resizeFactor, int slot) {
		// gli slot vanno da 1 a 6
		Rectangle temp = new Rectangle((cardBaseSlot.getInitx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getFinalx() + ((slot - 1) * cardOffsetX)),
				(cardBaseSlot.getInity() + cardCharacterOffsetY), (cardBaseSlot.getFinaly() + cardCharacterOffsetY));
		return temp.resize(resizeFactor);
	}

	public Rectangle getCoinSlot(double resizeFactor) {
		return coinSlot.resize(resizeFactor);
	}

	public Rectangle getStoneSlot(double resizeFactor) {
		return stoneSlot.resize(resizeFactor);
	}

	public Rectangle getWoodSlot(double resizeFactor) {
		return woodSlot.resize(resizeFactor);
	}

	public Rectangle getServantSlot(double resizeFactor) {
		return servantSlot.resize(resizeFactor);
	}

	public Rectangle getBlackButtonSLot(double resizeFactor) {
		return blackDiceButtonSlot.resize(resizeFactor);
	}

	public Rectangle getWhiteButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + diceButtonOffsetX),
				(blackDiceButtonSlot.getFinalx() + diceButtonOffsetX), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public Rectangle getOrangeButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + (2 * diceButtonOffsetX)),
				(blackDiceButtonSlot.getFinalx() + (2 * diceButtonOffsetX)), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	public Rectangle getNeutralButtonSLot(double resizeFactor) {
		Rectangle temp = new Rectangle((blackDiceButtonSlot.getInitx() + (3 * diceButtonOffsetX)),
				(blackDiceButtonSlot.getFinalx() + (3 * diceButtonOffsetX)), blackDiceButtonSlot.getInity(),
				blackDiceButtonSlot.getFinaly());
		return temp.resize(resizeFactor);
	}

	// DISTRIBUZIONE GIOCATORI: ESTENDIBILITA'
	/*
	 * GIOCATORE 1 GIOCATORE 3 GIOCATORE 5 GIOCATORE 2 GIOCATORE 4 ECC...
	 */

	public Rectangle getPlayerButtonSLot(double resizeFactor, int player) {
		int xMolt = (int) (player / 2);
		int yMolt = 1 - (player % 2);
		Rectangle temp = new Rectangle((basicPlayerButtonSlot.getInitx() + ((xMolt) * playerOffsetX)),
				(basicPlayerButtonSlot.getFinalx() + ((xMolt) * playerOffsetX)),
				(basicPlayerButtonSlot.getInity() + ((yMolt) * playerOffsetY)),
				(basicPlayerButtonSlot.getFinaly() + ((yMolt) * playerOffsetY)));
		return temp.resize(resizeFactor);
	}

}