package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * 
 * This class checks whether the {@link VictoryPoint} class works
 * properly or not
 *
 */
public class TestVictoryPoint {

	private VictoryPoint s = new VictoryPoint(9);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(s.clone().getName() == "VictoryPoint" && s.clone().getQuantity() == 9);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(s.getName() == "VictoryPoint");
	}
}
