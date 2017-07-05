package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only on Building Tower. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementBuilding extends BonusAbstract {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus useful to take {@link CardBuilding} from {@link TowerBuilding}
	 * @param quantity is the value of the increment
	 */
	public IncrementBuilding(int quantity) {
		super(quantity);
	}

	@Override
	public IncrementBuilding clone() {
		return new IncrementBuilding(this.getQuantity());
	}

}
