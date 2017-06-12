package it.polimi.ingsw.ps22.server.model;


public enum ColorPlayer {
	RED(1, java.awt.Color.RED), BLUE(2, java.awt.Color.BLUE), GREEN(3, java.awt.Color.GREEN), YELLOW(4,
			java.awt.Color.YELLOW);

	private int x;

	private java.awt.Color color;

	private ColorPlayer(int x, java.awt.Color color) {
		this.x = x;
		this.color = color;
	}

	public static ColorPlayer getColor(int x) {
		for (ColorPlayer el : ColorPlayer.values()) {
			if (el.x == x) {
				return el;
			}
		}
		return null;
	}
	
	public java.awt.Color getColor(){
		return this.color;
	}

}
