package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class EndVictoryEffect implements EndEffect {
	
	private static final long serialVersionUID = 1L;
	private VictoryPoint points;
	
	public EndVictoryEffect(int value){
		points=new VictoryPoint(value);
	}
	
	@Override
	public EndVictoryEffect clone(){
		EndVictoryEffect temp=new EndVictoryEffect(this.points.getQuantity());
		return temp;
	}

	@Override
	public void performEffect(Player player) {
		player.addPoints("VictoryPoint", points);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("End effect: \n");
		if(points.getQuantity() > 0)
			str.append("+ " + points.getQuantity() + " victory points" + "\n");
		else
			str.append("- " + points.getQuantity() + " victory points" + "\n");
		return str.toString();
	}
}
