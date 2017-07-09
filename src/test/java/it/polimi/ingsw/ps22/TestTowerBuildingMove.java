package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.TowerBuildingMove;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

/**
 * 
 * This class checks whether the {@link TowerBuildingMove} class works properly or not
 *
 */

public class TestTowerBuildingMove {
	 
		private TowerBuildingMove buildingMove;
		private Model model;
		private TowerBuildingMove buildingMoveFail;
		private Model modelFail;
		
		/**
		 * This method initializes a {@link TowerBuildingMove} and {@link Model}, to test
		 * success, and two others to test failure
		 * 
		 */
		
		@Before
		public void init() {
			CardBuilding card = new CardBuilding();
			card.setName("Card1");
			card.setEra(1);
			card.addCost("Coin", new Coin(2));
			card.addCost("Stone", new Stone(2));
			card.addCost("Wood", new Wood(2));
			model = new Model();
			model.addPlayers("Marco");
			model.startGame();
			model.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(4));
			model.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
			model.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
			model.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
			model.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
			model.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
			model.getBoard().getTower("Building").getTowerSpaces()[0].addCard(card);
			buildingMove = new TowerBuildingMove(model.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,1);
			modelFail = new Model();
			modelFail.addPlayers("Marco");
			modelFail.startGame();
			modelFail.getPlayers().get("Marco").addSpecificResource("Coin", new Coin(1));
			modelFail.getPlayers().get("Marco").addSpecificResource("Stone", new Stone(4));
			modelFail.getPlayers().get("Marco").addSpecificResource("Wood", new Wood(4));
			modelFail.getPlayers().get("Marco").addSpecificResource("Servant", new Servant(4));
			modelFail.getPlayers().get("Marco").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
			modelFail.getPlayers().get("Marco").addSpecificResource("FaithPoint", new FaithPoint(4));
			modelFail.getBoard().getTower("Building").getTowerSpaces()[0].addCard(card);
			buildingMoveFail = new TowerBuildingMove(modelFail.getPlayers().get("Marco").getUsername(),Color.NEUTRAL,1,0);
		}
		
		/**
		 * this method tests the success applyMove method in {@link TowerBuildingMove}
		 */
		
		@Test
		public void testApplyMove() {
			buildingMove.applyMove(model);
			Family fam = model.getBoard().getTower("Building").getTowerSpaces()[0].getFamilies().get(0);
			assert(fam.getColor()==Color.NEUTRAL && fam.getPlayer().getUsername().equals("Marco"));
		}
		
		/**
		 * this method tests the failure applyMove method in {@link TowerBuildingMove}
		 */
		
		@Test
		public void testApplyMoveFail() {
			buildingMoveFail.applyMove(modelFail);
			assert(modelFail.getBoard().getTower("Building").getTowerSpaces()[0].getFamilies().size()==0);
		}



}
