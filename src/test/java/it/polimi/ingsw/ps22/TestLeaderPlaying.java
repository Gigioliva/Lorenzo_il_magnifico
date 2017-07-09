package it.polimi.ingsw.ps22;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.CouncilMove;
import it.polimi.ingsw.ps22.server.move.LeaderPlaying;
import it.polimi.ingsw.ps22.server.resource.Coin;

/**
 * 
 * This class checks whether the {@link LeaderPlaying} class works properly or not
 *
 */

public class TestLeaderPlaying {

	private LeaderPlaying leaderPlay;
	private Model model;
	
	/**
	 * This method initializes a {@link LeaderPlaying} and {@link Model}
	 * 
	 */
	
	@Before
	public void init() {
		CardLeader cardLeader1 = new CardLeader("Pico della Mirandola");
		HashMap<String,Integer> req=new HashMap<>();
		req.put("Wood", 1);
		cardLeader1.addRequisite(req);
		cardLeader1.setCopy(false);
		GainResource eff=new GainResource();
		eff.addGain("Coin", new Coin(3));
		cardLeader1.addImmediateEffect(eff);
		model = new Model();
		model.addPlayers("Marco");
		model.getPlayers().get("Marco").addLeader(cardLeader1);
		leaderPlay = new LeaderPlaying(
				model.getPlayers().get("Marco").getUsername(),
				"Pico della Mirandola");	
	}
	
	/**
	 * this method tests the success applyMove method in {@link CouncilMove}
	 */
	
	@Test
	public void testApplyMove() {
		leaderPlay.applyMove(model);
		assert(model.getPlayers().get("Marco").getSpecificResource("Coin").getQuantity()==3);
	}
	
	/**
	 * this method tests the failure applyMove method in {@link CouncilMove}
	 */
	
	@Test
	public void testApplyMoveFail(){
		LeaderPlaying leaderPlayFail = new LeaderPlaying(
				model.getPlayers().get("Marco").getUsername(),
				"Nome a caso");
		leaderPlayFail.applyMove(model);
		assert(model.getPlayers().get("Marco").getSpecificResource("Coin").getQuantity()==0);
	}

}
