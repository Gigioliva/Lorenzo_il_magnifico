package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.model.Color;

/**
 * Is the concrete representation of the Family in the game
 */
public class Family implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private Player player;
	private int value;
	private boolean placed=false;

	/**
	 * Constructor of Family
	 * @param color is the color of the Family associated to a relative dice
	 * @param player is the owner of the family 
	 */
	public Family(Color color, Player player) {
		this.color = color;
		this.player = player;
	}
	
	public Family clone(Player player) {
		Family temp = new Family(this.color, player);
		temp.value=this.value;
		temp.placed=this.placed;
		return temp;
	}

	/**
	 * @return the color of this {@link Family}
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the player owner of this {@link Family}
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return the value of the {@link Action} that this {@link Family} can do
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Method that set the correct value to the {@link Family}
	 * @param value is the value added by the the bonus that the player have
	 */
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
	
	/**
	 * Increment action value of the number given by parameters
	 * @param valueis the value of the increment
	 */
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

		str.append(color.toString() + " of value " + value);
		
		return str.toString();
	}

}
