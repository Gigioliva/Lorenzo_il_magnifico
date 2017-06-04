package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.Visitor;

public class AskExcomm extends MessageAsk{

	private static final long serialVersionUID = 1L;
	private transient Player player;

	public AskExcomm() {
		setString("Appoggi la chiesa?");
	}
	
	public AskExcomm(String str,int id) {
		super(str,id);
	}

	public void addPlayer(Player player) {
		this.player=player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public AskExcomm accept(Visitor visitor){
		return visitor.visit(this);
	}

}
