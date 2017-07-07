package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.LeaderDiscarding;

public class TestLeaderDiscarding {

	private LeaderDiscarding leaderDisc;
	private Model model;
	private LeaderDiscarding leaderDiscFail;
	private Model modelFail;
	
	@Before
	public void init() {
		CardLeader cardLeader1 = new CardLeader("Giovanni dalle Bande Nere");
		CardLeader cardLeader2 = new CardLeader("Pico della Mirandola");
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addLeader(cardLeader1);
		model.getPlayers().get("Marco").addLeader(cardLeader2);
		leaderDisc = new LeaderDiscarding(model.getPlayers().get("Marco").getUsername(),"Giovanni dalle Bande Nere");
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addLeader(cardLeader2);
		leaderDiscFail = new LeaderDiscarding(modelFail.getPlayers().get("Marco").getUsername(),"Giovanni dalle Bande Nere");
	}
	
	@Test
	public void testApplyMove() {
		assert(model.getPlayers().get("Marco").getLeaders().size()==2);
		leaderDisc.applyMove(model);
		assert(model.getPlayers().get("Marco").getLeaders().size()==1);
	}
	
	@Test
	public void testApplyMoveFail() {
		assert(modelFail.getPlayers().get("Marco").getLeaders().size()==1);
		leaderDiscFail.applyMove(modelFail);
		assert(modelFail.getPlayers().get("Marco").getLeaders().size()==1);
		
	}

}
