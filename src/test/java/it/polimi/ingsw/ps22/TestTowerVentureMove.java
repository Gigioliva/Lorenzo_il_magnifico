package it.polimi.ingsw.ps22;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.TowerVentureMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestTowerVentureMove {

	 
	private TowerVentureMove ventureMove;
	private Model model;
	private TowerVentureMove ventureMoveFail;
	private Model modelFail;
	
	@Before
	public void init() {
		CardVenture card = new CardVenture();
		card.setName("Card1");
		card.setEra(1);
		HashMap<String, ResourceAbstract> cost = new HashMap<String, ResourceAbstract>();
		HashMap<String, ResourceAbstract> requisite = new HashMap<String, ResourceAbstract>();
		cost.put("Coin", new Coin(2));
		cost.put("Stone", new Stone(2));
		cost.put("Wood", new Wood(2));
		card.addRequisiteCost(cost, requisite);
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(4));
		model.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
		model.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		model.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
		model.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
		model.getBoard().getTower("Venture").getTowerSpaces()[0].addCard(card);
		ventureMove = new TowerVentureMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(1));
		modelFail.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
		modelFail.getBoard().getTower("Venture").getTowerSpaces()[0].addCard(card);
		ventureMoveFail = new TowerVentureMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
	}
	
	@Test
	public void testApplyMove() {
		ventureMove.applyMove(model);
		Family fam = model.getBoard().getTower("Venture").getTowerSpaces()[0].getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	@Test
	public void testApplyMoveFail() {
		ventureMoveFail.applyMove(modelFail);
		assert(modelFail.getBoard().getTower("Venture").getTowerSpaces()[0].getFamilies().size()==0);
	}


}
