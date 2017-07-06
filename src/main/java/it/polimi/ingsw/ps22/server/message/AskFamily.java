package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskFamily extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private Player player;
	
	public AskFamily(Player player){
		super(player.getUsername());
		this.player=player;
		String ask="A quale famigliare assegni valore 6?";
		setString(ask);
	}
	
	public AskFamily(String str, int id){
		super(str,id);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public AskFamily accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
