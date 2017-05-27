package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.model.Model;

public class MessageAsk {
	private static int id;
	protected int id_ask;
	private String ask;

	protected static Model model;

	public MessageAsk() {
		if (id == 0) {
			id_ask = 1;
		} else {
			id_ask = id + 1;
		}
		id = id_ask;
	}

	public String getString() {
		return ask;
	}

	public void setString(String str) {
		this.ask = str;
	}

	public static void setModel(Model mod) {
		model = mod;
	}

}
