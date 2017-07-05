package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Point}, it implements a
 * representation of the Faith Point.
 */
public class FaithPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link FaithPoint}
	 * @param faithpoint is the quantity to which the point is set
	 */
	public FaithPoint(int faithpoint) {
		super(faithpoint);
	}
	
	@Override
	public String getName() {
		return "FaithPoint";
	}
	
	@Override
	public FaithPoint clone() {
		return new FaithPoint(this.getQuantity());
	}
	
}
