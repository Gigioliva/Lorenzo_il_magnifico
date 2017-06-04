package it.polimi.ingsw.ps22.server.move;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class LeaderPlaying extends LeaderMove {

	private static final long serialVersionUID = 1L;

	public LeaderPlaying(String username, String nameCard) {
		super(username, nameCard);
	}

	@Override
	public void applyMove(Model model) {
		Player player = model.getPlayers().get(username);
		ArrayList<CardLeader> temp = player.getLeaders();
		CardLeader leader = null;
		for (CardLeader el : temp) {
			if (el.getName().equalsIgnoreCase(nameCard)) {
				leader = el;
			}
		}
		if (leader != null) {
			boolean playable = false;
			ArrayList<HashMap<String, Integer>> requisite = leader.getRequisite();
			for (HashMap<String, Integer> el : requisite) {
				playable = true;
				for (String type : el.keySet()) {
					if (el.get(type) > player.getGenericValue(type)) {
						playable = false;
					}
				}
				if (playable == true) {
					break;
				}
			}
			if (playable == true) {
				leader.playLeader(player);
				return;
			}
		} else {
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
		}
	}

}
