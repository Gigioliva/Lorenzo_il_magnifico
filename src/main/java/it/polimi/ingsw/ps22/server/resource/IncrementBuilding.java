package it.polimi.ingsw.ps22.server.resource;

public class IncrementBuilding extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	public IncrementBuilding(int quantity) {
		super(quantity);
	}

	@Override
	public IncrementBuilding clone() {
		return new IncrementBuilding(this.getQuantity());
	}

}
