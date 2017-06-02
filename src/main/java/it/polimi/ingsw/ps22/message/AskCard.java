package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.action.CardAction;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.view.Visitor;

public class AskCard extends MessageAsk{
	private HashMap<String,ArrayList<DevelopmentCard>> possibleCard;
	private Player player;
	private CardAction cardAction;
	
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
	
	public HashMap<String,ArrayList<DevelopmentCard>> getPossibleCard(){
		return possibleCard;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public CardAction getCardAction(){
		return cardAction;
	}
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}

}
