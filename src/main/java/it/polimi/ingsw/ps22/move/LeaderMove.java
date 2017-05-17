package it.polimi.ingsw.ps22.move;

public class LeaderMove extends Move {
	private String nameCard;
	
	public LeaderMove(String username,String nameCard){
		super(username);
		this.nameCard=nameCard;
	}

}
