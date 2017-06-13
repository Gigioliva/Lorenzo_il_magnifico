package it.polimi.ingsw.ps22.server.resource;

public class MilitaryPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	
	public MilitaryPoint(int militarypoint) {
		super(militarypoint);
	}
	
	public String getName() {
		return "MilitaryPoint";
	}
	
	@Override
	public MilitaryPoint clone() {
		return new MilitaryPoint(this.getQuantity());
	}
}
