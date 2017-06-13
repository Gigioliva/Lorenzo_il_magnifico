package it.polimi.ingsw.ps22.server.resource;

import java.io.Serializable;

public abstract class BonusAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	private int quantity;

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
	
	public abstract BonusAbstract clone();
}