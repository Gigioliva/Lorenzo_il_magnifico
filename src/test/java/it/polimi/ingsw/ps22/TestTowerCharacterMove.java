package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.TowerCharacterMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestTowerCharacterMove { 
	private TowerCharacterMove characterMove;
	private Model model;
	private TowerCharacterMove characterMoveFail;
	private Model modelFail;
	
	@Before
	public void init() {
		CardCharacter card = new CardCharacter();
		card.setName("Card1");
		card.setEra(1);
		card.addCost(new Coin(2));
		model = new Model();
		model.addPlayers("Marco");
		model.startGame();
		model.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(4));
		model.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
		model.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
		model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		model.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
		model.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
		model.getBoard().getTower("Character").getTowerSpaces()[0].addCard(card);
		characterMove = new TowerCharacterMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
		modelFail = new Model();
		modelFail.addPlayers("Marco");
		modelFail.startGame();
		modelFail.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(1));
		modelFail.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
		modelFail.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
		modelFail.getBoard().getTower("Character").getTowerSpaces()[0].addCard(card);
		characterMoveFail = new TowerCharacterMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
	}
	
	@Test
	public void testApplyMove() {
		characterMove.applyMove(model);
		Family fam = model.getBoard().getTower("Character").getTowerSpaces()[0].getFamilies().get(0);
		assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
	}
	
	@Test
	public void testApplyMoveFail() {
		characterMoveFail.applyMove(modelFail);
		assert(modelFail.getBoard().getTower("Character").getTowerSpaces()[0].getFamilies().size()==0);
	}


}
