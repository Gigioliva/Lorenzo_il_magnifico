package it.polimi.ingsw.ps22.effect;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class ExchangeResource implements ActionEffect{
	private HashMap<String,ResourceAbstract> exchange;
	
	public ExchangeResource(){
		exchange=new HashMap<String, ResourceAbstract>();
	}
	
	public void addExchange(String type, ResourceAbstract resource){
		exchange.put(type, resource);
	}
	
	@Override
	public void performEffect(Player player, Board board) { //Controlla che il giocatore abbia suff risorse per lo scambio
		

	}
	

}
