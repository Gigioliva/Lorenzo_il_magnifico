package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.CouncilMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * This class checks whether the {@link CouncilMove} class works properly or not
 *
 */

public class TestCouncilMove {

	private CouncilMove councilMove;
	private Model model;
	private CouncilMove councilMoveFail;
	private Model modelFail;

	/**
	 * This method initializes a {@link CouncilMove} and {@link Model}, to test
	 * success, and two others to test failure
	 * 
	 */

	@Before
	public void init() {
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		councilMove = new CouncilMove(model.getPlayers().get("Marco").getUsername(), Color.NEUTRAL, 1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(2));
		councilMoveFail = new CouncilMove(modelFail.getPlayers().get("Marco").getUsername(), Color.NEUTRAL, 0);
	}

	/**
	 * this method tests the success applyMove method in {@link CouncilMove}
	 */
	
	@Test
	public void testApplyMove() {
		councilMove.applyMove(model);
		Family fam = model.getBoard().getCouncilPalace().getFamilies().get(0);
		assert (fam.getColor() == Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}

	/**
	 * this method tests the failure applyMove method in {@link CouncilMove}
	 */
	
	@Test
	public void testApplyMoveFail() {
		councilMoveFail.applyMove(modelFail);
		assert (modelFail.getBoard().getCouncilPalace().getFamilies().size() == 0);
	}

}
