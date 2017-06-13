package it.polimi.ingsw.ps22.server.resource;

public class Coin extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	public Coin(int coin){
		super(coin);
	}
	
	public String getName() {
		return "Coin";
	}
	
	@Override
	public Coin clone() {
		return new Coin(this.getQuantity());
	}
	
}
