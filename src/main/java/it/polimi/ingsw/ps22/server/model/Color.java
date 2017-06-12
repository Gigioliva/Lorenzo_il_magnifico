package it.polimi.ingsw.ps22.server.model;

import java.io.Serializable;

public enum Color implements Serializable{
	BLACK(java.awt.Color.BLACK), WHITE(java.awt.Color.WHITE), ORANGE(java.awt.Color.ORANGE), NEUTRAL(java.awt.Color.GRAY);
	
	private java.awt.Color color;
	
	private Color(java.awt.Color color){
		this.color=color;
	}
	
	public static Color Conversion(String input){ 
		return Enum.valueOf(Color.class, input.toUpperCase());
	}
	
	public java.awt.Color getColor(){
		return this.color;
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