package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.LeaderPlaying;

public class TestLeaderPlaying {

	private LeaderPlaying leaderPlay;
	private Model model;
	
	@Before
	public void init() {
		CardLeader cardLeader1 = new CardLeader("Pico della Mirandola");
		CardLeader cardLeader2 = new CardLeader("Giovanni dalle Bande Nere");
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addLeader(cardLeader1);
		cardLeader1.setCopy(false);
		model.getPlayers().get("Marco").addLeader(cardLeader2);
		cardLeader1.setCopy(true);
		leaderPlay = new LeaderPlaying(
				model.getPlayers().get("Marco").getUsername(),
				"Giovanni dalle Bande Nere");	
	}
	
	@Test
	public void testApplyMove() {
		leaderPlay.applyMove(model);
		assert(true); // IMPLEMENTARE
	}

}
