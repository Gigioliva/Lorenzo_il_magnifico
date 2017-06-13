package it.polimi.ingsw.ps22.server.resource;

public class IncrementTerritory extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	public IncrementTerritory(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementTerritory clone() {
		return new IncrementTerritory(this.getQuantity());
	}

}
