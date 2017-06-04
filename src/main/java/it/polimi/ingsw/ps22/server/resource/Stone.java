package it.polimi.ingsw.ps22.server.resource;

public class Stone extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	public Stone(int stone){
		super(stone);
	}

	public String getName() {
		return "Stone";
	}
}
