package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.action.CardAction;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.Visitor;

public class AskCard extends MessageAsk{
	
	private static final long serialVersionUID = 1L;
	private transient HashMap<String,ArrayList<DevelopmentCard>> possibleCard;
	private transient Player player;
	private transient CardAction cardAction;
	
	public AskCard(HashMap<String,ArrayList<DevelopmentCard>> possibleCard, Player player, CardAction cardAction){
		this.player=player;
		this.possibleCard=possibleCard;
		this.cardAction=cardAction;
		StringBuilder str=new StringBuilder();
		for(String el: possibleCard.keySet()){
			str.append("Le carte di tipo "+ el + " che puoi prendere sono: \n");
			for(DevelopmentCard card: possibleCard.get(el)){
				str.append(card.getName() + "\n");
			}
		}
		str.append("Quale scegli?: "); //chiedi il tipo di carta e il nome della carta
		setString(str.toString());
	}
	
	public AskCard(String str, int id){
		super(str,id);
	}
	
	public HashMap<String,ArrayList<DevelopmentCard>> getPossibleCard(){
		return possibleCard;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public CardAction getCardAction(){
		return cardAction;
	}
	
	public AskCard accept(Visitor visitor){
		return visitor.visit(this);
	}

}
