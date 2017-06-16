package it.polimi.ingsw.ps22.client.gui;


import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class FamilyPath {

	public static String getFamilyPathname(Player player, Family fam) {
		StringBuilder name = new StringBuilder(
				"./image/family/" + player.getColor().toString()+"_"+fam.getColor().toString()
				);
		name = new StringBuilder(CardPath.addPNG(name.toString()));
		return name.toString().toLowerCase();
	}
	
}