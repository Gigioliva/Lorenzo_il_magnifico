package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Stone;

/**
 * 
 * This class tests whether the {@link Stone} behaves properly or not
 *
 */
public class TestStone {

	private Stone s = new Stone(3);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "Stone" && s.clone().getQuantity() == 3);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "Stone");
	}

}
