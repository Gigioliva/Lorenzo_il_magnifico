package it.polimi.ingsw.ps22.effect;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class EndVictoryEffect implements EndEffect {
	private VictoryPoint points;
	
	public EndVictoryEffect(int value){
		points=new VictoryPoint(value);
	}

	@Override
	public void performEffect(Player player, Board board) {
		player.addPoints("VictoryPoint", points);
	}
}
