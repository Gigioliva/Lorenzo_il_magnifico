package it.polimi.ingsw.ps22.model;

import java.io.Serializable;

public enum Color implements Serializable{
	BLACK, WHITE, ORANGE, NEUTRAL;
	
	public static Color Conversion(String input){ 
		return Enum.valueOf(Color.class, input.toUpperCase());	
		//conversione della stringain Enum
	}
	
	@Override
	public String toString() {
		switch(this){
		case BLACK: return "Black";
		case WHITE: return "White";
		case ORANGE: return "Orange";
		case NEUTRAL: return "Neutral";
		default: throw new IllegalArgumentException();
		}
	}
}