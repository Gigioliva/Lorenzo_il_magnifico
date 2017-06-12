package it.polimi.ingsw.ps22.server.move;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;

public class LeaderDiscarding extends LeaderMove {

	private static final long serialVersionUID = 1L;

	public LeaderDiscarding(String username, String nameCard) {
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
			temp.remove(leader);
			GainResource gain=new GainResource();
			gain.addGain("CouncilPrivilege", new CouncilPrivilege(1));
			gain.performEffect(player);
			return;
		}
		ErrorMove error = new ErrorMove();
		model.notifyMessage(error);
		model.notifyModel();
	}

}
