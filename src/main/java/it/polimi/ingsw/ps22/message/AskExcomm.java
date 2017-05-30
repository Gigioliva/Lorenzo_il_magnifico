package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.player.Player;

public class AskExcomm extends MessageAsk {
	private Player player;

	public AskExcomm() {
		setString("Vuoi essere scomunicato?");
	}

	public void addPlayer(Player player) {
		this.player=player;
	}

	public Player getPlayer() {
		return player;
	}

}
