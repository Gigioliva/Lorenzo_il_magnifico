package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskCouncilPrivilege extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private int numChoice;
	private Player player;
	
	public AskCouncilPrivilege(int numChoice, Player player){
		super(player.getUsername());
		this.numChoice=numChoice;
		this.player=player;
		StringBuilder str = new StringBuilder();
		str.append("cosa vuoi in cambio?: " + numChoice + " scelte [separati da spazio]\n");
		str.append("1: 1 legno e una pietra\n");
		str.append("2: 2 servitori\n");
		str.append("3: 2 monete\n");
		str.append("4: 2 punti militari\n");
		str.append("5: 1 punto fede\n");
		setString(str.toString());
	}
	
	public AskCouncilPrivilege(String str, int id, int num){
		super(str,id);
		this.numChoice=num;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getNumChoice(){
		return numChoice;
	}
	
	public AskCouncilPrivilege accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
	
}
