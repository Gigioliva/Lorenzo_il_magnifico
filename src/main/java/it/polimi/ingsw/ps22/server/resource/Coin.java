package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Resource}, it implements a
 * representation of the Coin resource.
 */
public class Coin extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link Coin}
	 * @param coin is the quantity to which the resource is set
	 */
	public Coin(int coin){
		super(coin);
	}
	
	/**
	 * @return the name of the resource
	 */
	@Override
	public String getName() {
		return "Coin";
	}
	
	@Override
	public Coin clone() {
		return new Coin(this.getQuantity());
	}
	
}
