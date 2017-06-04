package it.polimi.ingsw.ps22.server.resource;

public class VictoryPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public VictoryPoint(int victorypoint){
		super(victorypoint);
	}
	
	public String getName() {
		return "VictoryPoint";
	}
}
