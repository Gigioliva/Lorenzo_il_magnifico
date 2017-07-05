package it.polimi.ingsw.ps22.server.resource;

/**
 * Abstraction of all the class that represent points in the game (Faith, Military, Victory)
 * This class extends the class {@link ResourceAbstract}
 */
public class Point extends ResourceAbstract {

	private static final long serialVersionUID = 1L;

	/**
	 * @param points the value send to the superclass constructor to create the object
	 */
	public Point(int points) {
		super(points);
	}

	@Override
	public Point clone() {
		return new Point(this.getQuantity());
	}

}
