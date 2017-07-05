package it.polimi.ingsw.ps22.server.resource;

import java.io.Serializable;
/**
 * Abstraction of all the class that represent resources, points and bonus in the game.
 */
public abstract class BonusAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	private int quantity;

	/**
	 * Constructor of all the resources, increments and points used in the game
	 * @param quantity is the value to which the resource is set
	 */
	public BonusAbstract(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void addResource(BonusAbstract other) {
		this.quantity = this.quantity + other.getQuantity();
	}

	public void subResource(BonusAbstract other) {
		this.quantity = this.quantity - other.getQuantity();
	}
	
	@Override
	public abstract BonusAbstract clone();
	
}