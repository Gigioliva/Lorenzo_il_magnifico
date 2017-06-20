package it.polimi.ingsw.ps22.client.gui;

public class AdaptiveLayout {

	private static final Rectangle orangeDice = new Rectangle(1876, 2005, 3414, 3540);
	private static final Rectangle whiteDice = new Rectangle(1634, 1762, 3414, 3540);
	private static final Rectangle blackDice = new Rectangle(1390, 1520, 3414, 3540);
	private static final Rectangle councilPalaceButton = new Rectangle(1410, 1830, 2140, 2290);
	private static final Rectangle councilPalace = new Rectangle(1410, 1470, 2140, 2200);
	private static final Rectangle harvestLeftButton = new Rectangle(195, 335, 3360, 3500);
	private static final Rectangle harvestRightButton = new Rectangle(500, 920, 3360, 3500); 
	private static final Rectangle harvestRightFam = new Rectangle(590, 650, 3400, 3460); 
	private static final Rectangle harvestRightCover = new Rectangle(445, 975, 3340, 3568);
	private static final Rectangle productionLeftButton = new Rectangle(195, 335, 3050, 3190);
	private static final Rectangle productionRightButton = new Rectangle(500, 920, 3050, 3190);
	private static final Rectangle productionRightFam = new Rectangle(590, 650, 3085, 3145); 
	private static final Rectangle productionRightCover = new Rectangle(445, 975, 3034, 3256);
	private static final Rectangle market1 = new Rectangle(1456, 1556, 3020, 3120);
	private static final Rectangle market2 = new Rectangle(1690, 1790, 3020, 3120);
	private static final Rectangle market3 = new Rectangle(1914, 2014, 3090, 3190);
	private static final Rectangle market4 = new Rectangle(2090, 2190, 3260, 3360);
	private static final Rectangle marketCover3 = new Rectangle(1860, 2070, 3035, 3265);
	private static final Rectangle marketCover4 = new Rectangle(2030, 2250, 3205, 3440);
	private static final Rectangle playerGrid = new Rectangle(2042, 2148, 2000, 2106);
	private static final Rectangle church1 = new Rectangle(508, 685, 2263, 2623);
	private static final Rectangle church2 = new Rectangle(704, 883, 2307, 2654);
	private static final Rectangle church3 = new Rectangle(901, 1078, 2263, 2623);
	private static final Rectangle faithSlotFrom1To3 = new Rectangle(169, 274, 2738, 2845);
	private static final Rectangle faithSlotFrom3To5 = new Rectangle(550, 657, 2738, 2845);
	private static final Rectangle faithSlotFrom6To15 = new Rectangle(1077, 1182, 2738, 2845);
	private static final Rectangle victorySlot0 = new Rectangle(50, 130, 35, 115);
	private static final Rectangle victorySlotFrom1To19 = new Rectangle(195, 275, 5, 85);
	private static final Rectangle victorySlot20 = new Rectangle(2520, 2600, 35, 115);
	private static final Rectangle victorySlotFrom21To49 = new Rectangle(2565, 2645, 170, 250);
	private static final Rectangle victorySlot50 = new Rectangle(2520, 2600, 3630, 3710);
	private static final Rectangle victorySlotFrom51To69 = new Rectangle(2370, 2450, 3660, 3740);
	private static final Rectangle victorySlot70 = new Rectangle(50, 130, 3630, 3710);
	private static final Rectangle victorySlotFrom71To99 = new Rectangle(5, 85, 3490, 3570);
	private static final Rectangle familiar = new Rectangle(512, 612, 360, 460);
	private static final Rectangle militarySlotFrom1 = new Rectangle(2300, 2380, 3240, 3320);
	private static final Rectangle militarySlot0 = new Rectangle(2300, 2380, 3400, 3480);
	private static final Rectangle excommSlot1 = new Rectangle(534, 654, 2328, 2448);
	private static final Rectangle excommSlot2 = new Rectangle(732, 852, 2360, 2480);
	private static final Rectangle excommSlot3 = new Rectangle(930, 1050, 2328, 2448);

	private static final int cardDevelopmentStartOffsetX = 183;
	private static final int cardDevelopmentStartOffsetY = 193;
	private static final int cardDevelopmentHeight = 446;
	private static final int cardDevelopmentWidth = 275;
	private static final int cardDevelopmentOffsetX = 231;
	private static final int cardDevelopmentOffsetY = 24;
	private static final int playerGridOffsetY = 136;
	private static final int inChurchOffsetX = 186;
	private static final int outChurchOffsetX = 114;
	private static final int victoryPointOffsetXY = 120;
	private static final int familiarOffsetX = 508;
	private static final int familiarOffsetY = 473;
	private static final int militaryOffsetY = 127;
	private static final int addingMilitaryOffsetY = 15;

	
	public static Rectangle getExcommSlot(double boardResizeFactor, int era) {
		if (era == 1)
			return excommSlot1.resize(boardResizeFactor);
		if (era == 2)
			return excommSlot2.resize(boardResizeFactor);
		if (era == 3)
			return excommSlot3.resize(boardResizeFactor);
		return null;
	}
	
	public static Rectangle getMilitarySlotSpace(double boardResizeFactor, int slot) {
		slot = slot % 26;
		if (slot > 0) {
			Rectangle temp = new Rectangle(militarySlotFrom1.getInitx(), militarySlotFrom1.getFinalx(),
					(militarySlotFrom1.getInity() - ((slot - 1) * militaryOffsetY)),
					(militarySlotFrom1.getFinaly() - ((slot - 1) * militaryOffsetY)));
			if (slot > 2) {
				temp.setInity(temp.getInity() - addingMilitaryOffsetY);
				temp.setFinaly(temp.getFinaly() - addingMilitaryOffsetY);
			}
			if (slot > 6) {
				temp.setInity(temp.getInity() - addingMilitaryOffsetY);
				temp.setFinaly(temp.getFinaly() - addingMilitaryOffsetY);
			}
			if (slot > 11) {
				temp.setInity(temp.getInity() - addingMilitaryOffsetY);
				temp.setFinaly(temp.getFinaly() - addingMilitaryOffsetY);
			}
			if (slot > 17) {
				temp.setInity(temp.getInity() - addingMilitaryOffsetY);
				temp.setFinaly(temp.getFinaly() - addingMilitaryOffsetY);
			}
			return temp.resize(boardResizeFactor);
		} else
			return militarySlot0.resize(boardResizeFactor);
	}

	public static Rectangle getFamiliarSpace(double resizeFactor, int towerNumber, int towerSlot) {
		// deve essere da 0 a tre per entrambi
		towerSlot = 3 - towerSlot;
		Rectangle temp = new Rectangle((familiar.getInitx() + (towerNumber * familiarOffsetX)),
				(familiar.getFinalx() + (towerNumber * familiarOffsetX)),
				(familiar.getInity() + (towerSlot * familiarOffsetY)),
				(familiar.getFinaly() + (towerSlot * familiarOffsetY)));
		return temp.resize(resizeFactor);
	}

	public static Rectangle getVictorySlotSpace(double boardResizeFactor, int slot) {
		Rectangle temp;
		slot = slot % 100;
		if (slot == 0) {
			return victorySlot0.resize(boardResizeFactor);
		}
		if (slot == 20) {
			return victorySlot20.resize(boardResizeFactor);
		}
		if (slot == 50) {
			return victorySlot50.resize(boardResizeFactor);
		}
		if (slot == 70) {
			return victorySlot70.resize(boardResizeFactor);
		}
		if (slot > 0 && slot < 20) {
			temp = new Rectangle((victorySlotFrom1To19.getInitx() + ((slot - 1) * victoryPointOffsetXY)),
					(victorySlotFrom1To19.getFinalx() + ((slot - 1) * victoryPointOffsetXY)),
					victorySlotFrom1To19.getInity(), victorySlotFrom1To19.getFinaly());
			return temp.resize(boardResizeFactor);
		}
		if (slot > 20 && slot < 50) {
			temp = new Rectangle(victorySlotFrom21To49.getInitx(), victorySlotFrom21To49.getFinalx(),
					(victorySlotFrom21To49.getInity() + ((slot - 21) * victoryPointOffsetXY)),
					(victorySlotFrom21To49.getFinaly() + ((slot - 21) * victoryPointOffsetXY)));
			return temp.resize(boardResizeFactor);
		}
		if (slot > 50 && slot < 70) {
			temp = new Rectangle((victorySlotFrom51To69.getInitx() - ((slot - 51) * victoryPointOffsetXY)),
					(victorySlotFrom51To69.getFinalx() - ((slot - 51) * victoryPointOffsetXY)),
					victorySlotFrom51To69.getInity(), victorySlotFrom51To69.getFinaly());
			return temp.resize(boardResizeFactor);
		}
		if (slot > 70 && slot < 100) {
			temp = new Rectangle(victorySlotFrom71To99.getInitx(), victorySlotFrom71To99.getFinalx(),
					(victorySlotFrom71To99.getInity() - ((slot - 71) * victoryPointOffsetXY)),
					(victorySlotFrom71To99.getFinaly() - ((slot - 71) * victoryPointOffsetXY)));
			return temp.resize(boardResizeFactor);
		}
		return null;
	}

	public static Rectangle getFaithSlotSpace(double boardResizeFactor, int slot) {
		Rectangle temp;
		if (slot >= 0 && slot <= 2) {
			temp = new Rectangle((faithSlotFrom1To3.getInitx() + (slot * outChurchOffsetX)),
					(faithSlotFrom1To3.getFinalx() + (slot * outChurchOffsetX)), faithSlotFrom1To3.getInity(),
					faithSlotFrom1To3.getFinaly());
			return temp.resize(boardResizeFactor);
		}
		if (slot >= 3 && slot <= 5) {
			temp = new Rectangle((faithSlotFrom3To5.getInitx() + ((slot - 3) * inChurchOffsetX)),
					(faithSlotFrom3To5.getFinalx() + ((slot - 3) * inChurchOffsetX)), faithSlotFrom3To5.getInity(),
					faithSlotFrom3To5.getFinaly());
			return temp.resize(boardResizeFactor);
		}
		if (slot >= 6 && slot <= 15) {
			temp = new Rectangle((faithSlotFrom6To15.getInitx() + ((slot - 6) * outChurchOffsetX)),
					(faithSlotFrom6To15.getFinalx() + ((slot - 6) * outChurchOffsetX)), faithSlotFrom3To5.getInity(),
					faithSlotFrom3To5.getFinaly());
			return temp.resize(boardResizeFactor);
		}
		return null;
	}

	public static Rectangle getChurchSpace(double boardResizeFactor, int era) {
		// era deve essere 1, 2 o 3
		if (era == 1)
			return church1.resize(boardResizeFactor);
		if (era == 2)
			return church2.resize(boardResizeFactor);
		if (era == 3)
			return church3.resize(boardResizeFactor);
		return null;
	}

	public static Rectangle getPlayerGridSpace(double boardResizeFactor, int position) {
		// da 0 a 3 la position
		// position--;
		Rectangle temp = new Rectangle(playerGrid.getInitx(), playerGrid.getFinalx(),
				(playerGrid.getInity() + (position * playerGridOffsetY)),
				(playerGrid.getFinaly() + (position * playerGridOffsetY)));
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getProdLeftSpace(double boardResizeFactor) {
		return productionLeftButton.resize(boardResizeFactor);
	}

	public static Rectangle getProdRightSpace(double boardResizeFactor) {
		return productionRightButton.resize(boardResizeFactor);
	}
	
	public static Rectangle getProdRightCover(double boardResizeFactor) {
		return productionRightCover.resize(boardResizeFactor);
	}

	public static Rectangle getHarvestLeftSpace(double boardResizeFactor) {
		return harvestLeftButton.resize(boardResizeFactor);
	}

	public static Rectangle getHarvestRightSpace(double boardResizeFactor) {
		return harvestRightButton.resize(boardResizeFactor);
	}
	
	public static Rectangle getHarvestRightCover(double boardResizeFactor) {
		return harvestRightCover.resize(boardResizeFactor);
	}

	public static Rectangle getMarket1Space(double boardResizeFactor) {
		return market1.resize(boardResizeFactor);
	}

	public static Rectangle getMarket2Space(double boardResizeFactor) {
		return market2.resize(boardResizeFactor);
	}

	public static Rectangle getMarket3Space(double boardResizeFactor) {
		return market3.resize(boardResizeFactor);
	}
	
	public static Rectangle getMarket3Cover(double boardResizeFactor) {
		return marketCover3.resize(boardResizeFactor);
	}
	public static Rectangle getMarket4Cover(double boardResizeFactor) {
		return marketCover4.resize(boardResizeFactor);
	}

	public static Rectangle getMarket4Space(double boardResizeFactor) {
		return market4.resize(boardResizeFactor);
	}

	public static Rectangle getOrangeDiceSpace(double boardResizeFactor) {
		return orangeDice.resize(boardResizeFactor);
	}

	public static Rectangle getBlackDiceSpace(double boardResizeFactor) {
		return blackDice.resize(boardResizeFactor);
	}

	public static Rectangle getWhiteDiceSpace(double boardResizeFactor) {
		return whiteDice.resize(boardResizeFactor);
	}

	public static Rectangle getCouncilPalaceSpace(double boardResizeFactor) {
		return councilPalaceButton.resize(boardResizeFactor);
	}
	
	public static Rectangle getCouncilPalaceFamSpace(double boardResizeFactor,int position) {
		Rectangle temp = new Rectangle(
				(councilPalace.getInitx()+(councilPalace.getOffsetX()*(position%8))),
				(councilPalace.getFinalx()+(councilPalace.getOffsetX()*(position%8))),
				(councilPalace.getInity()+(councilPalace.getOffsetY()*(position/8))),
				(councilPalace.getFinaly()+(councilPalace.getOffsetY()*(position/8)))
				);
		return temp.resize(boardResizeFactor);
	}
	
	public static Rectangle getProdRightFamSpace(double boardResizeFactor,int position) {
		Rectangle temp = new Rectangle(
				(productionRightFam.getInitx()+(productionRightFam.getOffsetX()*(position%8))),
				(productionRightFam.getFinalx()+(productionRightFam.getOffsetX()*(position%8))),
				(productionRightFam.getInity()+(productionRightFam.getOffsetY()*(position/8))),
				(productionRightFam.getFinaly()+(productionRightFam.getOffsetY()*(position/8)))
				);
		return temp.resize(boardResizeFactor);
	}
	
	public static Rectangle getHarvestRightFamSpace(double boardResizeFactor,int position) {
		Rectangle temp = new Rectangle(
				(harvestRightFam.getInitx()+(harvestRightFam.getOffsetX()*(position%8))),
				(harvestRightFam.getFinalx()+(harvestRightFam.getOffsetX()*(position%8))),
				(harvestRightFam.getInity()+(harvestRightFam.getOffsetY()*(position/8))),
				(harvestRightFam.getFinaly()+(harvestRightFam.getOffsetY()*(position/8)))
				);
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getCardTerritorySpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getCharacterSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += (cardDevelopmentOffsetX + cardDevelopmentWidth);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getCardBuildingSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 2);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getCardVentureSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 3);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public static Rectangle getCardDevelopmentSpace(double boardResizeFactor, int towerNumber, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * towerNumber);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

}
