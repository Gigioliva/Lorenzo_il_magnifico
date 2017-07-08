package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.ProductionMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * This class test the {@link ProductionMove} class
 *
 */
public class TestProductionMove {

	private ProductionMove productionMove;
	private Model model;
	private ProductionMove productionMoveFail;
	private Model modelFail;
	
	/**
	 * this method initialize the {@link Model}, the {@link ProductionMove}
	 * 
	 */
	@Before
	public void init() {
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		productionMove = new ProductionMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		productionMoveFail = new ProductionMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
	}
	
	/**
	 * this method tests the applyMove method in {@link ProductionMove}
	 */
	@Test
	public void testApplyMove() {
		productionMove.applyMove(model);
		Family fam = model.getBoard().getProdZone().getProdSpaces()[0].getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	/**
	 * this method checks that applyMove correctly fails
	 */
	@Test
	public void testApplyMoveFail() {
		productionMoveFail.applyMove(modelFail);
		assert(modelFail.getBoard().getProdZone().getProdSpaces()[0].getFamilies().size()==0);
	}

}
