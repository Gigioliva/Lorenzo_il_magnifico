package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.player.Player;

public class AskEffect extends MessageAsk {
	private HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect;
	private ProductionAction prodAction;
	private Player player;
	
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
	
	public HashMap<DevelopmentCard,ArrayList<ActionEffect>> getListEffect(){
		return listEffect;
	}

	public ProductionAction getProdAction() {
		return prodAction;
	}

	public Player getPlayer() {
		return player;
	}
	

}
