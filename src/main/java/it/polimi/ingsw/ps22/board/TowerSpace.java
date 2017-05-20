package it.polimi.ingsw.ps22.board;
import it.polimi.ingsw.ps22.card.DevelopmentCard;

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
		DevelopmentCard temp=this.card;
		this.card=null;
		return temp;
	}
	
	public int getPlan(){
		return plan;
	}

}
