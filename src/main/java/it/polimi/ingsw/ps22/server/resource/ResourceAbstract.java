package it.polimi.ingsw.ps22.server.resource;

/**
 * Abstraction of all the class that represent resources and point in the game.
 * This class extends the class {@link BonusAbstract}
 */
public class ResourceAbstract extends BonusAbstract {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param quantity the value send to the superclass constructor to create the object
	 */
	public ResourceAbstract(int quantity) {
		super(quantity);

	}
	
	/**
	 * @return the name of the resource
	 */
	public String getName() {
		return null;
	}

	@Override
	public ResourceAbstract clone() {
		return new ResourceAbstract(this.getQuantity());
	}

}
