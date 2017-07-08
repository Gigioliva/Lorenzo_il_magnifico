package it.polimi.ingsw.ps22;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Stone;

/**
 * 
 * This class checks whether the {@link CardVenture} class works properly or
 * not
 *
 */

public class TestCardVenture {
	
	private CardVenture card;
	private HashMap<String, ResourceAbstract> cost;
	private HashMap<String, ResourceAbstract> requisite;
	private Player player;
	
	/**
	 * this method initialize the {@link CardVenture} and the {@link Player}
	 * 
	 */
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		player.getSpecBonus().setSpecBonus("DoubleGain");
		card = new CardVenture();
		cost = new HashMap<>();
		requisite = new HashMap<>();
		ResourceAbstract r1 = new Coin(3);
		cost.put("Coin", r1);
		ResourceAbstract r2 = new Stone(4);
		cost.put("Stone", r2);
		ResourceAbstract r3 = new MilitaryPoint(1);
		requisite.put("MilitaryPoint", r3);
		card.addRequisiteCost(cost, requisite);
		card.addEndEffect(new NoPointsCard("Territory"));
		GainResource eff=new GainResource();
		eff.addGain("Coin", new Coin(3));
		card.addImmediateEffect(eff);
	}
	
	/**
	 * this method tests the Clone method in {@link CardVenture}
	 */
	
	@Test
	public void testClone(){
		CardVenture clone=card.clone();
		assert(clone.toString().equals(card.toString()));
	}
	
	/**
	 * this method tests the applyEffect method in {@link CardVenture}
	 */
	
	@Test
	public void testApplyEffect(){
		card.applyImmediateEffects(player, null);
		card.loadEndEffects(player);
		assert(player.getSpecificResource("Coin").getQuantity()==6);
		assert(player.getEndEffects().size()==1);
	}
	
	/**
	 * this method tests the TakeCardControl method in {@link CardVenture}
	 */
	
	@Test
	public void testTakeCard(){
		player.addSpecificResource("Coin", new Coin(3));
		player.addSpecificResource("Stone", new Stone(2));
		player.addSpecificResource("MilitaryPoint", new MilitaryPoint(1));
		assert(card.takeCardControl(player));
	}
	
	/**
	 * this method tests the applyCostToPlayer method in {@link CardVenture}
	 */
	
	@Test
	public void testPayCard(){
		player.addSpecificResource("Coin", new Coin(4));
		player.addSpecificResource("Stone", new Stone(2));
		player.addSpecificResource("MilitaryPoint", new MilitaryPoint(1));
		card.applyCostToPlayer(player, card.getRequisiteCost().get(0));
		assert(player.getSpecificResource("Coin").getQuantity()==1);
		assert(player.getSpecificResource("Stone").getQuantity()==0);
		assert(player.getSpecificResource("Wood").getQuantity()==2);
		assert(player.getSpecificResource("Servant").getQuantity()==3);
		assert(player.getSpecificResource("MilitaryPoint").getQuantity()==1);
	}
}
