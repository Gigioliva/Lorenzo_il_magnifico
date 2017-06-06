package it.polimi.ingsw.ps22.client.gui;

public class AdaptiveLayout {
	
	private static final Rectangle orangeDice = new Rectangle(1876, 2005, 3414, 3540);
	private static final Rectangle whiteDice = new Rectangle(1634, 1762, 3414, 3540);
	private static final Rectangle blackDice = new Rectangle(1390, 1520, 3414, 3540);
	private static final Rectangle councilPalace = new Rectangle(1410, 1830, 2140, 2290);
	private static final Rectangle harvestLeft = new Rectangle(162, 368, 3340, 3568);
	private static final Rectangle harvestRight = new Rectangle(445, 975, 3340, 3568);
	private static final Rectangle productionLeft = new Rectangle(162, 368, 3034, 3256);
	private static final Rectangle productionRight = new Rectangle(445, 975, 3034, 3256);
	private static final Rectangle market1 = new Rectangle(1403, 1609, 2973, 3200);
	private static final Rectangle market2 = new Rectangle(1638, 1845, 2973, 3200);
	private static final Rectangle market3 = new Rectangle(1860, 2064, 3038, 3262);
	private static final Rectangle market4 = new Rectangle(2032, 2240, 3210, 3432);
	private static final Rectangle playerGrid = new Rectangle(2042, 2148, 2000, 2106);
	private static final Rectangle church1 = new Rectangle(508,685,2263,2623);
	private static final Rectangle church2 = new Rectangle(704,883,2307,2654);
	private static final Rectangle church3 = new Rectangle(901,1078,2263,2623);
	private static final Rectangle faithSlotFrom1To3 = new Rectangle(169,274,2738,2845);
	private static final Rectangle faithSlotFrom3To5 = new Rectangle(518,693,2738,2845);
	private static final Rectangle faithSlotFrom6To15 = new Rectangle(1077,1182,2738,2845);
	private static final int cardDevelopmentStartOffsetX = 183;
	private static final int cardDevelopmentStartOffsetY = 193;
	private static final int cardDevelopmentHeight = 446;
	private static final int cardDevelopmentWidth = 275;
	private static final int cardDevelopmentOffsetX = 231;
	private static final int cardDevelopmentOffsetY = 24;
	private static final int playerGridOffsetY = 136;
	private static final int inChurchOffsetx = 486;
	private static final int outChurchOffsetx = 114;
	
	public static Rectangle getFaithSlotSpace(double resizeFactor, int slot) { 
		Rectangle temp;
		if (slot>=0 && slot<=2) {
			temp = new Rectangle(
				(faithSlotFrom1To3.getInitx()+(slot*outChurchOffsetx)),
				(faithSlotFrom1To3.getFinalx()+(slot*outChurchOffsetx)),
				faithSlotFrom1To3.getInity(),faithSlotFrom1To3.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>=3 && slot<=5) {
			temp = new Rectangle(
					(faithSlotFrom3To5.getInitx()+((slot-3)*inChurchOffsetx)),
					(faithSlotFrom3To5.getFinalx()+((slot-3)*inChurchOffsetx)),
					faithSlotFrom3To5.getInity(),faithSlotFrom3To5.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>=6 && slot<=15) {
			temp = new Rectangle(
					(faithSlotFrom6To15.getInitx()+((slot-6)*outChurchOffsetx)),
					(faithSlotFrom6To15.getFinalx()+((slot-6)*outChurchOffsetx)),
					faithSlotFrom3To5.getInity(),faithSlotFrom3To5.getFinaly());
			return temp.resize(resizeFactor);
		}
		return null;
	}
	
	
	
	
	
	public static Rectangle getChurchSpace(double resizeFactor, int era) { 
		//era deve essere 1, 2 o 3
		if (era==1)
			return church1.resize(resizeFactor);
		if (era==2)
			return church2.resize(resizeFactor);
		if (era==3)
			return church3.resize(resizeFactor);
		return null;
	}
	
	public static Rectangle getPlayerGridSpace(double resizeFactor, int position) {
		// da 1 a 4 la position
		position--;
		Rectangle temp = new Rectangle(playerGrid.getInitx(), playerGrid.getFinalx(),
				(playerGrid.getInity() + (position * playerGridOffsetY)),
				(playerGrid.getFinaly() + (position * playerGridOffsetY)));
		return temp.resize(resizeFactor);
	}

	public static Rectangle getProdLeftSpace(double resizeFactor) {
		return productionLeft.resize(resizeFactor);
	}

	public static Rectangle getProdRightSpace(double resizeFactor) {
		return productionRight.resize(resizeFactor);
	}

	public static Rectangle getHarvestLeftSpace(double resizeFactor) {
		return harvestLeft.resize(resizeFactor);
	}

	public static Rectangle getHarvestRightSpace(double resizeFactor) {
		return harvestRight.resize(resizeFactor);
	}

	public static Rectangle getMarket1Space(double resizeFactor) {
		return market1.resize(resizeFactor);
	}

	public static Rectangle getMarket2Space(double resizeFactor) {
		return market2.resize(resizeFactor);
	}

	public static Rectangle getMarket3Space(double resizeFactor) {
		return market3.resize(resizeFactor);
	}

	public static Rectangle getMarket4Space(double resizeFactor) {
		return market4.resize(resizeFactor);
	}

	public static Rectangle getOrangeDiceSpace(double resizeFactor) {
		return orangeDice.resize(resizeFactor);
	}

	public static Rectangle getBlackDiceSpace(double resizeFactor) {
		return blackDice.resize(resizeFactor);
	}

	public static Rectangle getWhiteDiceSpace(double resizeFactor) {
		return whiteDice.resize(resizeFactor);
	}

	public static Rectangle getCouncilPalaceSpace(double resizeFactor) {
		return councilPalace.resize(resizeFactor);
	}

	public static Rectangle getCardTerritorySpace(double resizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}

	public static Rectangle getCharacterSpace(double resizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += (cardDevelopmentOffsetX + cardDevelopmentWidth);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}

	public static Rectangle getCardBuildingSpace(double resizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 2);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}

	public static Rectangle getCardVentureSpace(double resizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 3);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}

	public static Rectangle getCardDevelopmentSpace(double resizeFactor, int towerNumber, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		// towerNumber--;
		// towerSlot--;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * towerNumber);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}

}
