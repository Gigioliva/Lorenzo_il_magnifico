package it.polimi.ingsw.ps22.server.message;

import java.util.HashMap;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.UserData;

public class RankingMessage extends GenericMessage {

	private static final long serialVersionUID = 1L;

	public RankingMessage(HashMap<String, UserData> login) {
		StringBuilder str = new StringBuilder();
		for (String el : login.keySet()) {
			str.append("Player: " + el + " Giocate: " + login.get(el).getNumPlayedGame() + " Vinte: "
					+ login.get(el).getNumVictory());
			str.append("\n");
		}
		setString(str.toString());
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
}
