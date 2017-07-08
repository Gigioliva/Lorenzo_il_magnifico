package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Wood;

/**
 * 
 * This class checks whether the {@link Wood} class behaves
 * properly or not
 *
 */
public class TestWood {

	private Wood s = new Wood(9);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "Wood" && s.clone().getQuantity() == 9);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "Wood");
	}
}
