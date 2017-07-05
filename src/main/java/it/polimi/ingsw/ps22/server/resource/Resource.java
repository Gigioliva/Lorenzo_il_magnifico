package it.polimi.ingsw.ps22.server.resource;

/**
 * Abstraction of all the class that represent resources in the game (Coin,
 * Stone, Wood, Servant) This class extends the class {@link ResourceAbstract}
 */
public class Resource extends ResourceAbstract {

	private static final long serialVersionUID = 1L;

	/**
	 * @param resources the value send to the superclass constructor to create the object
	 */
	public Resource(int resources) {
		super(resources);
	}

	@Override
	public Resource clone() {
		return new Resource(this.getQuantity());
	}

}
