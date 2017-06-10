package it.polimi.ingsw.ps22.server.move;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
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
				if (leader.getCopy() == false) {
					leader.playLeader(player);
					player.setFamily(model.getBoard().getDice());
					return;
				} else {
					ArrayList<CardLeader> leaderPlay=getLeaderPlay(model);
					if(!leaderPlay.isEmpty()){
						AskCopyLeader mex=new AskCopyLeader(leaderPlay, leader, player);
						mex.applyAsk();
						leader.setCopy(false);
						return;
					}
				}
			}
		} else {
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
		}
	}

	private ArrayList<CardLeader> getLeaderPlay(Model model) {
		ArrayList<CardLeader> temp = new ArrayList<CardLeader>();
		ArrayList<Player> players = new ArrayList<Player>(model.getPlayers().values());
		for (Player el : players) {
			ArrayList<CardLeader> cards = el.getLeaders();
			for (CardLeader card : cards) {
				if (card.isPlay()) {
					temp.add(card);
				}
			}
		}
		return temp;
	}

}
