package it.polimi.ingsw.ps22.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.model.Color;

public class Dice {
	private HashMap<Color, Integer> dice; 
	
	public Dice(){
		Color[] colors=Color.values();
		for(Color el: colors){
			if(el!=Color.NEUTRAL){
				dice.put(el, 0);  //invece di 0 fai random
			}
		}
	}
	
	public int getDice(Color color){
		return dice.get(color);
	}

}
