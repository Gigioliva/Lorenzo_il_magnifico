package it.polimi.ingsw.ps22.server.move;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * Move that permit to Play a {@link CardLeader}
 */
public class LeaderPlaying extends LeaderMove {

	private static final long serialVersionUID = 1L;

	/**
	 * @param username
	 *            is the username of the player which is creating the move
	 * @param nameCard
	 *            is the name of the {@link CardLeader} the player want to play
	 */
	public LeaderPlaying(String username, String nameCard) {
		super(username, nameCard);
	}

	/**
	 * Apply the move which call this method to the model, if is possible
	 * 
	 * @param model
	 *            is the model to which apply the move
	 */
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
					model.notifyModel();
					return;
				} else {
					ArrayList<CardLeader> leaderPlay = getLeaderPlay(model);
					if (!leaderPlay.isEmpty()) {
						AskCopyLeader mex = new AskCopyLeader(leaderPlay, leader, player);
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
