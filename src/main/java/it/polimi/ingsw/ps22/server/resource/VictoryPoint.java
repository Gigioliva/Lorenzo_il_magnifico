package it.polimi.ingsw.ps22.server.resource;

public class VictoryPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public VictoryPoint(int victorypoint){
		super(victorypoint);
	}
	
	@Override
	public String getName() {
		return "VictoryPoint";
	}
	
	@Override
	public VictoryPoint clone() {
		return new VictoryPoint(this.getQuantity());
	}
}
