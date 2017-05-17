package board;
import card.DevelopmentCard;

public class TowerSpace extends ActionSpace {
	
	private DevelopmentCard card;
	
	public TowerSpace(int actionCost, boolean multi){
		super(actionCost,multi);
	}
	
	public void addCard(DevelopmentCard card){
		this.card=card;
	}
	
	public DevelopmentCard getCard(){
		DevelopmentCard temp=this.card;
		this.card=null;
		return temp;
	}

}
