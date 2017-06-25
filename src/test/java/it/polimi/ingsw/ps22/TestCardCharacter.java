package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.effect.BonusEffect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;

public class TestCardCharacter {
	
	private CardCharacter card;
	private Player player;
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		card=new CardCharacter();
		card.setName("Carta prova");
		card.setEra(1);
		GainResource eff=new GainResource();
		eff.addGain("Coin", new Coin(3));
		card.addImmediateEffect(eff);
		BonusEffect eff2=new BonusEffect();
		eff2.addBonus("Coin", new Coin(2));
		card.addPermanentEffect(eff2);
		card.addCost(new Coin(2));
	}
	
	@Test
	public void testClone(){
		CardCharacter clone=card.clone();
		assert(clone.toString().equals(card.toString()));
	}
	
	@Test
	public void testCost(){
		assert(card.getCost().get("Coin").getQuantity()==2);
	}
	
	@Test
	public void testEffect(){
		card.applyImmediateEffects(player, null);
		card.applyPermanentEffects(player, null);
		assert(player.getSpecificResource("Coin").getQuantity()==3);
		assert(player.getBonusAcc().getBonus("Coin").getQuantity()==2);
	}
	
	@Test
	public void testTakeCard(){
		player.addSpecificResource("Coin", new Coin(5));
		assert(card.takeCardControl(player));
		card.applyCostToPlayer(player);
		assert(player.getSpecificResource("Coin").getQuantity()==3);
	}

}
