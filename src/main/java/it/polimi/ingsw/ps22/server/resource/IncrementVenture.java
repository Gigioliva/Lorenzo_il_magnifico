package it.polimi.ingsw.ps22.server.resource;

public class IncrementVenture extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	public IncrementVenture(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementVenture clone() {
		return new IncrementVenture(this.getQuantity());
	}

}
