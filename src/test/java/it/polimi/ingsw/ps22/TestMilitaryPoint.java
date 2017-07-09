package it.polimi.ingsw.ps22;

import org.junit.Test;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;

/**
 * 
 * This class checks whether the {@link MilitaryPoint} class 
 * works properly or not
 *
 */

public class TestMilitaryPoint {
	
	private MilitaryPoint s = new MilitaryPoint(9);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	
	@Test
	public void testClone() {
		assert(s.clone().getName() == "MilitaryPoint" && s.clone().getQuantity() == 9);
	}
	
	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	
	@Test
	public void testGetName() {
		assert(s.getName() == "MilitaryPoint");
	}

}
