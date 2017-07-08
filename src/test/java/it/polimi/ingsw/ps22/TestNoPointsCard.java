package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.EndVictoryEffect;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * This class checks whether the {@link NoPointsCard} class 
 * works properly or not
 *
 */
public class TestNoPointsCard {
	
	Player player;
	NoPointsCard eff;

	/**
	 * This method initializes the variables needed to check the class.
	 */
	@Before
	public void init(){
		player = new Player("Lore", ColorPlayer.BLUE);
		
		for(int i = 0; i< 3; i++){
			CardVenture card = new CardVenture();
			card.addEndEffect(new EndVictoryEffect(4));
			card.loadEndEffects(player);
			player.addDevelopmentCard("Venture", card);
		}
		
		EndEffect effe = new EndVictoryEffect(3);
		
		player.getEndEffects().add(effe);
	
		
		eff = new NoPointsCard("Venture");
		eff.performEffect(player, null);
	}
	
	/**
	 * this method checks whether the performEffect method works
	 * correctly
	 */
	@Test
	public void testPerformEffect() {
		assert(player.getEndEffects().size() == 1 && player.getDevelopmentCard("Venture").size() == 0);
	}
	
}
