package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerLeader;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This message is used to ask the player which {@link CardLeader}
 * he wants. This messages are usually sent during the initial
 * draft of the leader cards
 *
 */
public class AskLeader extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private Player player;
	private ArrayList<CardLeader> leaders;
	
	/**
	 * 
	 * @param leaders the {@link CardLeader} among which the player has to choose
	 * @param player the target player
	 */
	public AskLeader(ArrayList<CardLeader> leaders, Player player){
		super(player.getUsername());
		this.player=player;
		this.leaders = leaders;
		StringBuilder temp=new StringBuilder();
		temp.append("Choose one card: \n");
		for(CardLeader el: leaders){
			temp.append(el.getName() +"\n");
		}
		setString(temp.toString());	
	}
	
	/**
	 * 
	 * @return the target {@link Player}
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * 
	 * @return an {@link ArrayList} containing the names of the leaders
	 */
	public ArrayList<String> getLeaders(){
		ArrayList<String> lead = new ArrayList<String>();
		for(CardLeader c: leaders){
			lead.add(c.getName());
		}
		return lead;
	}
	
	/**
	 * 
	 * @return an {@link ArrayList} containing the leaders
	 */
	public ArrayList<CardLeader> getLead(){
		return leaders;
	}
	
	/**
	 * 
	 * @param str the text of the message
	 * @param id the id of the message
	 * @param leaders the leaders among which the
	 * choice has to be made
	 */
	public AskLeader(String str, int id, ArrayList<CardLeader> leaders){
		super(str,id);
		this.leaders=new ArrayList<CardLeader>();
		for(CardLeader el: leaders){
			this.leaders.add(el.clone());
		}
	}
	
	/**
	 * This method will be used to apply default answers to messages 
	 * which dont't have answer by the player in a given time clock.
	 * In this case a random card will be assigned to the player
	 */
	@Override
	public void applyDefault(Model model){
		AnswerLeader ans=new AnswerLeader(this.getId(), leaders.get(0).getName());
		ans.applyAnswer(model);
	}
	
	public AskLeader accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
}
