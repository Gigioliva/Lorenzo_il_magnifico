package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.Visitor;

public class AskCopyLeader extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private transient CardLeader leader;
	private transient Player player;
	private transient ArrayList<CardLeader> leaders;
	
	public AskCopyLeader(ArrayList<CardLeader> leaders, CardLeader leader, Player player){
		super();
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
	
	public AskCopyLeader(String str, int id){
		super(str,id);
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public CardLeader getLeader(){
		return leader;
	}
	
	public ArrayList<CardLeader> getLeaders() {
		return leaders;
	}

	public AskCopyLeader accept(Visitor visitor){
		return visitor.visit(this);
	}
}
