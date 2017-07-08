package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * This class tests that the {@link Servant} class works properly
 *
 */
public class TestServant {
	
	private Servant s = new Servant(2);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "Servant" && s.clone().getQuantity() == 2);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "Servant");
	}

}
