package it.polimi.ingsw.ps22.client.gui;

import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.player.PersonalBoard;

public class CardPath {

	public static String getDevCardPathname(DevelopmentCard cardDev) {
		StringBuilder name = new StringBuilder("./image/developmentCard/"+deleteSpaces(cardDev.getName()));
		name = new StringBuilder(addPNG(name.toString()));
		return name.toString().toLowerCase();
	}
	
	public static String getLeaderCardPathname(CardLeader card) {
		StringBuilder name = new StringBuilder("./image/leaderCard/"+deleteSpaces(card.getName()));
		name = new StringBuilder(addPNG(name.toString()));
		return name.toString().toLowerCase();
	}
	

	public static String getExcommCardPathname(CardExcomm card) {
		StringBuilder name = new StringBuilder("./image/excommCard/"+deleteSpaces(card.getPathname()));
		return name.toString();
	}
	
	
	public static String getPersonalBoardPathname(PersonalBoard pers) {
		StringBuilder name = new StringBuilder("./image/personalboard/"+deleteSpaces(pers.getPathname()));
		return name.toString();
	}
	
	public static String deleteSpaces(String toModify) {
		StringBuilder temp = new StringBuilder(toModify);
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i)==' '|| temp.charAt(i)=='\'') {
				temp.deleteCharAt(i);
				i--;
			}
		}
		return temp.toString();
	}

	public static StringBuilder addPNG(String x) {
		StringBuilder temp = new StringBuilder(x);
		temp.append(".png");
		return temp;
	}
	
}
