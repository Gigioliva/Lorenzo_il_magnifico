package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerCopyLeader;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

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

	public AskCopyLeader accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public AnswerCopyLeader accept(VisitorB visitor){
		return visitor.visit(this);
	}
}
