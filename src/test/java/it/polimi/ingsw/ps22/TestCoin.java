package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Coin;

/**
 * 
 * This class check whether the {@link Coin} class behaves 
 * properly or not
 *
 */
public class TestCoin {

	private Coin s = new Coin(22);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "Coin" && s.clone().getQuantity() == 22);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "Coin");
	}
}
