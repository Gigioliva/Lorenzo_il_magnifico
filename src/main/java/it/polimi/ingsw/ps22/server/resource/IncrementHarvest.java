package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only in {@link HarvestZone}. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementHarvest extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus useful to make a {@link HarvestAction} in {@link HarvestZone}
	 * @param quantity is the value of the increment
	 */
	public IncrementHarvest(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementHarvest clone() {
		return new IncrementHarvest(this.getQuantity());
	}

}
