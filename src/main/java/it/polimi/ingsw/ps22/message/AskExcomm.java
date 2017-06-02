package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.view.Visitor;

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
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}

}
