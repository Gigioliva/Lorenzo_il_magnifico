package it.polimi.ingsw.ps22;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.player.BonusAcc;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * This class checks whether the {@link BonusAcc} class works properly or
 * not
 *
 */

public class TestBonusAcc {
	
	private BonusAcc bonus;
	
	/**
	 * this method initialize the {@link BonusAcc}
	 * 
	 */
	
	@Before
	public void init(){
		bonus=new BonusAcc();
	}
	
	/**
	 * this method tests the set/get methods in {@link BonusAcc}
	 */
	
	@Test
	public void testAddBonus(){
		HashMap<String, ResourceAbstract> discChar=new HashMap<>();
		discChar.put("Coin", new Coin(2));
		HashMap<String, ResourceAbstract> discVenture=new HashMap<>();
		discVenture.put("Stone", new Coin(1));
		bonus.addSales(discChar, "Character");
		bonus.addSales(discVenture, "Venture");
		assert(bonus.getSaleCharacter().getQuantity()==2);
		assert(bonus.getSaleVenture().get("Stone").getQuantity()==1);
		bonus.subSales(discChar, "Character");
		assert(bonus.getSaleCharacter().getQuantity()==0);
	}

}
