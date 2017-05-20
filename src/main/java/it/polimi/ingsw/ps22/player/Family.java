package it.polimi.ingsw.ps22.player;

import it.polimi.ingsw.ps22.model.Color;

public class Family {
	private Color color;
	private Player player;
	private int value;  //trova il modo di settarlo in base a BonusAcc e SpecBonus

	public Family(Color color, Player player) {
		this.color = color;
		this.player = player;
	}

	public Color getColor() {
		return color;
	}

	public Player getPlayer() {
		return player;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int value){
		this.value=value;
		
	}
	
	public void incrementValue(int value){
		this.value=this.value+value;
	}

}
