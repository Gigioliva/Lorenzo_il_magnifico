package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.model.Model;

public class MessageAsk extends GenericMessage{
	
	private static final long serialVersionUID = 1L;
	private transient static int id;
	protected int id_ask;
	protected transient static Model model;

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

	public static void setModel(Model mod) {
		model = mod;
	}
	
	public void applyAsk(){
		model.notifyAsk(this);
	}
	
	public int getId(){
		return id_ask;
	}

}
