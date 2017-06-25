package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;

public class TestCardTerritory {
	
	private CardTerritory card;
	private Player player;
	
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
	
	@Test
	public void testClone(){
		CardTerritory clone=card.clone();
		assert(clone.toString().equals(card.toString()));
	}
	
	@Test
	public void testEffect(){
		card.applyImmediateEffects(player, null);
		card.applyActionEffect(player, 0, null);
		assert(player.getSpecificResource("Coin").getQuantity()==3);
		assert(player.getSpecificResource("Stone").getQuantity()==3);
	}
	
	@Test
	public void testSetCard(){
		assert(card.getActionValue()==2);
		assert(card.getName().equals("Carta prova"));
		assert(card.getEra()==1);
	}
	
	@Test
	public void testTakeCard(){
		assert(card.takeCardControl(player));
	}
}
