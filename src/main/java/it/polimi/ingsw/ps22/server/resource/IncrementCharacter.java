package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only on Character Tower. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementCharacter extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus useful to take {@link CardCharacter} from {@link TowerCharacter}
	 * @param quantity is the value of the increment
	 */
	public IncrementCharacter(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementCharacter clone() {
		return new IncrementCharacter(this.getQuantity());
	}

}
