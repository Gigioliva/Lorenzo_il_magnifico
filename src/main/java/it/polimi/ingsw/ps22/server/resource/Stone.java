package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Resource}, it implements a
 * representation of the Stone resource.
 */
public class Stone extends Resource {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new {@link Stone}
	 * @param stone is the quantity to which the resource is set
	 */
	public Stone(int stone) {
		super(stone);
	}
	
	/**
	 * @return the name of the resource
	 */
	@Override
	public String getName() {
		return "Stone";
	}

	@Override
	public Stone clone() {
		return new Stone(this.getQuantity());
	}
}
