package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Point}, it implements a
 * representation of the Military Point.
 */
public class MilitaryPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link MilitaryPoint}
	 * @param militarypoint is the quantity to which the point is set
	 */
	public MilitaryPoint(int militarypoint) {
		super(militarypoint);
	}
	
	@Override
	public String getName() {
		return "MilitaryPoint";
	}
	
	@Override
	public MilitaryPoint clone() {
		return new MilitaryPoint(this.getQuantity());
	}
}
