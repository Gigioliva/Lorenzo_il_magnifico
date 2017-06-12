package it.polimi.ingsw.ps22.client.gui;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;

public class CardPath {
	
	public static String getDevCardPathname(DevelopmentCard cardDev) {
		StringBuilder name = new StringBuilder(deleteSpaces(cardDev.getName()));
		name = new StringBuilder(addPNG(name.toString()));
		return name.toString().toLowerCase();
	}

	public static String deleteSpaces(String toModify) {
		StringBuilder temp = new StringBuilder(toModify);
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i)==' ') {
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
