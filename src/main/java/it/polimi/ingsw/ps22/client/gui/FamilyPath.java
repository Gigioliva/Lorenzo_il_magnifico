package it.polimi.ingsw.ps22.client.gui;


import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class FamilyPath {

	public static String getFamilyPathname(java.awt.Color playerColor, Color fam) {
		StringBuilder name = new StringBuilder(
				"./image/family/" + playerColor.toString() + "_" + fam.toString()
				);
		name = new StringBuilder(CardPath.addPNG(name.toString()));
		return name.toString().toLowerCase();
	}
	
}