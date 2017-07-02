package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.CouncilMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class TestCouncilMove {
	
	private CouncilMove councilMove;
	private Model model;
	private CouncilMove councilMove2;
	private Model model2;
	
	@Before
	public void init() {
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		councilMove = new CouncilMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1);
		model2 = new Model();
		model2.addPlayers("Marco");
		model2.startGame();
		model2.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(2));
		councilMove2 = new CouncilMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,0);
	}
	
	@Test
	public void testApplyMove() {
		councilMove.applyMove(model);
		Family fam = model.getBoard().getCouncilPalace().getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	@Test
	public void testApplyMoveFail() {
		councilMove2.applyMove(model2);
		assert(model2.getBoard().getCouncilPalace().getFamilies().size()==0);
	}
	
}
