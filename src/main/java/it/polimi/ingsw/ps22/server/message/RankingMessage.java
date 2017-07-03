package it.polimi.ingsw.ps22.server.message;

import java.text.DecimalFormat;
import java.util.HashMap;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.UserData;

public class RankingMessage extends GenericMessage {

	private static final long serialVersionUID = 1L;

	public RankingMessage(HashMap<String, UserData> login) {
		StringBuilder str = new StringBuilder();
		int vict;
		int play;
		DecimalFormat dec = new DecimalFormat("#.##");
		;
		HashMap<String, Float> temp = new HashMap<String, Float>();
		str.append("<html>Top Players:<br/><br/>");
		// Crea l'hashmap con i soli dati necessari
		for (String el : login.keySet()) {
			if (login.get(el).getNumPlayedGame() != 0) {
				play = login.get(el).getNumPlayedGame();
				vict = login.get(el).getNumVictory();
				temp.put(el, ((float) vict / (float) play));
			}
		}

		// Aggiunge alla stringa che sarà inviata nel messaggio
		for (String el : temp.keySet()) {
			str.append("Player: " + el + " Victory ratio: " + dec.format(temp.get(el)));
			str.append("<br/>");
		}
		str.append("</html>");
		setString(str.toString());
	}

	public void accept(VisitorB visitor) {
		visitor.visit(this);
	}
}
