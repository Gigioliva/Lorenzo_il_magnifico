package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestCardBuilding {

	private CardBuilding card;
	private Player player;
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		player.addSpecificResource("Coin", new Coin(5));
		card=new CardBuilding();
		card.setName("Carta prova");
		card.setEra(1);
		card.addCost("Stone", new Stone(2));
		card.addCost("Wood", new Wood(2));
		card.addCost("Coin", new Coin(1));
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
		CardBuilding clone=card.clone();
		assert(clone.toString().equals(card.toString()));
	}
	
	@Test
	public void testEffect(){
		card.applyImmediateEffects(player, null);
		card.applyActionEffect(player, 0, null);
		assert(player.getSpecificResource("Coin").getQuantity()==8);
		assert(player.getSpecificResource("Stone").getQuantity()==3);
	}
	
	@Test
	public void testSetCard(){
		assert(card.getActionValue()==2);
		assert(card.getName().equals("Carta prova"));
		assert(card.getEra()==1);
		assert(card.getCost().get("Coin").getQuantity()==1);
		assert(card.getCost().get("Stone").getQuantity()==2);
		assert(card.getCost().get("Wood").getQuantity()==2);
		assert(card.getActionEffects().size()==1);
	}
	
	@Test
	public void testTakeCard(){
		assert(card.takeCardControl(player));
		card.applyCostToPlayer(player);
		assert(player.getSpecificResource("Coin").getQuantity()==4);
		assert(player.getSpecificResource("Stone").getQuantity()==0);
		assert(player.getSpecificResource("Wood").getQuantity()==0);
	}
}
