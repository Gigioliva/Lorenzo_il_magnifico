package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerCouncilPrivilege;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * The player is asked to convert the council privilege to resources
 *
 */
public class AskCouncilPrivilege extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private int numChoice;
	private Player player;
	
	/**
	 * 
	 * @param numChoice the number of different choices the player has to make
	 * @param player the target player
	 */
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
	
	/**
	 * 
	 * @param str the text of the message
	 * @param id the id of the message
	 * @param num the number of choices the player has to make
	 */
	public AskCouncilPrivilege(String str, int id, int num){
		super(str,id);
		this.numChoice=num;
	}
	
	/**
	 * 
	 * @return the target player
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * 
	 * @return the number of choices the player has to make
	 */
	public int getNumChoice(){
		return numChoice;
	}
	
	@Override
	public void applyDefault(Model model) {
		ArrayList<Integer> eff = new ArrayList<>();
		for (int i = 0; i < numChoice; i++) {
			eff.add(i+1);
		}
		AnswerCouncilPrivilege ans = new AnswerCouncilPrivilege(this.getId(), eff);
		ans.applyAnswer(model);
	}
	
	public AskCouncilPrivilege accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
	
}
