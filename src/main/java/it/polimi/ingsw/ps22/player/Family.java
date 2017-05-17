package it.polimi.ingsw.ps22.player;


import it.polimi.ingsw.ps22.model.Color;

public class Family {
	private Color color;
	private Player player;
	
	public Family(Color color, Player player){
		this.color=color;
		this.player=player;
	}
	
	public Color getColor(){
		return color;
	}
	public Player getPlayer(){
		return player;
	}

}
