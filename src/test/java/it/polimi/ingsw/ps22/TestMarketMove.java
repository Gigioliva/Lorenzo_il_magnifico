package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.MarketMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * This class checks whether the {@link MarketMove} class works properly or not
 *
 */

public class TestMarketMove {

	private MarketMove marketMove;
	private Model model;
	private MarketMove marketMoveFail;
	private Model modelFail;
	
	/**
	 * This method initializes a {@link MarketMove} and {@link Model}, to test
	 * success, and two others to test failure
	 * 
	 */
	
	@Before
	public void init() {
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		marketMove = new MarketMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		marketMoveFail = new MarketMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
	}
	
	/**
	 * this method tests the success applyMove method in {@link MarketMove}
	 */
	
	@Test
	public void testApplyMove() {
		marketMove.applyMove(model);
		Family fam = model.getBoard().getMarket().getMarketSpaces()[0].getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	/**
	 * this method tests the failure applyMove method in {@link MarketMove}
	 */
	
	@Test
	public void testApplyMoveFail() {
		marketMoveFail.applyMove(modelFail);
		assert(modelFail.getBoard().getMarket().getMarketSpaces()[0].getFamilies().size()==0);
	}
}
