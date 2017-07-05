package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only on Territory Tower. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementTerritory extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus useful to take {@link CardTerritory} from {@link TowerTerritory}
	 * @param quantity is the value of the increment
	 */
	public IncrementTerritory(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementTerritory clone() {
		return new IncrementTerritory(this.getQuantity());
	}

}
