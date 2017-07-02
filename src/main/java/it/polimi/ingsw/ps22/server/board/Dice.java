package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps22.server.model.Color;

/**
 * 
 * This class implements the concept of dices, that determine the action value of
 *  a the familiars of the same color.
 *
 */
public class Dice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Color, Integer> dice; 
	
	/**
	 * It creates the dices and sets randomly their values.
	 */
	public Dice(){
		this.dice=new HashMap<Color, Integer>();
		Color[] colors=Color.values();
		for(Color el: colors){
			if(el!=Color.NEUTRAL){
				dice.put(el, 0);
			}
		}
		this.setDice();
	}

	@Override
	public Dice clone() {
		Dice temp=new Dice();
		for (Color c: dice.keySet()) {
		temp.dice.put(c, dice.get(c));
		}
		return temp;
	}
	
	/**
	 * 
	 * @param color the color of the dice
	 * @return the value associated to that dice
	 */
	public int getDice(Color color){
		if(dice.containsKey(color))
			return dice.get(color);
		else
			return 0;
	}
	
	/**
	 * It sets randomly (uniformly) the value of the dices
	 */
	public void setDice(){
		Color[] colors=Color.values();
		for(Color el: colors){
			if(el!=Color.NEUTRAL){
				Random random=new Random();
				int value=random.nextInt(6)+1;
				dice.put(el, value);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Dice: \n");
		for(Color col: dice.keySet()){
			str.append("  " + col.toString() + " value: " + dice.get(col) + "\n");
		}
		return str.toString();
		
	}

}
