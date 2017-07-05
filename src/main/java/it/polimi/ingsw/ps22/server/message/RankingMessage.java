package it.polimi.ingsw.ps22.server.message;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.UserData;

public class RankingMessage extends GenericMessage {

	private static final long serialVersionUID = 1L;

	public RankingMessage(HashMap<String, UserData> login) {
		StringBuilder str = new StringBuilder();
		int vict;
		int play;
		DecimalFormat dec = new DecimalFormat("#.##");
		HashMap<String, Float> temp = new HashMap<String, Float>();
		str.append("<html>Top Players:<br/><br/>");
		for (String el : login.keySet()) {
			if (login.get(el).getNumPlayedGame() != 0) {
				play = login.get(el).getNumPlayedGame();
				vict = login.get(el).getNumVictory();
				temp.put(el, ((float) vict / (float) play));
			}
		}
		ArrayList<String> top=(ArrayList<String>)temp.keySet().stream().sorted((a,b)->{
				if(temp.get(b)-temp.get(a)>0){
					return 1;
				}
				if(temp.get(b)-temp.get(a)<0){
					return -1;
				}
				return 0;
			}).collect(Collectors.toList());
		for(String el: top){
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
