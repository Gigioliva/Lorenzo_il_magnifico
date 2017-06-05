package it.polimi.ingsw.ps22.client.gui;

public class AdaptiveLayout {
	private static final Rectangle orangeDice = new Rectangle(1876,2005,3414,3540);
	private static final Rectangle whiteDice = new Rectangle(1634,1762,3414,3540);
	private static final Rectangle blackDice = new Rectangle(1390,1520,3414,3540);
	private static final Rectangle councilPalace = new Rectangle(1410,1830,2140,2290);
	private static final int cardDevelopmentStartOffsetX = 183;
	private static final int cardDevelopmentStartOffsetY = 193;
	private static final int cardDevelopmentHeight = 446;
	private static final int cardDevelopmentWidth = 275;
	private static final int cardDevelopmentOffsetX = 231;
	private static final int cardDevelopmentOffsetY = 24;
	
	
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
		towerSlot=3-towerSlot;
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		//towerNumber--;
		//towerSlot--;
		inity+=((cardDevelopmentOffsetY+cardDevelopmentHeight)*towerSlot);
		Rectangle temp = 
				new Rectangle(initx,initx+cardDevelopmentWidth,inity,inity+cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}
	
	public static Rectangle getCardDevelopmentSpace(double resizeFactor, int towerNumber, int towerSlot) {
		int initx = cardDevelopmentStartOffsetX;
		int inity = cardDevelopmentStartOffsetY;
		//towerNumber--;
		//towerSlot--;
		initx+=((cardDevelopmentOffsetX+cardDevelopmentWidth)*towerNumber);
		inity+=((cardDevelopmentOffsetY+cardDevelopmentHeight)*towerSlot);
		Rectangle temp = 
				new Rectangle(initx,initx+cardDevelopmentWidth,inity,inity+cardDevelopmentHeight);
		return temp.resize(resizeFactor);
	}
	
}
