package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Point}, it implements a
 * representation of the Victory Point.
 */
public class VictoryPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link VictoryPoint}
	 * @param victorypoint is the quantity to which the point is set
	 */
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
