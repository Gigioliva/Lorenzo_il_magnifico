package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;

/**
 * 
 * This class checks whether the {@link CardTerritory} class works properly or
 * not
 *
 */

public class TestCardTerritory {
	
	private CardTerritory card;
	private Player player;
	
	/**
	 * this method initialize the {@link CardTerritory} and the {@link Player}
	 * 
	 */
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		card=new CardTerritory();
		card.setName("Carta prova");
		card.setEra(1);
		GainResource eff=new GainResource();
		eff.addGain("Coin", new Coin(3));
		card.addImmediateEffect(eff);
		GainResource eff2=new GainResource();
		eff2.addGain("Stone", new Stone(1));
		card.addActionEffect(eff2);
		card.setActionValue(2);
	}
	
	/**
	 * this method tests the Clone method in {@link CardTerritory}
	 */
	
	@Test
	public void testClone(){
		CardTerritory clone=card.clone();
		assert(clone.toString().equals(card.toString()));
	}
	
	/**
	 * this method tests the applyEffect method in {@link CardTerritory}
	 */
	
	@Test
	public void testEffect(){
		card.applyImmediateEffects(player, null);
		card.applyActionEffect(player, 0, null);
		assert(player.getSpecificResource("Coin").getQuantity()==3);
		assert(player.getSpecificResource("Stone").getQuantity()==3);
	}
	
	/**
	 * this method tests the correct initialization of the {@link CardTerritory}
	 */
	
	@Test
	public void testSetCard(){
		assert(card.getActionValue()==2);
		assert(card.getName().equals("Carta prova"));
		assert(card.getEra()==1);
	}
	
	/**
	 * this method tests the TakeCardControl method in {@link CardTerritory}
	 */
	
	@Test
	public void testTakeCard(){
		assert(card.takeCardControl(player));
		CardTerritory c1=new CardTerritory();
		c1.setName("Carta prova1");
		c1.setEra(1);
		CardTerritory c2=new CardTerritory();
		c2.setName("Carta prova2");
		c2.setEra(1);
		CardTerritory c3=new CardTerritory();
		c3.setName("Carta prova3");
		c3.setEra(1);
		CardTerritory c4=new CardTerritory();
		c4.setName("Carta prova4");
		c4.setEra(1);
		CardTerritory c5=new CardTerritory();
		c5.setName("Carta prova5");
		c5.setEra(1);
		player.addDevelopmentCard("Territory", c1);
		player.addDevelopmentCard("Territory", c2);
		player.addDevelopmentCard("Territory", c3);
		player.addDevelopmentCard("Territory", c4);
		player.addDevelopmentCard("Territory", c5);
		player.addSpecBonus("NoTerritoryReq");
		assert(card.takeCardControl(player));
	}
}
