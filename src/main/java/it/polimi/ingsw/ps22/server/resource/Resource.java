package it.polimi.ingsw.ps22.server.resource;

public class Resource extends ResourceAbstract {
	
	private static final long serialVersionUID = 1L;
	
	public Resource(int resources){
		super(resources);
	}
	
	@Override
	public Resource clone() {
		return new Resource(this.getQuantity());
	}
	
}
