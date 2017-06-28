package it.polimi.ingsw.ps22;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.action.HarvestAction;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class TestHarvestAction {

	Player player;
	Model model;
	int servants;
	HarvestAction action;
	
	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.RED);
		model = new Model();
		servants = 3;
		action = new HarvestAction(3);
		
		CardTerritory card1 = new CardTerritory();
		card1.setActionValue(3);
		GainResource eff1 = new GainResource();
		eff1.addGain("Servant", new Servant(2));
		eff1.addGain("Coin", new Coin(1));
		card1.addActionEffect(eff1);
		
		CardTerritory card2 = new CardTerritory();
		card2.setActionValue(3);
		GainResource eff2 = new GainResource();
		eff2.addGain("Servant", new Servant(2));
		eff2.addGain("Coin", new Coin(1));
		card2.addActionEffect(eff2);
		GainResource eff3 = new GainResource();
		eff3.addGain("VictoryPoint", new VictoryPoint(2));
		eff3.addGain("MilitaryPoint", new MilitaryPoint(1));
		card2.addActionEffect(eff3);
		
		HashMap<String, GainResource> bonus = new HashMap<>();
		GainResource eff = new GainResource();
		eff.addGain("Servant", new Servant(1));
		eff.addGain("MilitaryPoint", new MilitaryPoint(1));
		bonus.put("Harvest", eff);
		
		player.getPersonalBoard().setPersonalBonus(bonus);
		player.addDevelopmentCard("Territory", card1);
		player.addDevelopmentCard("Territory", card2);
	}
	
	@Test
	public void testApplyAction() {

		action.applyAction(player, servants, model);
		
		assert(player.getSpecificResource("MilitaryPoint").getQuantity() == 
				player.getPersonalBoard().getPersonalBonus().get("Harvest").getGain().get("MilitaryPoint").getQuantity() + 1 &&
				player.getSpecificResource("Servant").getQuantity() == 5 && 
				player.getSpecificResource("VictoryPoint").getQuantity() == 2 &&
				player.getSpecificResource("Coin").getQuantity() == 2);
	}
	
}
