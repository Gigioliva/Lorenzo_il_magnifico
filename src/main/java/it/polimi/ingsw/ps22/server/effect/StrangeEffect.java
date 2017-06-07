package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.player.Player;

public class StrangeEffect implements PermanentEffect{
	
	private static final long serialVersionUID = 1L;
	private String type;
	
	public StrangeEffect(String type) {
		this.type = type;
	}

	@Override
	public void performEffect(Player player) {
		player.getSpecBonus().setSpecBonus(type);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(type);
		return str.toString();
	}
}