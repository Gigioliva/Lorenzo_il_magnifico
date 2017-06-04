package it.polimi.ingsw.ps22.server.board;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;

public class TowerSpace extends ActionSpace {
	
	private DevelopmentCard card;
	private int plan;
	
	public TowerSpace(int actionCost, boolean multi, int plan){
		super(actionCost,multi);
		this.plan=plan;
	}
	
	public void addCard(DevelopmentCard card){
		this.card=card;
	}
	
	public DevelopmentCard getCard(){
		return card;
	}
	
	public void removeCard(){
		card=null;
	}
	
	public int getPlan(){
		return plan;
	}
	
	@Override
	public String toString() {
	
		StringBuilder str = new StringBuilder(super.toString());
		
		str.append("flat: " + plan + "\n");
		str.append(card.toString() + "\n");
		
		return str.toString();
	}

}
