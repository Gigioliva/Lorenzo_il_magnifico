package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.effect.ReduceCostEffect;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;

public class TestReduceCostEffect {
	
	Player player;
	ReduceCostEffect eff;
	
	@Before
	public void init(){
		player = new Player("Gigi", ColorPlayer.BLUE);
		eff = new ReduceCostEffect("Building");
		eff.addBonus("Coin", new Coin(3));
		eff.addBonus("Stone", new Stone(2));
	}

	@Test
	public void testPerformEffect() {
		eff.performEffect(player, null);
		assert(player.getBonusAcc().getSaleBuilding().get("Coin").getQuantity() == 3);
		assert(player.getBonusAcc().getSaleBuilding().get("Stone").getQuantity() == 2);
	}

}
