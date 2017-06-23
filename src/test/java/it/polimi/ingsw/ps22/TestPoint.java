package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Point;

public class TestPoint {
	

	@Test
	public void testClone() {
		Point p = new Point(4);
		assert(p.clone().getQuantity() == p.getQuantity());
	}
}
