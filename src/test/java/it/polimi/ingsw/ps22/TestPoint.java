package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Point;

/**
 * 
 * This class checks whether the {@link Point} class 
 * works properly or not
 *
 */
public class TestPoint {
	

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		Point p = new Point(4);
		assert(p.clone().getQuantity() == p.getQuantity());
	}
}
