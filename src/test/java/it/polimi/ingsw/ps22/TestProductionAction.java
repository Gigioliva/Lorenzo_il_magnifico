package it.polimi.ingsw.ps22;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.MultiplyEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * 
 * This class checks whether the {@link ProductionAction} class works properly or
 * not
 *
 */

public class TestProductionAction {

	Player player;
	Model model;
	int servants;
	ProductionAction action;
	
	/**
	 * This method initializes a {@link ProductionAction}, {@link Model} and
	 * {@link Player}
	 * 
	 */
	
	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.RED);
		model = new Model();
		servants = 3;
		action = new ProductionAction(3);
		
		CardBuilding card1 = new CardBuilding();
		card1.setActionValue(3);
		MultiplyEffect eff1 = new MultiplyEffect();
		eff1.setMultiplicand(new VictoryPoint(1));
		eff1.setMultiplicandType("VictoryPoint");
		eff1.setMultiplier("Building");
		eff1.setMultiplierQty(1);
		card1.addActionEffect(eff1);
		
		CardBuilding card2 = new CardBuilding();
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
		bonus.put("Production", eff);
		
		player.getPersonalBoard().setPersonalBonus(bonus);
		player.addDevelopmentCard("Building", card1);
		player.addDevelopmentCard("Building", card2);
	}
	
	/**
	 * this method tests the applyAction method in {@link ProductionAction}
	 */
	
	@Test
	public void testApplyAction() {

		action.applyAction(player, servants, model);

		assert(player.getSpecificResource("MilitaryPoint").getQuantity() == 
				player.getPersonalBoard().getPersonalBonus().get("Production").getGain().get("MilitaryPoint").getQuantity() + 1 &&
				player.getSpecificResource("Servant").getQuantity() == 3 && 
				player.getSpecificResource("VictoryPoint").getQuantity() == 4 &&
				player.getSpecificResource("Coin").getQuantity() == 1);
	}
	

}
