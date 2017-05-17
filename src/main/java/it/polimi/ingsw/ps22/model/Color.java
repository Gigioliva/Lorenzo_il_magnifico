package it.polimi.ingsw.ps22.model;


public enum Color {
	BLACK, WHITE, ORANGE, NEUTRAL;
	
	public static Color Conversion(String input){ 
		return Enum.valueOf(Color.class, input.toUpperCase());	
		//conversione della stringain Enum
	}
}