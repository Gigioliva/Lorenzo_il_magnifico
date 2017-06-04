package it.polimi.ingsw.ps22.server.move;

public class LeaderMove extends Move {
	
	private static final long serialVersionUID = 1L;
	protected String nameCard;
	
	public LeaderMove(String username,String nameCard){
		super(username);
		this.nameCard=nameCard;
	}

}
