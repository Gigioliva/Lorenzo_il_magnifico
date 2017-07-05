package it.polimi.ingsw.ps22.server.resource;

/**
 * Class which represent the increment of the dice of the player that have this bonus.
 *  This class extends the class {@link BonusAbstract}
 */
public class IncrementDice extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create a bonus which increment value of the {@link Dice}
	 * @param quantity is the value of the increment
	 */
	public IncrementDice(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementDice clone() {
		return new IncrementDice(this.getQuantity());
	}

}
