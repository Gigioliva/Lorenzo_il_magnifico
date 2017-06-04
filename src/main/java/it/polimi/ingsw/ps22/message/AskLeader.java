package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.card.CardLeader;
import it.polimi.ingsw.ps22.player.Player;

public class AskLeader extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private transient Player player;
	
	public AskLeader(ArrayList<CardLeader> leaders, Player player){
		this.player=player;
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
	
	public AskLeader(String str, int id){
		super(str,id);
	}
}
