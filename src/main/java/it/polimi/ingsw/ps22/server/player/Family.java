package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.model.Color;

public class Family implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private Player player;
	private int value;
	private transient boolean placed=false;

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
		if(player.getSpecBonus().returnBool("AllFamilyCol5") && color!=Color.NEUTRAL)
			this.value=5;
		if(player.getSpecBonus().returnBool("Neutral+3") && color==Color.NEUTRAL)
			this.value=this.value+3;
		if(player.getSpecBonus().returnBool("FamilyCol+2") && color!=Color.NEUTRAL)
			this.value=this.value+2;
		this.value=this.value-player.getBonusAcc().getBonus("IncrementDice").getQuantity();
	}
	
	public void incrementValue(int value){
		this.value=this.value+value;
	}
	
	public void setPlaced(boolean placed){
		this.placed=placed;
	}
	public boolean isPlaced(){
		return placed;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(color.toString() + " of value " + value + "\n");
		
		return str.toString();
	}

}