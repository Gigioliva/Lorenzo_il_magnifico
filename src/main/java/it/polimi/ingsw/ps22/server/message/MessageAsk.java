package it.polimi.ingsw.ps22.server.message;

public class MessageAsk extends GenericMessage{
	
	private static final long serialVersionUID = 1L;
	private static int id;
	protected int id_ask;

	public MessageAsk() {
		if (id == 0) {
			id_ask = 1;
		} else {
			id_ask = id + 1;
		}
		id = id_ask;
	}
	
	public MessageAsk(String ask, int id){
		setString(ask);
		id_ask=id;
	}
	
	public int getId(){
		return id_ask;
	}

}
