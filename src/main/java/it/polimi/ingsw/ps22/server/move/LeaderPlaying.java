package it.polimi.ingsw.ps22.server.move;

import java.util.ArrayList;
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
			if (leader.takeCardControl(player)) {
				if (leader.getCopy() == false) {
					leader.playLeader(player, model);
					player.setFamily(model.getBoard().getDice());
					return;
				} else {
					ArrayList<CardLeader> leaderPlay=getLeaderPlay(model);
					if(!leaderPlay.isEmpty()){
						AskCopyLeader mex=new AskCopyLeader(leaderPlay, leader, player);
						model.notifyAsk(mex);
						leader.setCopy(false);
						return;
					}
				}
			}
		}
		ErrorMove error = new ErrorMove();
		model.notifyMessage(error);
		model.notifyModel();
	}

	private ArrayList<CardLeader> getLeaderPlay(Model model) {		
		//ritorna tutte le carte in gioco di tutti i giocatori
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
