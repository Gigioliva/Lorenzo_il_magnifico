package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.player.Player;

public class AskCard extends MessageAsk{
	private HashMap<String,ArrayList<DevelopmentCard>> possibleCard;
	private Player player;
	
	public AskCard(HashMap<String,ArrayList<DevelopmentCard>> possibleCard, Player player){
		this.player=player;
		this.possibleCard=possibleCard;
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

}
