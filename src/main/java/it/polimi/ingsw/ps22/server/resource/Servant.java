package it.polimi.ingsw.ps22.server.resource;

public class Servant extends Resource {
	
	private static final long serialVersionUID = 1L;
	
	public Servant (int servant){
		super(servant);
	}
	
	public String getName() {
		return "Servant";
	}
	
}
