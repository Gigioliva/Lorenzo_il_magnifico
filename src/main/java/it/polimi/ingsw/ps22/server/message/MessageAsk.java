package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.model.Model;

public class MessageAsk extends GenericMessage{
	
	private static final long serialVersionUID = 1L;
	private static int id;
	protected int id_ask;
	private String user;

	public MessageAsk(String user) {
		if (id == 0) {
			id_ask = 1;
		} else {
			id_ask = id + 1;
		}
		id = id_ask;
		this.user=user;
	}
	
	public MessageAsk(String ask, int id){
		setString(ask);
		id_ask=id;
	}
	
	public int getId(){
		return id_ask;
	}
	
	public String getUser(){
		return user;
	}
	
	public void applyDefault(Model model){
		return;
	}

}
