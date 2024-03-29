package it.polimi.ingsw.ps22;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;

/**
 * 
 * This class checks whether the {@link CardLeader} class works properly or
 * not
 *
 */

public class TestCardLeader {
	
	private CardLeader card;
	private Player player;
	
	/**
	 * this method initialize the {@link CardLeader} and the {@link Player}
	 * 
	 */
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		card=new CardLeader("Carta Prova");
		GainResource eff=new GainResource();
		eff.addGain("Coin", new Coin(3));
		card.addImmediateEffect(eff);
		HashMap<String, Integer> req1=new HashMap<>();
		req1.put("Coin", 2);
		req1.put("Stone", 5);
		HashMap<String, Integer> req2=new HashMap<>();
		req2.put("Wood", 2);
		card.addRequisite(req1);
		card.addRequisite(req2);
	}
	
	/**
	 * this method tests the Clone method in {@link CardLeader}
	 */
	
	@Test
	public void testClone(){
		CardLeader clone=card.clone();
		assert(clone.toString().equals(card.toString()));
		assert(card.getName().equals("Carta Prova"));
	}
	
	/**
	 * this method tests the TakeCardControl method in {@link CardLeader}
	 */
	
	@Test
	public void testTakeCard(){
		assert(card.takeCardControl(player));
	}
	
	/**
	 * this method tests the applyEffect method in {@link CardLeader}
	 */
	
	@Test
	public void testApplyEffect(){
		card.playLeader(player, null);
		assert(card.isPlay());
		assert(!card.getCopy());
		assert(player.getSpecificResource("Coin").getQuantity()==3);
		card.resetLeader();
		assert(!card.isPlay());
	}

}
