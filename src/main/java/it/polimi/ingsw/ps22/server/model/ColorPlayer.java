package it.polimi.ingsw.ps22.server.model;

public enum ColorPlayer {
	RED(1), BLUE(2), GREEN(3), YELLOW(4);
	
	private int x;
	
	private ColorPlayer(int x){
		this.x=x;
	}
	
	public static ColorPlayer getColor(int x){
		for(ColorPlayer el: ColorPlayer.values()){
			if(el.x==x){
				return el;
			}
		}
		return null;
	}

}
