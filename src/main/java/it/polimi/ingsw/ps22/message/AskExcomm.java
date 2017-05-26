package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.player.Player;

public class AskExcomm extends MessageAsk {
	private ArrayList<Player> players;

	public AskExcomm() {
		setString("Vuoi essere scomunicato?");
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void applyAsk() {
		if (!players.isEmpty()){
			model.notifyAsk(this);
		}else{
			model.notifyModel();
		}
	}

}
