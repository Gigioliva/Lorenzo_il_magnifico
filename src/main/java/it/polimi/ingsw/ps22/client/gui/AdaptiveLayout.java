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
	private static final Rectangle victorySlot0 = new Rectangle(50,130,35,115);
	private static final Rectangle victorySlotFrom1To19 = new Rectangle(195,275,5,85);
	private static final Rectangle victorySlot20 = new Rectangle(2520,2600,35,115);
	private static final Rectangle victorySlotFrom21To49 = new Rectangle(2565,2645,170,250);
	private static final Rectangle victorySlot50 = new Rectangle(2520,2600,3630,3710);
	private static final Rectangle victorySlotFrom51To69 = new Rectangle(2370,2450,3660,3740);
	private static final Rectangle victorySlot70 = new Rectangle(50,130,3630,3710);
	private static final Rectangle victorySlotFrom71To99 = new Rectangle(5,85,3490,3570);
	private static final int cardDevelopmentStartOffsetX = 183;
	private static final int cardDevelopmentStartOffsetY = 193;
	private static final int cardDevelopmentHeight = 446;
	private static final int cardDevelopmentWidth = 275;
	private static final int cardDevelopmentOffsetX = 231;
	private static final int cardDevelopmentOffsetY = 24;
	private static final int playerGridOffsetY = 136;
	private static final int inChurchOffsetX = 486;
	private static final int outChurchOffsetX = 114;
	private static final int victoryPointOffsetXY = 120;
	
	public static Rectangle getVictorySlotSpace(double resizeFactor, int slot) { 
		Rectangle temp;
		slot=slot%100;
		if(slot==0) {
			return victorySlot0.resize(resizeFactor);
		}
		if(slot==20) {
			return victorySlot20.resize(resizeFactor);
		}
		if(slot==50) {
			return victorySlot50.resize(resizeFactor);
		}
		if(slot==70) {
			return victorySlot70.resize(resizeFactor);
		}
		if (slot>0 && slot<20) {
			temp = new Rectangle(
				(victorySlotFrom1To19.getInitx()+((slot-1)*victoryPointOffsetXY)),
				(victorySlotFrom1To19.getFinalx()+((slot-1)*victoryPointOffsetXY)),
				victorySlotFrom1To19.getInity(),victorySlotFrom1To19.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>20 && slot<50) {
			temp = new Rectangle(
					victorySlotFrom21To49.getInitx(),victorySlotFrom21To49.getFinalx(),
					(victorySlotFrom21To49.getInity()+((slot-21)*victoryPointOffsetXY)),
					(victorySlotFrom21To49.getFinaly()+((slot-21)*victoryPointOffsetXY)));
			return temp.resize(resizeFactor);
		}
		if (slot>50 && slot<70) {
			temp = new Rectangle(
					(victorySlotFrom51To69.getInitx()-((slot-51)*victoryPointOffsetXY)),
					(victorySlotFrom51To69.getFinalx()-((slot-51)*victoryPointOffsetXY)),
					victorySlotFrom51To69.getInity(),victorySlotFrom51To69.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>70 && slot<100) {
			temp = new Rectangle(
					victorySlotFrom71To99.getInitx(),victorySlotFrom71To99.getFinalx(),
					(victorySlotFrom71To99.getInity()-((slot-71)*victoryPointOffsetXY)),
					(victorySlotFrom71To99.getFinaly()-((slot-71)*victoryPointOffsetXY)));
			return temp.resize(resizeFactor);
		}
		return null;
	}
	
	public static Rectangle getFaithSlotSpace(double resizeFactor, int slot) { 
		Rectangle temp;
		if (slot>=0 && slot<=2) {
			temp = new Rectangle(
				(faithSlotFrom1To3.getInitx()+(slot*outChurchOffsetX)),
				(faithSlotFrom1To3.getFinalx()+(slot*outChurchOffsetX)),
				faithSlotFrom1To3.getInity(),faithSlotFrom1To3.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>=3 && slot<=5) {
			temp = new Rectangle(
					(faithSlotFrom3To5.getInitx()+((slot-3)*inChurchOffsetX)),
					(faithSlotFrom3To5.getFinalx()+((slot-3)*inChurchOffsetX)),
					faithSlotFrom3To5.getInity(),faithSlotFrom3To5.getFinaly());
			return temp.resize(resizeFactor);
		}
		if (slot>=6 && slot<=15) {
			temp = new Rectangle(
					(faithSlotFrom6To15.getInitx()+((slot-6)*outChurchOffsetX)),
					(faithSlotFrom6To15.getFinalx()+((slot-6)*outChurchOffsetX)),
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
		// da 0 a 3 la position
		//position--;
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
