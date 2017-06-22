package it.polimi.ingsw.ps22.server.message;

public class CloseGame extends GenericMessage {

	private static final long serialVersionUID = 1L;
	
	public CloseGame(){
		setString("Disconnected");
	}

}
