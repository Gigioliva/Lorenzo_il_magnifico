package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * An effect is to choose a card leader and copy its effects.
 * This message implements the question of asking to the player
 * which {@link CardLeader} he wants to copy
 *
 */
public class AskCopyLeader extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private CardLeader leader;
	private Player player;
	private ArrayList<CardLeader> leaders;
	
	/**
	 * It creates a new {@link AskCopyLeader} message
	 * @param leaders the leaders among which the choice has to be made
	 * @param leader the leader that invoked the effect of copying a leader
	 * @param player the target player
	 */
	public AskCopyLeader(ArrayList<CardLeader> leaders, CardLeader leader, Player player){
		super(player.getUsername());
		this.leader=leader;
		this.player=player;
		this.leaders=leaders;
		StringBuilder temp=new StringBuilder();
		temp.append("Scegli una carta da copiare ");
		for(CardLeader el: leaders){
			temp.append(el.toString() + "\n");
		}
		setString(temp.toString());
	}
	
	/**
	 * 
	 * @param str the text of the message
	 * @param id the id of the message
	 * @param leaders the leaders among which the choice has to be made
	 */
	public AskCopyLeader(String str, int id, ArrayList<CardLeader> leaders){
		super(str,id);
		this.leaders=new ArrayList<CardLeader>();
		for(CardLeader el: leaders){
			this.leaders.add(el.clone());
		}
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
	 * @return the leader that invoked the effect of copying a leader
	 */
	public CardLeader getLeader(){
		return leader;
	}
	
	/**
	 * 
	 * @return the leaders among which the choice has to be made
	 */
	public ArrayList<CardLeader> getLeaders() {
		return leaders;
	}

	public AskCopyLeader accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
}
