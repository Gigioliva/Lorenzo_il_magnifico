package it.polimi.ingsw.ps22.resource;

public abstract class BonusAbstract {
private int quantity;
	
	public BonusAbstract(int quantity){
		this.quantity=quantity;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public void addResource(BonusAbstract other){
		this.quantity=this.quantity+other.getQuantity();
	}
	
	public void subResource(BonusAbstract other){
		this.quantity=this.quantity-other.getQuantity();
	}
}