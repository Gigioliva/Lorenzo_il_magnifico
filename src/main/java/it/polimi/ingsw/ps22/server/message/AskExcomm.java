package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

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
	
	public AskExcomm accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
