package it.polimi.ingsw.ps22.server.resource;

public class FaithPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
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
