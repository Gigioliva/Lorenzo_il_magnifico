package it.polimi.ingsw.ps22.server.resource;

public class Wood extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	public Wood(int wood){
		super(wood);
	}
	
	public String getName() {
		return "Wood";
	}
	
	@Override
	public Wood clone() {
		return new Wood(this.getQuantity());
	}
}
