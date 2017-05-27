package it.polimi.ingsw.ps22.message;

public class GenericMessage {
	private String ask;

	public GenericMessage(String ask) {
		this.ask=ask;
	}

	public String getString() {
		return ask;
	}

}
