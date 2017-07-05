package it.polimi.ingsw.ps22.server.resource;

/**
 * Extension of the class {@link Resource}, it implements a
 * representation of the Wood resource.
 */
public class Wood extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new {@link Wood}
	 * @param wood is the quantity to which the resource is set
	 */
	public Wood(int wood){
		super(wood);
	}
	
	/**
	 * @return the name of the resource
	 */
	@Override
	public String getName() {
		return "Wood";
	}
	
	@Override
	public Wood clone() {
		return new Wood(this.getQuantity());
	}
}
