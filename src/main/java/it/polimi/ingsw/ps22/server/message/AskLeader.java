package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskLeader extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private transient Player player;
	private transient ArrayList<CardLeader> leaders;
	
	public AskLeader(ArrayList<CardLeader> leaders, Player player){
		this.player=player;
		this.leaders = leaders;
		StringBuilder temp=new StringBuilder();
		temp.append("Choice one card: \n");
		for(CardLeader el: leaders){
			temp.append(el.getName() +"\n");
		}
		setString(temp.toString());	
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public ArrayList<String> getLeaders(){
		ArrayList<String> lead = new ArrayList<String>();
		for(CardLeader c: leaders){
			lead.add(c.getName());
		}
		return lead;
	}
	
	public AskLeader(String str, int id){
		super(str,id);
	}
	
	public AskLeader accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
}
