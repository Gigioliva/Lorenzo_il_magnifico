package it.polimi.ingsw.ps22.client.gui;

public class AdaptiveLayout {

	private final Rectangle orangeDice;
	private final Rectangle whiteDice;
	private final Rectangle blackDice;
	private final Rectangle councilPalaceButton;
	private final Rectangle councilPalace;
	private final Rectangle harvestLeftButton;
	private final Rectangle harvestRightButton;
	private final Rectangle harvestRightFam;
	private final Rectangle harvestRightCover;
	private final Rectangle productionLeftButton;
	private final Rectangle productionRightButton;
	private final Rectangle productionRightFam;
	private final Rectangle productionRightCover;
	private final Rectangle market1;
	private final Rectangle market2;
	private final Rectangle market3;
	private final Rectangle market4;
	private final Rectangle marketCover3;
	private final Rectangle marketCover4;
	private final Rectangle playerGrid;
	private final Rectangle church1;
	private final Rectangle church2;
	private final Rectangle church3;
	private final Rectangle faithSlotFrom1To3;
	private final Rectangle faithSlotFrom3To5;
	private final Rectangle faithSlotFrom6To15;
	private final Rectangle victorySlot0;
	private final Rectangle victorySlotFrom1To19;
	private final Rectangle victorySlot20;
	private final Rectangle victorySlotFrom21To49;
	private final Rectangle victorySlot50;
	private final Rectangle victorySlotFrom51To69;
	private final Rectangle victorySlot70;
	private final Rectangle victorySlotFrom71To99;
	private final Rectangle familiar;
	private final Rectangle militarySlotFrom1;
	private final Rectangle militarySlot0;
	private final Rectangle excommSlot1;
	private final Rectangle excommSlot2;
	private final Rectangle excommSlot3;
	private final Rectangle playerNameSlot;
	private final Rectangle turnNumberSlot;
	private final Rectangle isYourTurnSlot;
	private final Rectangle closeGameDim;
	private final Rectangle endTurnDim;
	private final Rectangle chatSlot;
	private final Rectangle cheatSlot;
	private final int cardDevelopmentStartOffsetX;
	private final int cardDevelopmentStartOffsetY;
	private final int cardDevelopmentHeight;
	private final int cardDevelopmentWidth;
	private final int cardDevelopmentOffsetX;
	private final int cardDevelopmentOffsetY;
	private final int playerGridOffsetY;
	private final int inChurchOffsetX;
	private final int outChurchOffsetX;
	private final int victoryPointOffsetXY;
	private final int familiarOffsetX;
	private final int familiarOffsetY;
	private final int militaryOffsetY;
	private final int addingMilitaryOffsetY;

	private static AdaptiveLayout instanceSing = null;

	private AdaptiveLayout() {
		orangeDice = new Rectangle(1876, 2005, 3414, 3540);
		whiteDice = new Rectangle(1634, 1762, 3414, 3540);
		blackDice = new Rectangle(1390, 1520, 3414, 3540);
		councilPalaceButton = new Rectangle(1410, 1830, 2140, 2290);
		councilPalace = new Rectangle(1410, 1470, 2140, 2200);
		harvestLeftButton = new Rectangle(160, 370, 3360, 3500);
		harvestRightButton = new Rectangle(500, 920, 3360, 3500);
		harvestRightFam = new Rectangle(590, 650, 3400, 3460);
		harvestRightCover = new Rectangle(395, 1025, 3315, 3595);
		productionLeftButton = new Rectangle(160, 370, 3050, 3190);
		productionRightButton = new Rectangle(500, 920, 3050, 3190);
		productionRightFam = new Rectangle(590, 650, 3085, 3145);
		productionRightCover = new Rectangle(395, 1025, 3000, 3280);
		market1 = new Rectangle(1431, 1581, 3020, 3120);
		market2 = new Rectangle(1665, 1815, 3020, 3120);
		market3 = new Rectangle(1890, 2040, 3090, 3190);
		market4 = new Rectangle(2065, 2215, 3260, 3360);
		marketCover3 = new Rectangle(1860, 2070, 3035, 3265);
		marketCover4 = new Rectangle(2030, 2250, 3205, 3440);
		playerGrid = new Rectangle(2042, 2148, 2000, 2106);
		church1 = new Rectangle(508, 685, 2263, 2623);
		church2 = new Rectangle(704, 883, 2307, 2654);
		church3 = new Rectangle(901, 1078, 2263, 2623);
		faithSlotFrom1To3 = new Rectangle(165, 285, 2750, 2830);
		faithSlotFrom3To5 = new Rectangle(540, 660, 2750, 2830);
		faithSlotFrom6To15 = new Rectangle(1035, 1185, 2750, 2830);
		victorySlot0 = new Rectangle(50, 130, 15, 135);
		victorySlotFrom1To19 = new Rectangle(195, 275, 5, 125);
		victorySlot20 = new Rectangle(2520, 2600, 15, 135);
		victorySlotFrom21To49 = new Rectangle(2525, 2645, 170, 250);
		victorySlot50 = new Rectangle(2520, 2600, 3610, 3730);
		victorySlotFrom51To69 = new Rectangle(2350, 2470, 3660, 3740);
		victorySlot70 = new Rectangle(30, 150, 3630, 3710);
		victorySlotFrom71To99 = new Rectangle(5, 125, 3490, 3570);
		familiar = new Rectangle(485, 635, 360, 460);
		militarySlotFrom1 = new Rectangle(2280, 2400, 3240, 3320);
		militarySlot0 = new Rectangle(2280, 2400, 3400, 3480);
		excommSlot1 = new Rectangle(534, 654, 2298, 2478);
		excommSlot2 = new Rectangle(732, 852, 2330, 2510);
		excommSlot3 = new Rectangle(930, 1050, 2298, 2478);
		turnNumberSlot = new Rectangle(5650, 6600, 240, 420);
		playerNameSlot = new Rectangle(5650, 6600, 50, 230);
		isYourTurnSlot = new Rectangle(5650, 6600, 430, 550);
		closeGameDim = new Rectangle(0, 700, 0, 300);
		endTurnDim = new Rectangle(0, 700, 0, 400);
		chatSlot = new Rectangle(5650, 6550, 600, 1500);
		cheatSlot = new Rectangle(5000, 5600, 2900, 3200);
		cardDevelopmentStartOffsetX = 183;
		cardDevelopmentStartOffsetY = 193;
		cardDevelopmentHeight = 446;
		cardDevelopmentWidth = 275;
		cardDevelopmentOffsetX = 231;
		cardDevelopmentOffsetY = 24;
		playerGridOffsetY = 136;
		inChurchOffsetX = 186;
		outChurchOffsetX = 122;
		victoryPointOffsetXY = 120;
		familiarOffsetX = 508;
		familiarOffsetY = 473;
		militaryOffsetY = 120;
		addingMilitaryOffsetY = 15;
	}
	
	public static AdaptiveLayout instance() {
		if (instanceSing == null) {
			instanceSing = new AdaptiveLayout();
		}
		return instanceSing;
	}

	public Rectangle getExcommSlot(double boardResizeFactor, int era) {
		if (era == 1)
			return excommSlot1.resize(boardResizeFactor);
		if (era == 2)
			return excommSlot2.resize(boardResizeFactor);
		if (era == 3)
			return excommSlot3.resize(boardResizeFactor);
		return null;
	}

	public Rectangle getCloseGameDim(double boardResizeFactor) {
		return closeGameDim.resize(boardResizeFactor);
	}
	
	public Rectangle getEndTurnDim(double boardResizeFactor) {
		return endTurnDim.resize(boardResizeFactor);
	}
	
	public Rectangle getCheatButtonSlot(double boardResizeFactor) {
		return cheatSlot.resize(boardResizeFactor);
	}
	
	public Rectangle getChatSlot(double boardResizeFactor) {
		return chatSlot.resize(boardResizeFactor);
	}
	
	public Rectangle getTurnNumberSlot(double boardResizeFactor) {
		return turnNumberSlot.resize(boardResizeFactor);
	}
	
	public Rectangle getPlayerNameSlot(double boardResizeFactor) {
		return playerNameSlot.resize(boardResizeFactor);
	}
	
	public Rectangle getIsYourTurnSlot(double boardResizeFactor) {
		return isYourTurnSlot.resize(boardResizeFactor);
	}
	
	public Rectangle getMilitarySlotSpace(double boardResizeFactor, int slot) {
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

	public Rectangle getFamiliarSpace(double resizeFactor, int towerNumber, int towerSlot) {
		// deve essere da 0 a tre per entrambi
		towerSlot = 3 - towerSlot;
		Rectangle temp = new Rectangle((familiar.getInitx() + (towerNumber * familiarOffsetX)),
				(familiar.getFinalx() + (towerNumber * familiarOffsetX)),
				(familiar.getInity() + (towerSlot * familiarOffsetY)),
				(familiar.getFinaly() + (towerSlot * familiarOffsetY)));
		return temp.resize(resizeFactor);
	}

	public Rectangle getVictorySlotSpace(double boardResizeFactor, int slot) {
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

	public Rectangle getFaithSlotSpace(double boardResizeFactor, int slot) {
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

	public Rectangle getChurchSpace(double boardResizeFactor, int era) {
		// era deve essere 1, 2 o 3
		if (era == 1)
			return church1.resize(boardResizeFactor);
		if (era == 2)
			return church2.resize(boardResizeFactor);
		if (era == 3)
			return church3.resize(boardResizeFactor);
		return null;
	}

	public Rectangle getPlayerGridSpace(double boardResizeFactor, int position) {
		// da 0 a 3 la position
		// position--;
		Rectangle temp = new Rectangle(playerGrid.getInitx(), playerGrid.getFinalx(),
				(playerGrid.getInity() + (position * playerGridOffsetY)),
				(playerGrid.getFinaly() + (position * playerGridOffsetY)));
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getProdLeftSpace(double boardResizeFactor) {
		return productionLeftButton.resize(boardResizeFactor);
	}

	public Rectangle getProdRightSpace(double boardResizeFactor) {
		return productionRightButton.resize(boardResizeFactor);
	}

	public Rectangle getProdRightCover(double boardResizeFactor) {
		return productionRightCover.resize(boardResizeFactor);
	}

	public Rectangle getHarvestLeftSpace(double boardResizeFactor) {
		return harvestLeftButton.resize(boardResizeFactor);
	}

	public Rectangle getHarvestRightSpace(double boardResizeFactor) {
		return harvestRightButton.resize(boardResizeFactor);
	}

	public Rectangle getHarvestRightCover(double boardResizeFactor) {
		return harvestRightCover.resize(boardResizeFactor);
	}

	public Rectangle getMarket1Space(double boardResizeFactor) {
		return market1.resize(boardResizeFactor);
	}

	public Rectangle getMarket2Space(double boardResizeFactor) {
		return market2.resize(boardResizeFactor);
	}

	public Rectangle getMarket3Space(double boardResizeFactor) {
		return market3.resize(boardResizeFactor);
	}

	public Rectangle getMarket3Cover(double boardResizeFactor) {
		return marketCover3.resize(boardResizeFactor);
	}

	public Rectangle getMarket4Cover(double boardResizeFactor) {
		return marketCover4.resize(boardResizeFactor);
	}

	public Rectangle getMarket4Space(double boardResizeFactor) {
		return market4.resize(boardResizeFactor);
	}

	public Rectangle getOrangeDiceSpace(double boardResizeFactor) {
		return orangeDice.resize(boardResizeFactor);
	}

	public Rectangle getBlackDiceSpace(double boardResizeFactor) {
		return blackDice.resize(boardResizeFactor);
	}

	public Rectangle getWhiteDiceSpace(double boardResizeFactor) {
		return whiteDice.resize(boardResizeFactor);
	}

	public Rectangle getCouncilPalaceSpace(double boardResizeFactor) {
		return councilPalaceButton.resize(boardResizeFactor);
	}

	public Rectangle getCouncilPalaceFamSpace(double boardResizeFactor, int position) {
		Rectangle temp = new Rectangle((councilPalace.getInitx() + (councilPalace.getOffsetX() * (position % 8))),
				(councilPalace.getFinalx() + (councilPalace.getOffsetX() * (position % 8))),
				(councilPalace.getInity() + (councilPalace.getOffsetY() * (position / 8))),
				(councilPalace.getFinaly() + (councilPalace.getOffsetY() * (position / 8))));
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getProdRightFamSpace(double boardResizeFactor, int position) {
		Rectangle temp = new Rectangle(
				(productionRightFam.getInitx() + (productionRightFam.getOffsetX() * (position % 8))),
				(productionRightFam.getFinalx() + (productionRightFam.getOffsetX() * (position % 8))),
				(productionRightFam.getInity() + (productionRightFam.getOffsetY() * (position / 8))),
				(productionRightFam.getFinaly() + (productionRightFam.getOffsetY() * (position / 8))));
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getHarvestRightFamSpace(double boardResizeFactor, int position) {
		Rectangle temp = new Rectangle((harvestRightFam.getInitx() + (harvestRightFam.getOffsetX() * (position % 8))),
				(harvestRightFam.getFinalx() + (harvestRightFam.getOffsetX() * (position % 8))),
				(harvestRightFam.getInity() + (harvestRightFam.getOffsetY() * (position / 8))),
				(harvestRightFam.getFinaly() + (harvestRightFam.getOffsetY() * (position / 8))));
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getCardTerritorySpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getCharacterSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += (cardDevelopmentOffsetX + cardDevelopmentWidth);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getCardBuildingSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 2);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getCardVentureSpace(double boardResizeFactor, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * 3);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

	public Rectangle getCardDevelopmentSpace(double boardResizeFactor, int towerNumber, int towerSlot) {
		towerSlot = 3 - towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		initx += ((cardDevelopmentOffsetX + cardDevelopmentWidth) * towerNumber);
		inity += ((cardDevelopmentOffsetY + cardDevelopmentHeight) * towerSlot);
		Rectangle temp = new Rectangle(initx, initx + cardDevelopmentWidth, inity, inity + cardDevelopmentHeight);
		return temp.resize(boardResizeFactor);
	}

}
