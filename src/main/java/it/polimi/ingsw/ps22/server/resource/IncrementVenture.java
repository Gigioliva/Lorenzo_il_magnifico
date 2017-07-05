package it.polimi.ingsw.ps22.server.resource;
/**
 * Class which represent the increment of the dice of the player which have this
 * bonus only on Venture Tower. This class extends the class
 * {@link BonusAbstract}
 */
public class IncrementVenture extends BonusAbstract {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a bonus useful to take {@link CardVenture} from {@link TowerVenture}
	 * @param quantity is the value of the increment
	 */
	public IncrementVenture(int quantity) {
		super(quantity);
	}
	
	@Override
	public IncrementVenture clone() {
		return new IncrementVenture(this.getQuantity());
	}

}
