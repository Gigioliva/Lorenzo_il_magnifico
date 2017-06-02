package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.view.Visitor;

public class AskCouncilPrivilege extends MessageAsk {
	private int numChoice;
	private Player player;
	
	public AskCouncilPrivilege(int numChoice, Player player){
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
	
	public Player getPlayer(){
		return player;
	}
	
	public int getNumChoice(){
		return numChoice;
	}
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}
	
}
