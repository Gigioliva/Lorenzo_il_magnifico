package it.polimi.ingsw.ps22.server.resource;

public class ResourceAbstract extends BonusAbstract{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceAbstract(int quantity){
		super(quantity);
		
	}
	
	public String getName() {
		return null;
	}
	
	@Override
	public ResourceAbstract clone() {
		return new ResourceAbstract(this.getQuantity());
	}
	

}
