package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.effect.BonusEffect;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.IncrementBuilding;

/**
 * 
 * This class checks whether the {@link CardExcomm} class works properly or
 * not
 *
 */

public class TestCardExcomm {
	
	private CardExcomm card;
	private Player player;
	
	/**
	 * this method initialize the {@link CardExcomm} and the {@link Player}
	 * 
	 */
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
		card=new CardExcomm();
		card.setEra(1);
		BonusEffect eff=new BonusEffect();
		eff.addBonus("IncrementBuilding", new IncrementBuilding(2));
		card.addPermanentEffect(eff);
		NoPointsCard eff2=new NoPointsCard("Building");
		card.addEndEffect(eff2);
		card.setPathname("prova.jpg");
	}
	
	/**
	 * this method tests the Clone method in {@link CardExcomm}
	 */
	
	@Test
	public void testClone(){
		CardExcomm clone=card.clone();
		assert(clone.toString().equals(card.toString()));
		assert(card.getEra()==1);
		assert(card.getPathname().equals("prova.jpg"));
	}
	
	/**
	 * this method tests the applyEffect method in {@link CardExcomm}
	 */
	
	@Test
	public void testApplyEffect(){
		card.applyPermanentEffects(player, null);
		assert(player.getBonusAcc().getBonus("IncrementBuilding").getQuantity()==2);
		card.loadEndEffects(player);
		assert(player.getEndEffects().size()==1);
	}

}
