package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only {@link ProductionZone}. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementProduction extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus useful to make a {@link ProductionAction} in {@link ProductionZone}
	 * @param quantity is the value of the increment
	 */
	public IncrementProduction(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementProduction clone() {
		return new IncrementProduction(this.getQuantity());
	}

}
