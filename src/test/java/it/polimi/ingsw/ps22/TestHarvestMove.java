package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.HarvestMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * This class checks whether the {@link HarvestMove} class works properly or not
 *
 */

public class TestHarvestMove {
	private HarvestMove harvestMove;
	private Model model;
	private HarvestMove harvestMoveFail;
	private Model modelFail;
	
	/**
	 * This method initializes a {@link HarvestMove} and {@link Model}, to test
	 * success, and two others to test failure
	 * 
	 */
	
	@Before
	public void init() {
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		harvestMove = new HarvestMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		harvestMoveFail = new HarvestMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
	}
	
	/**
	 * this method tests the success applyMove method in {@link HarvestMove}
	 */
	
	@Test
	public void testApplyMove() {
		harvestMove.applyMove(model);
		Family fam = model.getBoard().getHarvestZone().getHarvestSpaces()[0].getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	/**
	 * this method tests the failure applyMove method in {@link HarvestMove}
	 */
	
	@Test
	public void testApplyMoveFail() {
		harvestMoveFail.applyMove(modelFail);
		assert(modelFail.getBoard().getHarvestZone().getHarvestSpaces()[0].getFamilies().size()==0);
	}
}
