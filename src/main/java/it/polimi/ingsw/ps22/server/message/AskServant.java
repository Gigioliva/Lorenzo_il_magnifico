package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.action.Action;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskServant extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private Action Action;
	
	public AskServant(Action Action, Player player){
		super(player.getUsername());
		this.Action=Action;
		setString("Quanti servitori vuoi spendere? ");
	}
	
	public AskServant(String str, int id){
		super(str,id);
	}
	
	public Action getAction(){
		return Action;
	}
	
	public AskServant accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
