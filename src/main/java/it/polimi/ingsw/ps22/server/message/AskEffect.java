package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.answer.AnswerEffect;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskEffect extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private transient HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect;
	private transient ProductionAction prodAction;
	private transient Player player;
	
	public AskEffect(HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect, ProductionAction prodAction, Player player){
		this.listEffect=listEffect;
		this.prodAction=prodAction;
		this.player=player;
		StringBuilder str=new StringBuilder();
		for(DevelopmentCard el: listEffect.keySet()){
			str.append("Gli effetti della carta " + el.getName() + " sono:\n");
			for(ActionEffect effect: listEffect.get(el)){
				str.append(effect.toString() + "\n");  //fare i toString degli effetti
			}
			str.append("\n");
		}
		str.append("Quale scegli?: "); //chiedi il nome della carta e il numero dell'effetto
		setString(str.toString());
	}
	
	public AskEffect(String str, int id){
		super(str,id);
	}
	
	public HashMap<DevelopmentCard,ArrayList<ActionEffect>> getListEffect(){
		return listEffect;
	}

	public ProductionAction getProdAction() {
		return prodAction;
	}

	public Player getPlayer() {
		return player;
	}
	
	public AskEffect accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public AnswerEffect accept(VisitorB visitor){
		return visitor.visit(this);
	}
}
