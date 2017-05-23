package it.polimi.ingsw.ps22.board;

import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps22.model.Color;

public class Dice {
	private HashMap<Color, Integer> dice; 
	
	public Dice(){
		Color[] colors=Color.values();
		for(Color el: colors){
			if(el!=Color.NEUTRAL){
				dice.put(el, 0);
			}
		}
		this.setDice();
	}
	
	public int getDice(Color color){
		if(dice.containsKey(color))
			return dice.get(color);
		else
			return 0;
	}
	
	public void setDice(){
		Color[] colors=Color.values();
		for(Color el: colors){
			if(el!=Color.NEUTRAL){
				Random random=new Random();
				int value=random.nextInt(7);
				dice.put(el, value);
			}
		}
	}

}
