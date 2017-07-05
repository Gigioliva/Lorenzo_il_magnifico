package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Resource}, it implements a
 * representation of the Servant resource.
 */
public class Servant extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link Servant}
	 * @param servant is the quantity to which the resource is set
	 */
	public Servant (int servant){
		super(servant);
	}
	
	/**
	 * @return the name of the resource
	 */
	@Override
	public String getName() {
		return "Servant";
	}
	
	@Override
	public Servant clone() {
		return new Servant(this.getQuantity());
	}
	
}
