package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.FaithPoint;

/**
 * 
 * This class checks whether the {@link FaithPoint} class 
 * behaves properly or not
 *
 */
public class TestFaithPoint {

	private FaithPoint s = new FaithPoint(9);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "FaithPoint" && s.clone().getQuantity() == 9);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "FaithPoint");
	}

}
